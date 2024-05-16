package com.afa.devicer.dispatcher.controllers;

import com.afa.devicer.core.rest.controllers.BaseRestController;
import com.afa.devicer.dispatcher.services.DispatcherKafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/dispatcher/v1")
@RestController
public class DispatcherRestController extends BaseRestController {

/*
    src
├── main
│   ├── java
│   │   └── com
│   │       └── example
│   │           ├── controller      // Контроллеры для взаимодействия с внешним миром
│   │           ├── dto             // DTO объекты
│   │           ├── kafka           // Конфигурация и обработчики Kafka
│   │           ├── saga            // Логика саги и сервисы шагов
│   │           └── service         // Бизнес-логика приложения
│   └── resources
│       └── application.properties  // Конфигурационный файл Spring Boot
└── test
    └── java
        └── com
            └── example
                └── ...
*/

    private DispatcherKafkaProducerService kafkaProducerService;

    @Autowired
    public void setKafkaProducerService(DispatcherKafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/orders/send-message")
    public String sendMessageToKafka(@RequestBody String message) {
        //kafkaProducerService.sendMessage("topicName", message);
        kafkaProducerService.sendMessage("dispatcher.orders", message);
        //kafkaProducerService.sendMessage("dispatcher", message);

        return "Message sent to Kafka: " + message;
    }

    /*
    @PostMapping("/orders/add")
    public String addOrder(@RequestBody String message) {
        //kafkaProducerService.sendMessage("topicName", message);
        kafkaProducerService.sendMessage("orders.dispatcher", message);
        kafkaProducerService.sendMessage("dispatcher", message);

        return "Message sent to Kafka: " + message;
    }
    */
}
