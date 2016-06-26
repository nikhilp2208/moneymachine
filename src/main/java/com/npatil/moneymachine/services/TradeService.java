package com.npatil.moneymachine.services;

import com.npatil.moneymachine.enums.TransactionType;
import com.npatil.moneymachine.models.StockTransactionRequest;

import java.io.InputStream;
import java.util.List;

/**
 * Created by nikhil.p on 19/06/16.
 */
public interface TradeService {
    void processTradeTransaction(StockTransactionRequest stockTransactionRequest, TransactionType buy) throws Exception;

    void processBulkTradeTransaction(List<StockTransactionRequest> stockTransactionRequests) throws Exception;

    void processBulkTradeTransaction(InputStream uploadedInputStream) throws Exception;
}
