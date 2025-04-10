package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockRealtimePriceView implements StockViewer {
    private final Map<String, Double> lastPrices = new HashMap<>();

    @Override
    public void onUpdate(StockPrice stockPrice) {
        String stockCode = stockPrice.getCode();
        double currentPrice = stockPrice.getAvgPrice();
        
        /*
        // If this is the first update for this stock or if the price has changed
        if (!lastPrices.containsKey(stockCode) || lastPrices.get(stockCode) != currentPrice) {
            // Update the last known price
            lastPrices.put(stockCode, currentPrice);
            
            // Log the real-time price update
            Logger.logRealtime(stockCode, currentPrice);
        }
        */
        // Get the last price, or use current price as default if not available
        Double lastPrice = lastPrices.get(stockCode);
        // Log if price has changed or this is the first update for this stock
        if (lastPrice == null || lastPrice != currentPrice) {
            Logger.logRealtime(stockCode, currentPrice);
            lastPrices.put(stockCode, currentPrice);
        }
    }
}