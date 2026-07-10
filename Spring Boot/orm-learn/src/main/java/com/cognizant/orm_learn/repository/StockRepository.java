package com.cognizant.orm_learn.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.orm_learn.model.Stock;
import com.cognizant.orm_learn.model.StockId;

@Repository
public interface StockRepository extends JpaRepository<Stock, StockId> {

    List<Stock> findByCode(String code);

    List<Stock> findByCodeAndDateBetween(
            String code,
            LocalDate startDate,
            LocalDate endDate);

    List<Stock> findByCodeAndCloseGreaterThan(
            String code,
            double close);

    List<Stock> findByCodeOrderByVolumeDesc(String code);
}
