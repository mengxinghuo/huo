package com.truck.pojo;

public class StockSend {
    private Integer stockId;

    private Integer stockNum;

    public StockSend(Integer stockId, Integer stockNum) {
        this.stockId = stockId;
        this.stockNum = stockNum;
    }

    public StockSend() {
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }
}