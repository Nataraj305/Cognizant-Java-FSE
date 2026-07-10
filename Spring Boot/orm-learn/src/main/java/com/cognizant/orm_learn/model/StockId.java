package com.cognizant.orm_learn.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class StockId implements Serializable {

    private String code;
    private LocalDate date;

    public StockId() {
    }

    public StockId(String code, LocalDate date) {
        this.code = code;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockId)) return false;
        StockId stockId = (StockId) o;
        return Objects.equals(code, stockId.code)
                && Objects.equals(date, stockId.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, date);
    }
}