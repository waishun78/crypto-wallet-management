package com.appbackend.appbackend.controller;

import com.appbackend.appbackend.exception.ResourceNotFoundException;
import com.appbackend.appbackend.model.Asset;
import com.appbackend.appbackend.model.Transaction;
import com.appbackend.appbackend.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api/v1/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping
    public List<Transaction> getFilteredTransactionsByUsername(@RequestParam(value="username", required = false) String username){
        List<Transaction> fullList = transactionRepository.findAll();
        if (username == null){
            return fullList;
        }
        List<Transaction> filteredList = new ArrayList<>();
        for (Transaction t : fullList){
            if (t.getAccount().getUsername().equalsIgnoreCase(username)){
                filteredList.add(t);
            }
        }
        return filteredList;
    }

    // create transaction REST API
    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction){
        return transactionRepository.save(transaction);
    }

    @DeleteMapping
    public ResponseEntity<Collection<Transaction>> deleteByAccount(@RequestParam(value="username", required = false) String username){

        List<Transaction> fullList = transactionRepository.findAll();
        if (username == null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        List<Asset> filteredList = new ArrayList<>();
        for (Transaction t : fullList){
            if (t.getAccount().getUsername().equalsIgnoreCase(username)){
                transactionRepository.delete(t);
            }
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //get transaction by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id){
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Transaction %d does not exist","id")));
        return ResponseEntity.ok(transaction);
    }

    //update transaction REST API
    @PutMapping("{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transactionDetails){
        Transaction transactionToUpdate = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Transaction %d does not exist","id")));
        if (transactionDetails.getQuantityTransacted() != null) {
            transactionToUpdate.setQuantityTransacted(transactionDetails.getQuantityTransacted());
        }
        if (transactionDetails.getCryptoId() != null){
            transactionToUpdate.setCryptoId(transactionDetails.getCryptoId());
        }
        if (transactionDetails.getCryptoName() != null){
            transactionToUpdate.setCryptoName(transactionDetails.getCryptoName());
        }
        if (transactionDetails.getExchangeRate() != null){
            transactionToUpdate.setExchangeRate(transactionDetails.getExchangeRate());
        }
        transactionRepository.save(transactionToUpdate);

        return ResponseEntity.ok(transactionToUpdate);
    }

    // delete transaction REST API
    @DeleteMapping("{id}")
    public ResponseEntity<Transaction> deleteTransaction(@PathVariable Long id){
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Employee %d does not exist","id")));

        transactionRepository.delete(transaction);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    };
}
