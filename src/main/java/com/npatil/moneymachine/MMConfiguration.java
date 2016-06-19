package com.npatil.moneymachine;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by nikhil.p on 19/06/16.
 */

@Getter
@Setter
public class MMConfiguration extends Configuration {

    @JsonProperty("database")
    private DataSourceFactory dataSourceFactory;
}
