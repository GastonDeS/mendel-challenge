package com.mendel.challenge;

import com.mendel.challenge.dto.TransactionDto;
import com.mendel.challenge.mapper.TransactionMapper;
import com.mendel.challenge.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MapperTest {


    @Test
    public void transactionToTransactionDto() {
        Transaction transaction = new Transaction(1L, 100.0, "food");

        TransactionDto transactionDto = TransactionMapper.toDto(transaction);

        assertEquals(transaction.getAmount(), transactionDto.amount());
        assertEquals(transaction.getType(), transactionDto.type());
        assertEquals(transaction.getParentId(), transactionDto.parentId());
    }

    @Test
    public void transactionDtoToTransaction() {
        TransactionDto transactionDto = new TransactionDto(100.0, "food", 1L);

        Transaction transaction = TransactionMapper.toModel(1L, transactionDto);

        assertEquals(transactionDto.amount(), transaction.getAmount());
        assertEquals(transactionDto.type(), transaction.getType());
        assertEquals(transactionDto.parentId(), transaction.getParentId());
    }
}
