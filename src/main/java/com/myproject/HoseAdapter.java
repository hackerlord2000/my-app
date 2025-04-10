package com.myproject;

import java.util.ArrayList;
import java.util.List;

public class HoseAdapter implements PriceFetcher {
    private HosePriceFetchLib hoseLib;
    private List<String> stockCodes;
 
    public HoseAdapter(HosePriceFetchLib hoseLib, List<String> stockCodes) {
        this.hoseLib = hoseLib;
        this.stockCodes = stockCodes;
    }

    @Override
    public List<StockPrice> fetch() {
        List<StockPrice> stockPrices = new ArrayList<>();
        
        // Get HOSE data for requested stock codes
        List<HoseData> hoseDataList = hoseLib.getPrices(stockCodes);
        
        // Convert HoseData objects to StockPrice objects
        for (HoseData hoseData : hoseDataList) {
            stockPrices.add(convertToStockPrice(hoseData));
        }
        
        return stockPrices;
    }

    private StockPrice convertToStockPrice(HoseData hoseData) {
        return new StockPrice(
            hoseData.getStockCode(),
            hoseData.getPrice(),
            hoseData.getVolume(),
            hoseData.getTimestamp()
        );
    }
}