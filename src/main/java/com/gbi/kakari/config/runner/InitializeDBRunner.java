package com.gbi.kakari.config.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.gbi.kakari.mapper.DummyMapper;
import com.gbi.kakari.repository.car.ProductColorRepository;
import com.gbi.kakari.repository.car.ProductRepository;
import com.gbi.kakari.repository.car.option.ProductOptionRepository;
import com.gbi.kakari.repository.point.PointRepository;
import com.gbi.kakari.repository.rent.RentLocationRepository;
import com.gbi.kakari.repository.user.UserInfoRepository;

@Component
public class InitializeDBRunner implements CommandLineRunner {
	private final DummyMapper dummyMapper;
	private final UserInfoRepository userInfoRepository;
	private final PointRepository pointRepository;
	private final ProductRepository productRepository;
	private final ProductColorRepository productColorRepository;
	private final ProductOptionRepository productOptionRepository;
	private final RentLocationRepository rentLocationRepository;

	private final boolean isTest = true;

	@Autowired
	public InitializeDBRunner(
		DummyMapper dummyMapper, UserInfoRepository userInfoRepository, PointRepository pointRepository,
		ProductRepository productRepository, ProductColorRepository productColorRepository, ProductOptionRepository productOptionRepository,
		RentLocationRepository rentLocationRepository) {
		this.dummyMapper = dummyMapper;
		this.productRepository = productRepository;
		this.productColorRepository = productColorRepository;
		this.productOptionRepository = productOptionRepository;
		this.userInfoRepository = userInfoRepository;
		this.pointRepository = pointRepository;
		this.rentLocationRepository = rentLocationRepository;
	}

	@Override
	public void run(String... args) {
		// product 테이블 더미 데이터 삽입 (이미지 파일명 포함)
		if (productRepository.count() == 0) { // 데이터가 없을때만 실행
			ProductRepositoryDummyDataInsert();
		}
		// productcolor 테이블 더미 데이터 삽입
		if (productColorRepository.count() == 0) { // 데이터가 없을때만 실행
			ProductColorRepositoryDummyDataInsert();
		}
		// productoption 테이블 더미 데이터 삽입
		if (productOptionRepository.count() == 0) { // 데이터가 없을때만 실행
			ProductOptionRepositoryDummyDataInsert();
		}
		// userinfo 테이블 더미 데이터 삽입
		if (userInfoRepository.count() == 0) { // 데이터가 없을때만 실행
			UserInfoRepositoryDummyDataInsert();
		}
		// point 테이블 더미 데이터 삽입
		if (pointRepository.count() == 0) { // 데이터가 없을때만 실행
			PointRepositoryDummyDataInsert();
		}
		// rentlocation 테이블 더미 데이터 삽입
		if (rentLocationRepository.count() == 0) { // 데이터가 없을때만 실행
			RentLocationDummyDataInsert();
		}
	}

	private void ProductRepositoryDummyDataInsert() {
		// product 테이블 더미 데이터 삽입
		dummyMapper.insertProduct("Model S", 100000.00, "model_s.jpg");
		dummyMapper.insertProduct("Model 3", 60000.00, "model_3.jpg");
		dummyMapper.insertProduct("Model X", 120000.00, "model_x.jpg");
		dummyMapper.insertProduct("Model Y", 70000.00, "model_y.jpg");
		dummyMapper.insertProduct("EQS", 150000.00, "eqs.jpg");
		dummyMapper.insertProduct("i4", 75000.00, "i4.jpg");
		dummyMapper.insertProduct("Taycan", 140000.00, "taycan.jpg");
		dummyMapper.insertProduct("Mach-E", 55000.00, "mach_e.jpg");
		dummyMapper.insertProduct("EV6", 50000.00, "ev6.jpg");
		dummyMapper.insertProduct("IONIQ 5", 48000.00, "ioniq5.jpg");
	}

