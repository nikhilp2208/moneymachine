package com.npatil.moneymachine.services.impl;

import com.csvreader.CsvReader;
import com.google.inject.Inject;
import com.npatil.moneymachine.dao.TradeDetailsDao;
import com.npatil.moneymachine.enums.TransactionType;
import com.npatil.moneymachine.models.StockTransactionRequest;
import com.npatil.moneymachine.models.db.TradeDetails;
import com.npatil.moneymachine.services.TradeService;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikhil.p on 19/06/16.
 */
@Slf4j
public class TradeServiceImpl implements TradeService {

    private TradeDetailsDao tradeDetailsDao;

    @Inject
    public TradeServiceImpl(TradeDetailsDao tradeDetailsDao) {
        this.tradeDetailsDao = tradeDetailsDao;
    }

    @Override
    public void processTradeTransaction(StockTransactionRequest stockTransactionRequest, TransactionType transactionType) throws Exception {
        Double totalTradeAmount;
        if (transactionType == TransactionType.BUY) {
            totalTradeAmount = (stockTransactionRequest.getPricePerUnit()* stockTransactionRequest.getQuantity())
                    + stockTransactionRequest.getBrokerage();
        } else {
            totalTradeAmount = (stockTransactionRequest.getPricePerUnit()* stockTransactionRequest.getQuantity())
                    - stockTransactionRequest.getBrokerage();
        }
        totalTradeAmount = Math.round(totalTradeAmount*100)/100D;
        TradeDetails tradeDetails = TradeDetails.builder()
                .stockCode(stockTransactionRequest.getStockCode())
                .quantity(stockTransactionRequest.getQuantity())
                .pricePerUnit(stockTransactionRequest.getPricePerUnit())
                .brokerage(stockTransactionRequest.getBrokerage())
                .transactionDate(stockTransactionRequest.getTransactionDate())
                .transactionType(transactionType)
                .total(totalTradeAmount)
                .build();
        tradeDetailsDao.save(tradeDetails);
    }

    @Override
    public void processBulkTradeTransaction(List<StockTransactionRequest> stockTransactionRequests) throws Exception {
        for (StockTransactionRequest stockTransactionRequest : stockTransactionRequests) {
            processTradeTransaction(stockTransactionRequest,stockTransactionRequest.getTransactionType());
        }
    }

    @Override
    public void processBulkTradeTransaction(InputStream uploadedInputStream) throws Exception {
        CsvReader reader = new CsvReader(new InputStreamReader(uploadedInputStream));
        reader.readHeaders();
        List<StockTransactionRequest> stockTransactionRequests = new ArrayList<>();
        log.info("Reading csv file");
        while (reader.readRecord()) {
            StockTransactionRequest stockTransactionRequest = StockTransactionRequest.builder()
                    .stockCode(reader.get("stock_code"))
                    .transactionDate(new SimpleDateFormat("dd/MM/yyyy").parse(reader.get("date")))
                    .transactionType(TransactionType.valueOf(reader.get("transaction_type").toUpperCase()))
                    .brokerage(Double.parseDouble(reader.get("brokerage")))
                    .pricePerUnit(Double.parseDouble(reader.get("price_per_unit")))
                    .quantity(Math.abs(Long.parseLong(reader.get("quantity"))))
                    .build();
            stockTransactionRequests.add(stockTransactionRequest);
        }
        processBulkTradeTransaction(stockTransactionRequests);
    }
}
