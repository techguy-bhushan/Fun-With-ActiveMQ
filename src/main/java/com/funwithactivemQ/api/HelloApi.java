package com.funwithactivemQ.api;

import com.funwithactivemQ.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloApi {
    @Autowired
    private Producer producer;

    @RequestMapping("/{message}")
    public ResponseEntity<?> greeting(@PathVariable("message") String message) {
        producer.send(message);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
