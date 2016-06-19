package com.npatil.moneymachine.dao.impl;

import com.npatil.moneymachine.dao.TradeDetailsDao;
import com.npatil.moneymachine.models.db.TradeDetails;
import io.dropwizard.hibernate.AbstractDAO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;

/**
 * Created by nikhil.p on 19/06/16.
 */
@Slf4j
public class TradeDetailsDaoImpl extends AbstractDAO<TradeDetails> implements TradeDetailsDao {
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public TradeDetailsDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void save(TradeDetails tradeDetails) throws Exception {
        log.info("Saving the trade details {}", tradeDetails);
        currentSession().merge(tradeDetails);
    }

    @Override
    public TradeDetails findById(long id) throws Exception {
        return get(id);
    }
}
