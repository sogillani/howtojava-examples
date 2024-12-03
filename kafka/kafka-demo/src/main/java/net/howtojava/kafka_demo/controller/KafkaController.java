package net.howtojava.kafka_demo.controller;

import net.howtojava.kafka_demo.producer.KafkaSender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/message")
public class KafkaController {

    private final KafkaSender kafkaSender;

    public KafkaController(KafkaSender kafkaSender) {
        this.kafkaSender = kafkaSender;
    }

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody String message) {
        kafkaSender.sendMessage(message, "test-topic");
        return ResponseEntity.ok("Ok");
    }
}
