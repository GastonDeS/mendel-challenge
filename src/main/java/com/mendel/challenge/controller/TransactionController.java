package com.mendel.challenge.controller;

import com.mendel.challenge.dto.TransactionDto;
import com.mendel.challenge.exception.TransactionException;
import com.mendel.challenge.service.TransactionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Log4j2
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PutMapping("/{transactionId}")
    public ResponseEntity<?> createTransaction(
        @PathVariable Long transactionId,
        @RequestBody TransactionDto transactionDto
    ) {
        if (transactionId < 0) {
            throw new TransactionException(
                    HttpStatus.BAD_REQUEST, "\"TransactionId\" cannot be negative");
        }
        if (transactionDto.parentId() != null && transactionDto.parentId().equals(transactionId)) {
            throw new TransactionException(
                    HttpStatus.BAD_REQUEST, "\"parent_id\" cannot be equal to \"TransactionId\"");
        }
        try {
            Optional<TransactionDto> savedTransaction = transactionService.createTransaction(transactionId, transactionDto);
            if (savedTransaction.isEmpty()) {
                throw new TransactionException(
                        HttpStatus.INTERNAL_SERVER_ERROR, "Unknown error creating transaction", transactionDto, transactionId);
            }
        } catch (IllegalArgumentException e) {
            throw new TransactionException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

}
