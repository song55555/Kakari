package com.gbi.kakari.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gbi.kakari.dto.TradeBoardDTO;
import com.gbi.kakari.dto.TradeBoardDetailDTO;
import com.gbi.kakari.dto.TradeBoardRequestDTO;
import com.gbi.kakari.dto.TradeDTO;
import com.gbi.kakari.entity.car.Product;
import com.gbi.kakari.entity.car.ProductColor;
import com.gbi.kakari.entity.car.UserCar;
import com.gbi.kakari.entity.car.option.UserCarOption;
import com.gbi.kakari.entity.trade.Trade;
import com.gbi.kakari.entity.trade.TradeBoard;
import com.gbi.kakari.entity.user.UserInfo;
import com.gbi.kakari.exception.access.AccessRestrictionException;
import com.gbi.kakari.exception.database.ElementNotFoundException;
import com.gbi.kakari.repository.car.ProductColorRepository;
import com.gbi.kakari.repository.car.ProductRepository;
import com.gbi.kakari.repository.car.UserCarRepository;
import com.gbi.kakari.repository.car.option.OptionTypeRepository;
import com.gbi.kakari.repository.car.option.UserCarOptionRepository;
import com.gbi.kakari.repository.trade.TradeBoardRepository;
import com.gbi.kakari.repository.trade.TradeRepository;
import com.gbi.kakari.repository.user.UserInfoRepository;