	private void ProductColorRepositoryDummyDataInsert() {
		// productcolor 테이블 더미 데이터 삽입
		dummyMapper.insertProductColor(1, "Pearl White");
		dummyMapper.insertProductColor(1, "Midnight Silver Metallic");
		dummyMapper.insertProductColor(1, "Deep Blue Metallic");
		dummyMapper.insertProductColor(1, "Solid Black");
		dummyMapper.insertProductColor(1, "Red Multi-Coat");
		dummyMapper.insertProductColor(2, "Pearl White");
		dummyMapper.insertProductColor(2, "Midnight Silver Metallic");
		dummyMapper.insertProductColor(2, "Solid Black");
		dummyMapper.insertProductColor(2, "Red Multi-Coat");
		dummyMapper.insertProductColor(3, "Pearl White");
		dummyMapper.insertProductColor(3, "Midnight Silver Metallic");
		dummyMapper.insertProductColor(3, "Deep Blue Metallic");
		dummyMapper.insertProductColor(3, "Solid Black");
		dummyMapper.insertProductColor(3, "Red Multi-Coat");
		dummyMapper.insertProductColor(4, "Pearl White");
		dummyMapper.insertProductColor(4, "Midnight Silver Metallic");
		dummyMapper.insertProductColor(4, "Solid Black");
		dummyMapper.insertProductColor(4, "Red Multi-Coat");
		dummyMapper.insertProductColor(5, "Obsidian Black");
		dummyMapper.insertProductColor(5, "Polar White");
		dummyMapper.insertProductColor(5, "Selenite Gray");
		dummyMapper.insertProductColor(5, "Emerald Green");
		dummyMapper.insertProductColor(6, "Alpine White");
		dummyMapper.insertProductColor(6, "Black Sapphire Metallic");
		dummyMapper.insertProductColor(6, "Portimao Blue Metallic");
		dummyMapper.insertProductColor(6, "San Remo Green Metallic");
		dummyMapper.insertProductColor(7, "White");
		dummyMapper.insertProductColor(7, "Black");
		dummyMapper.insertProductColor(7, "Gray");
		dummyMapper.insertProductColor(7, "Blue");
		dummyMapper.insertProductColor(8, "Carbonized Gray");
		dummyMapper.insertProductColor(8, "Space White");
		dummyMapper.insertProductColor(8, "Grabber Blue");
		dummyMapper.insertProductColor(8, "Rapid Red");
		dummyMapper.insertProductColor(9, "Snow White Pearl");
		dummyMapper.insertProductColor(9, "Aurora Black");
		dummyMapper.insertProductColor(9, "Glacier White");
		dummyMapper.insertProductColor(9, "Interstellar Gray");
		dummyMapper.insertProductColor(10, "Atlas White");
		dummyMapper.insertProductColor(10, "Cyber Gray");
		dummyMapper.insertProductColor(10, "Digital Green");
		dummyMapper.insertProductColor(10, "Lucid Blue");
	}

