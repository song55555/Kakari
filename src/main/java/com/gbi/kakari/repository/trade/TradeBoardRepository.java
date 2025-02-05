package com.gbi.kakari.repository.trade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gbi.kakari.entity.trade.TradeBoard;

@Repository
public interface TradeBoardRepository extends JpaRepository<TradeBoard, Integer>, JpaSpecificationExecutor<TradeBoard> {
	Page<TradeBoard> findAll(Pageable pageable); // 페이지네이션
}