package com.npatil.moneymachine.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.npatil.moneymachine.dao.TradeDetailsDao;
import com.npatil.moneymachine.models.db.TradeDetails;
import com.npatil.moneymachine.services.TradeService;
import com.npatil.moneymachine.models.BuyStockRequest;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by nikhil.p on 19/06/16.
 */
@Slf4j
@Path("/trade")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class TradeResource {

    private final TradeService tradeService;
    private TradeDetailsDao tradeDetailsDao;

    @Inject
    public TradeResource(TradeService tradeService, TradeDetailsDao tradeDetailsDao) {
        this.tradeService = tradeService;
        this.tradeDetailsDao = tradeDetailsDao;
    }

    @POST
    @UnitOfWork
    @Timed
    @Path("/buy")
    public void buyStock(BuyStockRequest buyStockRequest) throws Exception {
        TradeDetails tradeDetails = TradeDetails.builder()
                .stockCode(buyStockRequest.getStockCode())
                .quantity(buyStockRequest.getQuantity())
                .pricePerUnit(buyStockRequest.getPricePerUnit())
                .brokerage(buyStockRequest.getBrokerage())
                .transactionDate(buyStockRequest.getTransactionDate())
                .total((buyStockRequest.getPricePerUnit()*buyStockRequest.getQuantity())+buyStockRequest.getBrokerage())
                .build();
        tradeDetailsDao.save(tradeDetails);

    }
}
