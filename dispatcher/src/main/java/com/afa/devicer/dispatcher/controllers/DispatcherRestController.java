package com.afa.devicer.dispatcher.controllers;

import com.afa.devicer.core.errors.CoreException;
import com.afa.devicer.core.model.types.ENMessageActions;
import com.afa.devicer.core.model.types.ENMessageStatuses;
import com.afa.devicer.core.rest.controllers.BaseRestController;
import com.afa.devicer.core.rest.dto.DtoOrderMessage;
import com.afa.devicer.core.rest.dto.DtoResponseOrderMessage;
import com.afa.devicer.core.rest.dto.DtoState;
import com.afa.devicer.core.services.JsonMapper;
import com.afa.devicer.dispatcher.controllers.api.OrdersRestApi;
import com.afa.devicer.dispatcher.services.DispatcherKafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest/v1")
@RestController
@Slf4j
public class DispatcherRestController extends BaseRestController implements OrdersRestApi {

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

    @Override
    public String findOrderById(Long id) {
        return null;
    }

    @PostMapping("/orders/add")
    @Override
    public String addOrder(@RequestBody String message) {

        String responseMessage = "";
        try {
            DtoOrderMessage request = jsonMapper.fromJSON(message, DtoOrderMessage.class);

            request.setState(new DtoState(ENMessageActions.CREATE, ENMessageStatuses.PENDING, null));
            DtoResponseOrderMessage response = DtoResponseOrderMessage.builder()
                    .request(request)
                    .build();

            responseMessage = jsonMapper.toJSON(response, CoreException.THROWS);

            kafkaProducerService.sendMessage("dispatcher.orders", responseMessage);
        } catch (CoreException e) {
            log.error("", e);
        }
        return responseMessage;
    }
}
