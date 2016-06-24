package com.npatil.moneymachine;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.npatil.moneymachine.dao.TradeDetailsDao;
import com.npatil.moneymachine.dao.impl.TradeDetailsDaoImpl;
import com.npatil.moneymachine.services.TradeService;
import com.npatil.moneymachine.services.impl.TradeServiceImpl;
import io.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SessionFactory;

/**
 * Created by nikhil.p on 19/06/16.
 */
public class MMModule extends AbstractModule {
    private HibernateBundle<MMConfiguration> hibernateBundle;

    public MMModule(HibernateBundle<MMConfiguration> hibernateBundle) {
        this.hibernateBundle = hibernateBundle;
    }

    @Override
    protected void configure() {
        bind(TradeService.class).to(TradeServiceImpl.class);
        bind(TradeDetailsDao.class).to(TradeDetailsDaoImpl.class);
    }

    @Singleton
    @Provides
    public SessionFactory provideSessionFactory() {
        return hibernateBundle.getSessionFactory();
    }
}
