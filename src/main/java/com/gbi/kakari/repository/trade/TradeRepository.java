package com.gbi.kakari.repository.trade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gbi.kakari.entity.trade.Trade;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer> {
}
