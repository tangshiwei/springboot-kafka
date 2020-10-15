package com.kafka.controller;

import com.google.gson.Gson;
import com.kafka.config.User;
import com.kafka.constants.KafkaConsts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/msg/send")
    public String saveUser(User user) throws ExecutionException, InterruptedException {
        String json = new Gson().toJson(user);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(KafkaConsts.TOPIC_TEST, json);
        SendResult<String, String> result = future.get();
        Gson gson = new Gson();
        return gson.toJson(result.getProducerRecord());
    }

}
