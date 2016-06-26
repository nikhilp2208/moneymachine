package com.npatil.moneymachine.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.npatil.moneymachine.enums.TransactionType;
import com.npatil.moneymachine.models.StockTransactionRequest;
import com.npatil.moneymachine.services.TradeService;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;
import java.util.List;

/**
 * Created by nikhil.p on 19/06/16.
 */
@Slf4j
@Path("/trade")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class TradeResource {

    private final TradeService tradeService;

    @Inject
    public TradeResource(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @POST
    @UnitOfWork
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/buy")
    public void buyStock(StockTransactionRequest stockTransactionRequest) throws Exception {
        tradeService.processTradeTransaction(stockTransactionRequest, TransactionType.BUY);
    }

    @POST
    @UnitOfWork
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/sell")
    public void sellStock(StockTransactionRequest stockTransactionRequest) throws Exception {
        tradeService.processTradeTransaction(stockTransactionRequest, TransactionType.SELL);
    }

    @POST
    @UnitOfWork
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/bulk_transaction")
    public void bulkTransaction(List<StockTransactionRequest> stockTransactionRequests) throws Exception {
        tradeService.processBulkTradeTransaction(stockTransactionRequests);
    }

    @POST
    @UnitOfWork
    @Timed
    @Path("/csv_upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void bulkTransactionCsv(@FormDataParam("file") InputStream uploadedInputStream) throws Exception {
        tradeService.processBulkTradeTransaction(uploadedInputStream);
    }
}
