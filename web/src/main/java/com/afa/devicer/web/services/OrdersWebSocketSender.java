package com.afa.devicer.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrdersWebSocketSender {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendOrderUpdate(String message) {
        messagingTemplate.convertAndSend("/topic/orderUpdates", message);
    }

}

/* javascript
var socket = new SockJS('/websocket');
var stompClient = Stomp.over(socket);

stompClient.connect({}, function (frame) {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/orderUpdates', function (orderUpdate) {
        // Обновление списка заказов в вашем клиентском интерфейсе Thymeleaf
        // Например, обновление таблицы заказов или другого соответствующего компонента
        console.log('Received order update: ' + orderUpdate.body);
    });
});

*/
