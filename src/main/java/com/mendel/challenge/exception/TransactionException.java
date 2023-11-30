package com.mendel.challenge.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

@Log4j2
public class TransactionException extends ResponseStatusException {

    public TransactionException(HttpStatusCode status, String reason) {
        super(status, reason);
        if (status.is5xxServerError()) {
            log.error(reason);
        } else {
            log.info(reason);
        }
    }

    public TransactionException(HttpStatusCode status, String reason, Object... args) {
        super(status, String.format(reason, args));
        if (status.is5xxServerError()) {
            log.error(reason);
            log.error(Arrays.toString(args));
        } else {
            log.info(reason);
            log.info(args);
        }
    }
}
