package com.npatil.moneymachine.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.npatil.moneymachine.enums.TransactionType;
import lombok.*;

import java.util.Date;

/**
 * Created by nikhil.p on 19/06/16.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class StockTransactionRequest {

    @JsonProperty
    private String stockCode;

    @JsonProperty
    private Date transactionDate;

    @JsonProperty
    private TransactionType transactionType;

    @JsonProperty
    private double pricePerUnit;

    @JsonProperty
    private long quantity;

    @JsonProperty
    private double brokerage;
}
