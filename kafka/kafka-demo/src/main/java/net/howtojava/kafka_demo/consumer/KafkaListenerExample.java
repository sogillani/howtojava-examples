package net.howtojava.kafka_demo.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerExample {
    private static final Logger log = LoggerFactory.getLogger(KafkaListenerExample.class);

    @KafkaListener(topics = "test-topic", groupId = "group1")
    void listener(String data) {
        log.info("Received message [{}] in group1", data);
    }
}