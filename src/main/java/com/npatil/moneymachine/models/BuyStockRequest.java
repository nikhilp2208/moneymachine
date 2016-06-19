package com.npatil.moneymachine.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by nikhil.p on 19/06/16.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class BuyStockRequest {

    @JsonProperty
    private String stockCode;

    @JsonProperty
    private Date transactionDate;

    @JsonProperty
    private double pricePerUnit;

    @JsonProperty
    private long quantity;

    @JsonProperty
    private double brokerage;
}
