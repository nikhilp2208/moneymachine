package com.npatil.moneymachine.dao;

import com.npatil.moneymachine.models.db.TradeDetails;

/**
 * Created by nikhil.p on 19/06/16.
 */
public interface TradeDetailsDao {

    void save(TradeDetails tradeDetails) throws Exception;

    TradeDetails findById(long id) throws Exception;
}