import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TradeService {
	private final TradeBoardRepository tradeBoardRepository;
	private final TradeRepository tradeRepository;
	private final UserInfoRepository userInfoRepository;
	private final UserCarRepository userCarRepository;
	private final ProductRepository productRepository;
	private final ProductColorRepository productColorRepository;
	private final UserCarOptionRepository userCarOptionRepository;

	private final PointService pointService; // PointService DI
	private final OptionTypeRepository optionTypeRepository;

	@Autowired
	public TradeService(TradeBoardRepository tradeBoardRepository, TradeRepository tradeRepository,
		UserInfoRepository userInfoRepository, UserCarRepository userCarRepository,
		ProductRepository productRepository, ProductColorRepository productColorRepository,
		UserCarOptionRepository userCarOptionRepository, PointService pointService, OptionTypeRepository optionTypeRepository) { // PointService DI
		this.tradeBoardRepository = tradeBoardRepository;
		this.tradeRepository = tradeRepository;
		this.userInfoRepository = userInfoRepository;
		this.userCarRepository = userCarRepository;
		this.productRepository = productRepository;
		this.productColorRepository = productColorRepository;
		this.userCarOptionRepository = userCarOptionRepository;
		this.pointService = pointService;
		this.optionTypeRepository = optionTypeRepository;
	}

	// 중고거래 게시글 목록 조회 (페이지네이션)
	public Page<TradeBoardDTO> getTradeBoardList(Pageable pageable) {
		log.debug("getTradeBoardList() 호출, pageable: {}", pageable);
		Page<TradeBoard> tradeBoardPage = tradeBoardRepository.findAll(
			(root, query, criteriaBuilder) -> { // Specification 추가
				return criteriaBuilder.equal(root.get("tradeBoardStatus"), 0); // tradeBoardStatus = 0 조건 추가 (활성 상태)
			},
			pageable
		);
		return tradeBoardPage.map(this::mapToTradeBoardDTO); // DTO 변환 메서드 활용
	}

	// 중고거래 게시글 검색 (제목, 내용, 제목+내용)
	public Page<TradeBoardDTO> searchTradeBoardList(Pageable pageable, String keyword, String searchType) {
		log.debug("searchTradeBoardList() 호출, pageable: {}, keyword: {}, searchType: {}", pageable, keyword, searchType);
		Specification<TradeBoard> spec = createSearchSpecification(keyword, searchType)
			.and((root, query, criteriaBuilder) ->  // AND 조건 추가
				criteriaBuilder.equal(root.get("tradeBoardStatus"), 0) // tradeBoardStatus = 0 조건 추가 (활성 상태)
			);

		Page<TradeBoard> tradeBoardPage = tradeBoardRepository.findAll(spec, pageable);
		return tradeBoardPage.map(this::mapToTradeBoardDTO); // DTO 변환 메서드 활용
	}

	// TradeBoard 엔티티를 TradeBoardDTO 로 변환하는 메서드
	private TradeBoardDTO mapToTradeBoardDTO(TradeBoard tradeBoard) {
		UserInfo userInfo = userInfoRepository.findById(tradeBoard.getUserId())
			.orElseThrow(() -> new ElementNotFoundException("User not found: " + tradeBoard.getUserId()));
		Trade trade = tradeRepository.findById(tradeBoard.getTrade().getTradeId())
			.orElseThrow(() -> new ElementNotFoundException("Trade not found: " + tradeBoard.getTrade().getTradeId()));
		UserCar userCar = userCarRepository.findById(trade.getProductId()) // Trade 엔티티의 productId 가 UserCar의 userCarId 로 사용됨. 수정 필요.
			.orElseThrow(() -> new ElementNotFoundException("UserCar not found: " + trade.getProductId())); // 수정 필요
		Product product = productRepository.findById(userCar.getProductId())
			.orElseThrow(() -> new ElementNotFoundException("Product not found: " + userCar.getProductId()));

		return new TradeBoardDTO(
			tradeBoard.getTradePostId(),
			tradeBoard.getUserId(),
			userInfo.getUserName(),
			tradeBoard.getUsedTitle(),
			Timestamp.valueOf(tradeBoard.getUsedCreatedDate()),
			trade.getTradeId(),
			trade.getTradePrice(),
			product.getProductName(),
			tradeBoard.getTradeBoardStatus() // TradeBoard 상태 값 DTO 에 매핑
		);
	}

	// 검색 조건 Specification 생성 메서드
	private Specification<TradeBoard> createSearchSpecification(String keyword, String searchType) {
		return (root, query, criteriaBuilder) -> {
			Predicate predicate = null;
			switch (searchType) {
				case "title":
					predicate = criteriaBuilder.like(root.get("usedTitle"), "%" + keyword + "%");
					break;
				case "content":
					predicate = criteriaBuilder.like(root.get("usedContent"), "%" + keyword + "%");
					break;
				case "titleContent":
					Predicate titlePredicate = criteriaBuilder.like(root.get("usedTitle"), "%" + keyword + "%");
					Predicate contentPredicate = criteriaBuilder.like(root.get("usedContent"), "%" + keyword + "%");
					predicate = criteriaBuilder.or(titlePredicate, contentPredicate);
					break;
				default:
					predicate = criteriaBuilder.or(
						criteriaBuilder.like(root.get("usedTitle"), "%" + keyword + "%"),
						criteriaBuilder.like(root.get("usedContent"), "%" + keyword + "%")
					); // 기본 검색 타입: 제목 + 내용
					break;
			}
			return predicate;
		};
	}

	// 특정 중고거래 게시글 상세 조회
	@Transactional(readOnly = true) // 읽기 전용 트랜잭션 설정
	public TradeBoardDetailDTO getTradeBoardDetail(Integer tradePostId) {
		log.debug("getTradeBoardDetail() 호출, tradePostId: {}", tradePostId);
		TradeBoard tradeBoard = tradeBoardRepository.findById(tradePostId)
			.orElseThrow(() -> new ElementNotFoundException("TradeBoard not found: " + tradePostId));
		UserInfo userInfo = userInfoRepository.findById(tradeBoard.getUserId())
			.orElseThrow(() -> new ElementNotFoundException("User not found: " + tradeBoard.getUserId()));

		Trade trade = tradeRepository.findById(tradeBoard.getTrade().getTradeId())
			.orElseThrow(() -> new ElementNotFoundException("Trade not found: " + tradeBoard.getTrade().getTradeId()));
		UserCar userCar = userCarRepository.findById(trade.getProductId())
			.orElseThrow(() -> new ElementNotFoundException("UserCar not found: " + trade.getProductId()));
		Product product = productRepository.findById(userCar.getProductId())
			.orElseThrow(() -> new ElementNotFoundException("Product not found: " + userCar.getProductId()));
		ProductColor productColor = productColorRepository.findById(userCar.getProductColorId())
			.orElseThrow(() -> new ElementNotFoundException("ProductColor not found: " + userCar.getProductColorId()));
		List<UserCarOption> userCarOptions = userCarOptionRepository.findByUserCarId(userCar.getUserCarId());
		String optionNames = userCarOptions.stream()
			.map(userCarOption -> optionTypeRepository.findById(
					userCarOption.getProductOption().getOptionTypeId()) // ProductOption 과 Product 가 ManyToOne 관계가 아니므로 수정 필요. OptionType으로 연결해야 함.
				.orElseThrow(
					() -> new ElementNotFoundException("OptionType not found: " + userCarOption.getProductOption().getOptionTypeId())) // 수정 필요
				.getOptionName()) // optionType.getOptionName() 으로 수정해야 함.
			.collect(Collectors.joining(", "));

		TradeDTO tradeDTO = new TradeDTO( // TradeDTO 생성자 활용
			trade.getTradeId(),
			trade.getUserId(),
			trade.getTraderId(),
			userCar.getUserCarId(),
			Timestamp.valueOf(trade.getTradeDate()),
			trade.getTradePrice(),
			trade.getTradeLocation(),
			product.getProductName(),
			productColor.getColorName(),
			optionNames
		);

		return new TradeBoardDetailDTO(tradeBoard, tradeDTO, userInfo.getUserName());
	}

	// 중고거래 게시글 등록
	@Transactional
	public Integer createTradeBoard(TradeBoardRequestDTO requestDTO, String userId) {
		log.debug("createTradeBoard() 호출, requestDTO: {}, userId: {}", requestDTO, userId);
		// Trade 생성
		LocalDateTime tradeDate = parseDateTime(requestDTO.getTradeDate(), requestDTO.getTradeTime());

		Trade trade = Trade.builder()
			.userId(userId)
			.productId(requestDTO.getProductId()) // ProductId 가 UserCarId 이나 이름이 다르니 주의
			.tradeDate(tradeDate)
			.tradePrice(requestDTO.getTradePrice())
			.tradeLocation(requestDTO.getTradeLocation())
			.build();
		Trade savedTrade = tradeRepository.save(trade);

		// TradeBoard 생성 (Trade 생성 후 tradeId 사용)
		TradeBoard tradeBoard = TradeBoard.builder()
			.userId(userId)
			.usedTitle(requestDTO.getUsedTitle())
			.usedContent(requestDTO.getUsedContent())
			.trade(savedTrade) // 저장된 Trade 엔티티 연결
			.build();
		TradeBoard savedTradeBoard = tradeBoardRepository.save(tradeBoard);
		log.info("TradeBoard 게시글 생성 완료. tradePostId: {} / tradeBoardStatus: {}",
			savedTradeBoard.getTradePostId(), savedTradeBoard.getTradeBoardStatus());
		return savedTradeBoard.getTradePostId();
	}

	// LocalDateTime 파싱 유틸리티 메서드
	private LocalDateTime parseDateTime(String date, String time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		return LocalDateTime.parse(date + "T" + time, formatter);
	}

	// 중고거래 게시글 수정
	@Transactional
	public void updateTradeBoard(TradeBoardRequestDTO requestDTO, Integer tradePostId, String userId) {
		log.debug("updateTradeBoard() 호출, requestDTO: {}, tradePostId: {}, userId: {}", requestDTO, tradePostId, userId);
		TradeBoard tradeBoard = tradeBoardRepository.findById(tradePostId)
			.orElseThrow(() -> new ElementNotFoundException("TradeBoard not found: " + tradePostId));

		if (!tradeBoard.getUserId().equals(userId)) {
			throw new AccessRestrictionException("/trade/edit", "게시글 수정 권한이 없습니다."); // 예외 변경
		}

		// TradeBoard 정보 업데이트
		tradeBoard.setUsedTitle(requestDTO.getUsedTitle());
		tradeBoard.setUsedContent(requestDTO.getUsedContent());
		tradeBoardRepository.save(tradeBoard);

		// Trade 정보 업데이트
		Trade trade = tradeRepository.findById(tradeBoard.getTrade().getTradeId())
			.orElseThrow(() -> new ElementNotFoundException("Trade not found: " + tradeBoard.getTrade().getTradeId()));

		LocalDateTime tradeDate = parseDateTime(requestDTO.getTradeDate(), requestDTO.getTradeTime());

		trade.setProductId(requestDTO.getProductId()); // UserCarId 를 받아야 함. 수정 필요
		trade.setTradeDate(tradeDate);
		trade.setTradePrice(requestDTO.getTradePrice());
		trade.setTradeLocation(requestDTO.getTradeLocation());
		tradeRepository.save(trade);
		log.info("TradeBoard 게시글 수정 완료. tradePostId: {}", tradePostId);
	}

	// 중고거래 게시글 삭제 (상태 변경)
	@Transactional
	public void deleteTradeBoard(Integer tradePostId, String userId) {
		log.debug("deleteTradeBoard() 호출, tradePostId: {}, userId: {}", tradePostId, userId);
		TradeBoard tradeBoard = tradeBoardRepository.findById(tradePostId)
			.orElseThrow(() -> new ElementNotFoundException("TradeBoard not found: " + tradePostId));

		if (!tradeBoard.getUserId().equals(userId)) {
			throw new AccessRestrictionException("/trade/delete", "게시글 삭제 권한이 없습니다.");
		}

		tradeBoard.setTradeBoardStatus(2); // 게시글 상태를 '삭제됨' (2) 으로 변경
		tradeBoardRepository.save(tradeBoard); // 업데이트
		log.info("TradeBoard 게시글 상태 변경 (삭제됨). tradePostId: {}", tradePostId);
	}

	// 중고거래 구매
	@Transactional
	public void purchaseTrade(Integer tradePostId, String buyerUserId) {
		log.debug("purchaseTrade() 호출, tradePostId: {}, buyerUserId: {}", tradePostId, buyerUserId);
		TradeBoard tradeBoard = tradeBoardRepository.findById(tradePostId)
			.orElseThrow(() -> new ElementNotFoundException("TradeBoard not found: " + tradePostId));
		Trade trade = tradeRepository.findById(tradeBoard.getTrade().getTradeId())
			.orElseThrow(() -> new ElementNotFoundException("Trade not found: " + tradeBoard.getTrade().getTradeId()));

		String sellerUserId = trade.getUserId();
		Integer userCarId = trade.getProductId();
		Long tradePrice = trade.getTradePrice();
		long commission = (long)(tradePrice * 0.02); // 2% 수수료

		// 판매자 포인트 증가 (거래 가격의 98%)
		pointService.chargePoint(sellerUserId, tradePrice - commission);
		log.debug("판매자 '{}' 포인트 충전: {}", sellerUserId, tradePrice - commission);

		// 구매자 포인트 감소 (거래 가격)
		pointService.usePoint(buyerUserId, com.gbi.kakari.type.point.PurchaseType.TRADE, trade.getTradeId(), tradePrice);
		log.debug("구매자 '{}' 포인트 사용: {}, purchaseType: TRADE, tradeId: {}", buyerUserId, tradePrice, trade.getTradeId());

		// UserCar 테이블 userId 업데이트
		UserCar userCar = userCarRepository.findById(userCarId)
			.orElseThrow(() -> new ElementNotFoundException("UserCar not found: " + userCarId));
		userCar.setUserId(buyerUserId);
		userCarRepository.save(userCar);
		log.debug("UserCar '{}' userId 변경: {} -> {}", userCarId, sellerUserId, buyerUserId);

		// Trade 테이블에 구매자 ID 업데이트
		trade.setTraderId(buyerUserId);
		tradeRepository.save(trade);
		log.info("Trade 구매 완료. tradeId: {}, 구매자: {}", trade.getTradeId(), buyerUserId);

		// TradeBoard 상태를 '판매 완료' (1) 로 변경 (삭제 대신 상태 변경)
		tradeBoard.setTradeBoardStatus(1); // 게시글 상태를 '판매 완료' (1) 로 변경
		tradeBoardRepository.save(tradeBoard); // 업데이트
		log.info("TradeBoard 게시글 상태 변경 (판매 완료). tradePostId: {}", tradePostId);
	}
}