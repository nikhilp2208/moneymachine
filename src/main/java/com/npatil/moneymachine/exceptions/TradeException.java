package com.npatil.moneymachine.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

/**
 * Created by nikhil.p on 20/06/16.
 */
public class TradeException extends WebApplicationException {
    private List<String> errors;

    public TradeException(String... errors) {
        this(Arrays.asList(errors));
    }

    public TradeException(List<String> errors) {
        super(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new GenericEntity<List<String>>(errors) {
                }).build());
        this.errors = errors;
    }
}
