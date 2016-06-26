package com.npatil.moneymachine;

import com.google.inject.Stage;
import com.hubspot.dropwizard.guice.GuiceBundle;
import com.npatil.moneymachine.models.db.TradeDetails;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.ImprovedNamingStrategy;

/**
 * Created by nikhil.p on 19/06/16.
 */
@Slf4j
public class MMAplication extends Application<MMConfiguration> {

    public static void main(String[] args) throws Exception {
        new MMAplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<MMConfiguration> bootstrap) {
        HibernateBundle<MMConfiguration> hibernateBundle = new HibernateBundle<MMConfiguration>(
                TradeDetails.class
        ) {
            @Override
            public DataSourceFactory getDataSourceFactory(MMConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }

            @Override
            protected void configure(Configuration configuration) {
                configuration.setNamingStrategy(new ImprovedNamingStrategy());
                configuration.addPackage("com.npatil.moneymachine.models.db");
            }
        };

        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(GuiceBundle.<MMConfiguration>newBuilder()
                .enableAutoConfig(getClass().getPackage().getName())
                .addModule(new MMModule(hibernateBundle))
                .setConfigClass(MMConfiguration.class)
                .build(Stage.DEVELOPMENT));

    }

    @Override
    public void run(MMConfiguration configuration, Environment environment) throws Exception {
        environment.jersey().register(MultiPartFeature.class);
    }
}
