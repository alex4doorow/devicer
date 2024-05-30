package com.afa.devicer.web.controllers;

import com.afa.devicer.core.rest.dto.DtoOrder;
import com.afa.devicer.core.rest.dto.DtoOrderMessage;
import com.afa.devicer.core.services.JsonMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.afa.devicer.core.errors.CoreException;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

@Controller
@RequestMapping("/web/v1/orders")
@Slf4j
public class OrdersWebController extends BaseController {

    @Autowired
    private JsonMapper jsonMapper;
    @Autowired
    private Environment environment;
    private String serviceDispatcherUrl;

    private WebClient webClient;

    @PostConstruct
    public void init() {

        final int size = 16 * 1024 * 1024;
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();
        webClient = WebClient.builder()
                .baseUrl("http://localhost:9192")
                .exchangeStrategies(strategies)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                //.defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer yourAccessToken")
                //.defaultHeader(HttpHeaders.HOST, "api.cdek.ru")
                //.defaultHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br")
                //.defaultHeader(HttpHeaders.CONNECTION, "keep-alive")
                //.defaultHeader(HttpHeaders.USER_AGENT, "PostmanRuntime/7.29.2")
                .build();
        serviceDispatcherUrl = environment.getProperty("url.service.dispatcher");
    }

    @GetMapping("/{id}/{listType}")
    public String findById(@PathVariable("id") Long id, @PathVariable("listType") String listType, Model model) {
        /*
        log.info("[START] {} request: {}", "FIND", id);
        Order order = orderService.findById(id);
        DtoOrder dtoOrder = outDtoOrderConverter.convertTo(order);
        model.addAttribute("order", dtoOrder);
        model.addAttribute("listType", listType);
        */
        return "orders/show";
    }

    @GetMapping("/add/{listType}")
    public String addOrder(@PathVariable("listType") String listType, Model model) throws CoreException {
        log.info("[START] {} request", "ADD");

        DtoOrder orderForm = new DtoOrder();
        /*
        DtoCustomer customer = new DtoCustomer(CustomerTypes.CUSTOMER);
        DtoPerson person = new DtoPerson();
        customer.setPerson(person);
        dtoOrder.setCustomer(customer);
        dtoOrder.setOrderNo(orderService.nextOrderNo());
        dtoOrder.setOrderDate(LocalDate.now());
        //dtoOrder.setProductCategory(wikiProductService.getCategoryById(0L));
        dtoOrder.getItems().add(new DtoOrderItem());
        model.addAttribute("order", dtoOrder);
        model.addAttribute("listType", listType);

        */
        model.addAttribute("orderForm", orderForm);
        return "orders/edit";
    }

    @PostMapping("/add")
    public String saveAddOrder(Model model, @ModelAttribute("orderForm") @Validated DtoOrder orderForm) throws CoreException {
        log.info("[START] {} request", "SAVE_ADD");

        try {
            DtoOrderMessage orderMessage = DtoOrderMessage.builder().order(orderForm).build();
            DtoOrderMessage responseOrderMessage = webClient.post()
                    .uri(new URI(serviceDispatcherUrl + "/v1/orders/add"))
                    //.header(HttpHeaders.AUTHORIZATION, access.getSecret())
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(orderMessage)
                    .retrieve()
                    .bodyToMono(DtoOrderMessage.class)
                    .log()
                    .block();
            log.debug("result: {}", responseOrderMessage);
        } catch (Exception e) {
            log.error("result:", e);
        }
        return "orders/edit-test";
    }

    @GetMapping("/{id}/change-status/{listType}")
    public String changeStatusOrder(@PathVariable("id") Long id, @PathVariable("listType") String listType, Model model) throws CoreException {
        log.info("[START] {} request: {}", "CHANGE_STATUS", id);
        /*
        Order order = orderService.findById(id);
        DtoOrder dtoOrder = outDtoOrderConverter.convertTo(order);

        model.addAttribute("order", dtoOrder);
        model.addAttribute("listType", listType);
        populateDefaultModel(model);

        */
        return "orders/change-status";
    }

    @GetMapping("/")
    public String findAll(Model model) {
        log.info("[START] {} request", "FIND_ALL");
/*
        OrderConditions orderConditions = configService.loadOrderConditions(getCurrentUser());

        Collection<Order> orders = orderService.findAll(orderConditions);
        Collection<DtoOrder> dtoOrders = outDtoOrderConverter.convertTo(orders);

        Map<OrderAmountTypes, BigDecimal> totalAmounts = orderService.calcTotalOrdersAmountsByConditions(orders,
                orderConditions.getPeriod());
        populateDefaultModel(model);
        model.addAttribute("orders", dtoOrders);
        model.addAttribute("totalAmounts", totalAmounts);
        model.addAttribute("reportPeriodType", ReportPeriodTypes.CURRENT_MONTH);
        model.addAttribute("listType", "orders");
 */
        return "orders/list";
    }

    @Override
    protected void populateDefaultModel(Model model) {
        super.populateDefaultModel(model);
    }
}
