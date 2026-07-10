package com.cognizant.orm_learn.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.orm_learn.model.Stock;
import com.cognizant.orm_learn.repository.StockRepository;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public List<Stock> getStocks(String code) {
        return stockRepository.findByCode(code);
    }

    public List<Stock> getStocksBetweenDates(String code,
                                             LocalDate startDate,
                                             LocalDate endDate) {
        return stockRepository.findByCodeAndDateBetween(code, startDate, endDate);
    }

    public List<Stock> getStocksGreaterThan(String code,
                                            double close) {
        return stockRepository.findByCodeAndCloseGreaterThan(code, close);
    }

    public List<Stock> getStocksOrderByVolume(String code) {
        return stockRepository.findByCodeOrderByVolumeDesc(code);
    }
}