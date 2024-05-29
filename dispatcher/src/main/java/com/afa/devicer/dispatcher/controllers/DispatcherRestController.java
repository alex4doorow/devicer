package com.afa.devicer.dispatcher.controllers;

import com.afa.devicer.core.errors.CoreException;
import com.afa.devicer.core.rest.controllers.BaseRestController;
import com.afa.devicer.core.rest.dto.DtoOrderMessage;
import com.afa.devicer.core.services.JsonMapper;
import com.afa.devicer.dispatcher.services.DispatcherKafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/dispatcher/v1")
@RestController
@Slf4j
public class DispatcherRestController extends BaseRestController {

    @Autowired
    private JsonMapper jsonMapper;

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
        kafkaProducerService.sendMessage("dispatcher.orders", message);

        String responseMessage = "{}";
        return responseMessage;
    }


    @PostMapping("/orders/add")
    public String addOrder(@RequestBody String message) {

        String responseMessage = "";
        try {
            DtoOrderMessage dtoOrderMessage = jsonMapper.fromJSON(message, DtoOrderMessage.class);

            dtoOrderMessage.getState().setAction("CREATE");
            dtoOrderMessage.getState().setStatus("PENDING");
            responseMessage = jsonMapper.toJSON(dtoOrderMessage, true);

            kafkaProducerService.sendMessage("dispatcher.orders", responseMessage);
        } catch (CoreException e) {
            log.error("", e);
        }
        return responseMessage;
    }
}
