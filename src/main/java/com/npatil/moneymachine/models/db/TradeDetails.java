package com.npatil.moneymachine.models.db;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.jackson.JsonSnakeCase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by nikhil.p on 19/06/16.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonSnakeCase
@Entity
@Table(name = "trade_details",
        indexes = {
                @Index(name = "stock_code_index", columnList = "stock_code"),
                @Index(name = "transaction_date_index", columnList = "transaction_date")
        })

public class TradeDetails implements Serializable {

    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    @Id
    private long id;

    @JsonProperty
    @Column(name = "stock_code", nullable = false)
    private String stockCode;

    @JsonProperty
    @Column(name = "transaction_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss z", timezone = "IST")
    private Date transactionDate;

    @JsonProperty
    @Column(name = "price_per_unit", nullable = false)
    private double pricePerUnit;

    @JsonProperty
    @Column(name = "quantity", nullable = false)
    private long quantity;

    @JsonProperty
    @Column(name = "brokerage", nullable = false)
    private double brokerage;

    @JsonProperty
    @Column(name = "total", nullable = false)
    private double total;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss z", timezone = "IST")
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss z", timezone = "IST")
    private Date updatedAt;

}
