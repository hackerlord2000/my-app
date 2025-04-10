/*
package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockAlertView implements StockViewer {
    private double alertThresholdHigh;
    private double alertThresholdLow;
    private Map<String, Double> lastAlertedPrices = new HashMap<>();

    public StockAlertView(double highThreshold, double lowThreshold) {
        this.alertThresholdHigh = highThreshold;
        this.alertThresholdLow = lowThreshold;
    }

    @Override
    public void onUpdate(StockPrice stockPrice) {
        String stockCode = stockPrice.getCode();
        double currentPrice = stockPrice.getAvgPrice();
        
        // Get the last alerted price if it exists, otherwise use the current price
        double lastPrice = lastAlertedPrices.getOrDefault(stockCode, currentPrice);
        
        // Check threshold conditions
        if (currentPrice >= alertThresholdHigh && lastPrice < alertThresholdHigh) {
            // Alert that price has gone above high threshold
            alertAbove(stockCode, currentPrice);
            lastAlertedPrices.put(stockCode, currentPrice);
        } else if (currentPrice <= alertThresholdLow && lastPrice > alertThresholdLow) {
            // Alert that price has gone below low threshold
            alertBelow(stockCode, currentPrice);
            lastAlertedPrices.put(stockCode, currentPrice);
        } else if (!lastAlertedPrices.containsKey(stockCode)) {
            // Initialize the last alerted price if this is the first update
            lastAlertedPrices.put(stockCode, currentPrice);
        }
    }

    private void alertAbove(String stockCode, double price) {
        Logger.logAlert(stockCode, price);
    }

    private void alertBelow(String stockCode, double price) {
        Logger.logAlert(stockCode, price);
    }
}
*/
package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockAlertView implements StockViewer {
    private double alertThresholdHigh;
    private double alertThresholdLow;
    private Map<String, Double> lastAlertedPrices = new HashMap<>();

    public StockAlertView(double highThreshold, double lowThreshold) {
        this.alertThresholdHigh = highThreshold;
        this.alertThresholdLow = lowThreshold;
    }

    @Override
    public void onUpdate(StockPrice stockPrice) {
        String stockCode = stockPrice.getCode();
        double currentPrice = stockPrice.getAvgPrice();
        
        // Check if price is above high threshold
        if (currentPrice >= alertThresholdHigh) {
            alertAbove(stockCode, currentPrice);
            lastAlertedPrices.put(stockCode, currentPrice);
        }
        // Check if price is below low threshold
        else if (currentPrice <= alertThresholdLow) {
            alertBelow(stockCode, currentPrice);
            lastAlertedPrices.put(stockCode, currentPrice);
        }
        // Store the price if no alert triggered
        else {
            lastAlertedPrices.put(stockCode, currentPrice);
        }
    }

    private void alertAbove(String stockCode, double price) {
        Logger.logAlert(stockCode, price);
    }

    private void alertBelow(String stockCode, double price) {
        Logger.logAlert(stockCode, price);
    }
}

