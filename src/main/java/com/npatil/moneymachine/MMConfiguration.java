package com.npatil.moneymachine;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by nikhil.p on 19/06/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MMConfiguration extends Configuration {

    @JsonProperty
    @NonNull
    @NotEmpty
    private String name;

    @JsonProperty("database")
    private DataSourceFactory dataSourceFactory;
}
