package com.npatil.moneymachine;

import com.google.inject.AbstractModule;
import io.dropwizard.hibernate.HibernateBundle;

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
    }
}
