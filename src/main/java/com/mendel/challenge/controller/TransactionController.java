package com.mendel.challenge.controller;

import com.mendel.challenge.dto.TransactionDto;
import com.mendel.challenge.dto.TransitiveSumDto;
import com.mendel.challenge.exception.TransactionException;
import com.mendel.challenge.service.TransactionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Log4j2
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PutMapping("/{transactionId}")
    public ResponseEntity<TransactionDto> createTransaction(
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

    @GetMapping("/types/{type}")
    public ResponseEntity<List<Long>> getTransactionsByType(
        @PathVariable String type
    ) {
        if (type == null || type.isEmpty()) {
            throw new TransactionException(
                    HttpStatus.BAD_REQUEST, "\"type\" cannot be empty");
        }
        List<Long> transactionIds = transactionService.getTransactionsByType(type);
        return ResponseEntity.ok(transactionIds);
    }

    @GetMapping("/sum/{transactionId}")
    public ResponseEntity<TransitiveSumDto> getTransactionsSum(
        @PathVariable Long transactionId
    ) {
        if (transactionId < 0) {
            throw new TransactionException(
                    HttpStatus.BAD_REQUEST, "\"TransactionId\" cannot be negative");
        }
        Double sum = transactionService.getTransitiveTransactionsAmount(transactionId);
        return ResponseEntity.ok(new TransitiveSumDto(sum));
    }
}
