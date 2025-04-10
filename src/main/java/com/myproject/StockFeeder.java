/*
package com.myproject;

import java.util.*;

public class StockFeeder {
    private List<Stock> stockList = new ArrayList<>();
    private Map<String, List<StockViewer>> viewers = new HashMap<>();
    private static StockFeeder instance = null;

    // Private constructor for Singleton pattern
    private StockFeeder() {}

    public static StockFeeder getInstance() {
        if (instance == null) {
            instance = new StockFeeder();
        }
        return instance;
    }

    public void addStock(Stock stock) {
        if (stock != null) {
            stockList.add(stock);
        }
    }

    public void registerViewer(String code, StockViewer stockViewer) {
        // Check if the stock code exists in the monitoring list
        boolean stockExists = stockList.stream().anyMatch(stock -> stock.getCode().equals(code));
        
        if (!stockExists) {
            Logger.errorRegister(code);
            return;
        }
        
        // Create a new list if this stock code doesn't have any viewers yet
        if (!viewers.containsKey(code)) {
            viewers.put(code, new ArrayList<>());
        }
        
        // Add the viewer if it's not already registered
        List<StockViewer> stockViewers = viewers.get(code);
        if (!stockViewers.contains(stockViewer)) {
            stockViewers.add(stockViewer);
        }
    }    

    public void unregisterViewer(String code, StockViewer stockViewer) {
        // Check if the stock code exists and has registered viewers
        if (!viewers.containsKey(code)) {
            Logger.errorUnregister(code);
            return;
        }
        
        List<StockViewer> stockViewers = viewers.get(code);
        if (!stockViewers.contains(stockViewer)) {
            Logger.errorUnregister(code);
            return;
        }
        
        // Remove the viewer
        stockViewers.remove(stockViewer);
    }

    public void notify(StockPrice stockPrice) {
        if (stockPrice != null) {
            String code = stockPrice.getCode();
            
            // Check if there are any viewers registered for this stock code
            if (viewers.containsKey(code)) {
                List<StockViewer> stockViewers = viewers.get(code);
                
                // Notify all registered viewers
                for (StockViewer viewer : stockViewers) {
                    viewer.onUpdate(stockPrice);
                }
            }
        }
    }
}
*/
package com.myproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockFeeder {
    private List<Stock> stockList = new ArrayList<>();
    private Map<String, List<StockViewer>> viewers = new HashMap<>();
    private static StockFeeder instance = null;

    // Implement Singleton pattern
    private StockFeeder() {}

    public static StockFeeder getInstance() {
        if (instance == null) {
            instance = new StockFeeder();
        }
        return instance;
    }
    
    // Reset method for tests
    public static void resetForTest() {
        instance = new StockFeeder();
    }

    public void addStock(Stock stock) {
        if (stock != null) {
            stockList.add(stock);
        }
    }

    public void registerViewer(String code, StockViewer stockViewer) {
        // Check if stock exists in the monitoring list
        boolean stockExists = stockList.stream().anyMatch(s -> s.getCode().equals(code));
        
        if (!stockExists) {
            Logger.errorRegister(code);
            return;
        }
        
        // Get or create list of viewers for this stock code
        List<StockViewer> stockViewers = viewers.computeIfAbsent(code, k -> new ArrayList<>());
        
        // Check if viewer is already registered
        if (stockViewers.contains(stockViewer)) {
            Logger.errorRegister(code);
            return;
        }
        
        // Register the viewer
        stockViewers.add(stockViewer);
    }    

    public void unregisterViewer(String code, StockViewer stockViewer) {
        // Check if stock and viewer list exists
        if (!viewers.containsKey(code)) {
            Logger.errorUnregister(code);
            return;
        }
        
        List<StockViewer> stockViewers = viewers.get(code);
        
        // Check if viewer is registered
        if (!stockViewers.contains(stockViewer)) {
            Logger.errorUnregister(code);
            return;
        }
        
        // Unregister the viewer
        stockViewers.remove(stockViewer);
    }

    public void notify(StockPrice stockPrice) {
        if (stockPrice == null) {
            return;
        }
        
        String code = stockPrice.getCode();
        
        // Only notify if we have registered viewers for this stock code
        if (viewers.containsKey(code)) {
            List<StockViewer> stockViewers = viewers.get(code);
            
            // Notify all registered viewers
            for (StockViewer viewer : stockViewers) {
                viewer.onUpdate(stockPrice);
            }
        }
    }
}