	private void ProductOptionRepositoryDummyDataInsert() {
		// optiontype 테이블 더미 데이터 삽입
		dummyMapper.insertOptionType("Sunroof");
		dummyMapper.insertOptionType("Navigation System");
		dummyMapper.insertOptionType("Black Box");
		dummyMapper.insertOptionType("Auto Suspension");
		dummyMapper.insertOptionType("Premium Sound System");
		dummyMapper.insertOptionType("Leather Seats");
		dummyMapper.insertOptionType("Heated Seats");
		dummyMapper.insertOptionType("Ventilated Seats");
		dummyMapper.insertOptionType("Advanced Driver Assistance");
		dummyMapper.insertOptionType("Wireless Charging");

		// productoption 테이블 더미 데이터 삽입
		dummyMapper.insertProductOption(1, 1);
		dummyMapper.insertProductOption(1, 2);
		dummyMapper.insertProductOption(1, 3);
		dummyMapper.insertProductOption(1, 4);
		dummyMapper.insertProductOption(1, 5);
		dummyMapper.insertProductOption(1, 6);
		dummyMapper.insertProductOption(1, 7);
		dummyMapper.insertProductOption(1, 8);
		dummyMapper.insertProductOption(1, 9);
		dummyMapper.insertProductOption(1, 10);
		dummyMapper.insertProductOption(2, 1);
		dummyMapper.insertProductOption(2, 2);
		dummyMapper.insertProductOption(2, 3);
		dummyMapper.insertProductOption(2, 4);
		dummyMapper.insertProductOption(2, 5);
		dummyMapper.insertProductOption(2, 6);
		dummyMapper.insertProductOption(2, 7);
		dummyMapper.insertProductOption(2, 9);
		dummyMapper.insertProductOption(3, 1);
		dummyMapper.insertProductOption(3, 2);
		dummyMapper.insertProductOption(3, 3);
		dummyMapper.insertProductOption(3, 4);
		dummyMapper.insertProductOption(3, 5);
		dummyMapper.insertProductOption(3, 6);
		dummyMapper.insertProductOption(3, 7);
		dummyMapper.insertProductOption(3, 8);
		dummyMapper.insertProductOption(3, 9);
		dummyMapper.insertProductOption(3, 10);
		dummyMapper.insertProductOption(4, 1);
		dummyMapper.insertProductOption(4, 2);
		dummyMapper.insertProductOption(4, 3);
		dummyMapper.insertProductOption(4, 4);
		dummyMapper.insertProductOption(4, 5);
		dummyMapper.insertProductOption(4, 6);
		dummyMapper.insertProductOption(4, 7);
		dummyMapper.insertProductOption(4, 9);
		dummyMapper.insertProductOption(5, 1);
		dummyMapper.insertProductOption(5, 2);
		dummyMapper.insertProductOption(5, 3);
		dummyMapper.insertProductOption(5, 4);
		dummyMapper.insertProductOption(5, 5);
		dummyMapper.insertProductOption(5, 6);
		dummyMapper.insertProductOption(5, 7);
		dummyMapper.insertProductOption(5, 8);
		dummyMapper.insertProductOption(5, 9);
		dummyMapper.insertProductOption(5, 10);
		dummyMapper.insertProductOption(6, 1);
		dummyMapper.insertProductOption(6, 2);
		dummyMapper.insertProductOption(6, 3);
		dummyMapper.insertProductOption(6, 4);
		dummyMapper.insertProductOption(6, 5);
		dummyMapper.insertProductOption(6, 6);
		dummyMapper.insertProductOption(6, 7);
		dummyMapper.insertProductOption(6, 8);
		dummyMapper.insertProductOption(6, 9);
		dummyMapper.insertProductOption(7, 1);
		dummyMapper.insertProductOption(7, 2);
		dummyMapper.insertProductOption(7, 3);
		dummyMapper.insertProductOption(7, 4);
		dummyMapper.insertProductOption(7, 5);
		dummyMapper.insertProductOption(7, 6);
		dummyMapper.insertProductOption(7, 7);
		dummyMapper.insertProductOption(7, 9);
		dummyMapper.insertProductOption(8, 1);
		dummyMapper.insertProductOption(8, 2);
		dummyMapper.insertProductOption(8, 3);
		dummyMapper.insertProductOption(8, 4);
		dummyMapper.insertProductOption(8, 5);
		dummyMapper.insertProductOption(8, 6);
		dummyMapper.insertProductOption(8, 7);
		dummyMapper.insertProductOption(8, 9);
		dummyMapper.insertProductOption(9, 1);
		dummyMapper.insertProductOption(9, 2);
		dummyMapper.insertProductOption(9, 3);
		dummyMapper.insertProductOption(9, 4);
		dummyMapper.insertProductOption(9, 5);
		dummyMapper.insertProductOption(9, 6);
		dummyMapper.insertProductOption(9, 7);
		dummyMapper.insertProductOption(9, 9);
		dummyMapper.insertProductOption(10, 1);
		dummyMapper.insertProductOption(10, 2);
		dummyMapper.insertProductOption(10, 3);
		dummyMapper.insertProductOption(10, 4);
		dummyMapper.insertProductOption(10, 5);
		dummyMapper.insertProductOption(10, 6);
		dummyMapper.insertProductOption(10, 7);
		dummyMapper.insertProductOption(10, 9);
	}

	private void UserInfoRepositoryDummyDataInsert() {
		// userinfo 테이블 더미 데이터 삽입
		dummyMapper.insertUserInfo(
			"testuser01", "testuser01@email.com", "TestUser01", "TestUser01*", "01011111111", 1);
		dummyMapper.insertUserInfo(
			"testuser02", "testuser02@email.com", "TestUser02", "TestUser02*", "01022222222", 1);
		dummyMapper.insertUserInfo(
			"testadmin01", "testadmin01@email.com", "TestAdmin01", "TestAdmin01*", "01091919191", Integer.MAX_VALUE);
		dummyMapper.insertUserInfo(
			"testadmin02", "testadmin02@email.com", "TestAdmin02", "TestAdmin02*", "01092929292", Integer.MAX_VALUE);
	}

	private void PointRepositoryDummyDataInsert() {
		// point 테이블 더미 데이터 삽입
		dummyMapper.insertPoint(10000000, "testuser01");
		dummyMapper.insertPoint(20000000, "testuser02");
		dummyMapper.insertPoint(100000000, "testadmin01");
		dummyMapper.insertPoint(200000000, "testadmin02");
	}

	private void RentLocationDummyDataInsert() {
		// rentlocation 테이블 더미 데이터 삽입
		dummyMapper.insertRentLocation("서울 KAKARI 본점", "Seoul, Korea", "01012345678");
		dummyMapper.insertRentLocation("부산 광운대 KAKARI 대리점", "Busan, Korea", "01087654321");
		dummyMapper.insertRentLocation("대구 KAKARI 지점", "Daegu, Korea", "01013579246");
		dummyMapper.insertRentLocation("인천 KAKARI 지점", "Incheon, Korea", "01024681357");
		dummyMapper.insertRentLocation("광주 KAKARI 지점", "Gwangju, Korea", "01036925814");
		dummyMapper.insertRentLocation("충청남도 서산 KAKARI 대리점", "Invalid", "01058246913");
	}
}
