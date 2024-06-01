package com.afa.devicer.web.controllers;

import com.afa.devicer.core.errors.CoreException;
import com.afa.devicer.core.model.types.OrderAmountTypes;
import com.afa.devicer.core.model.types.ReportPeriodTypes;
import com.afa.devicer.core.rest.dto.DtoOrder;
import com.afa.devicer.core.rest.dto.DtoOrderMessage;
import com.afa.devicer.core.services.JsonMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/web/v1/orders")
@Slf4j
public class OrdersWebController extends BaseWebController {

    @Autowired
    private JsonMapper jsonMapper;
    private String serviceDispatcherUrl;

    private WebClient webClient;

    @PostConstruct
    public void init() {

        final int size = 16 * 1024 * 1024;
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();
        serviceDispatcherUrl = environment.getProperty("url.service.dispatcher");
        webClient = WebClient.builder()
                .baseUrl(serviceDispatcherUrl)
                .exchangeStrategies(strategies)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                //.defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer yourAccessToken")
                //.defaultHeader(HttpHeaders.HOST, "api.cdek.ru")
                //.defaultHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br")
                //.defaultHeader(HttpHeaders.CONNECTION, "keep-alive")
                //.defaultHeader(HttpHeaders.USER_AGENT, "PostmanRuntime/7.29.2")
                .build();

    }

    @GetMapping("/")
    public String findOrdersByRequest(Model model) {
        log.info("[START] {} request", "FIND_ORDERS_BY_REQUEST");
/*
        OrderConditions orderConditions = configService.loadOrderConditions(getCurrentUser());

        Collection<Order> orders = orderService.findAll(orderConditions);
        Collection<DtoOrder> dtoOrders = outDtoOrderConverter.convertTo(orders);

        Map<OrderAmountTypes, BigDecimal> totalAmounts = orderService.calcTotalOrdersAmountsByConditions(orders,
                orderConditions.getPeriod());

 */
        BigDecimal billAmount = BigDecimal.ZERO;
        BigDecimal supplierAmount = BigDecimal.ZERO;
        BigDecimal marginWithoutAdvertAmount = BigDecimal.ZERO;

        Map<OrderAmountTypes, BigDecimal> totalAmounts = new HashMap<>();
        totalAmounts.put(OrderAmountTypes.BILL, billAmount);
        totalAmounts.put(OrderAmountTypes.SUPPLIER, supplierAmount);
        totalAmounts.put(OrderAmountTypes.ADVERT_BUDGET, BigDecimal.ZERO);
        totalAmounts.put(OrderAmountTypes.MARGIN, marginWithoutAdvertAmount);
        totalAmounts.put(OrderAmountTypes.POSTPAY, BigDecimal.ZERO);
        totalAmounts.put(OrderAmountTypes.COUNT_REAL_ORDERS, BigDecimal.ZERO);

        List<DtoOrder> dtoOrders = new ArrayList<>();

        populateDefaultModel(model);
        model.addAttribute("orders", dtoOrders);
        model.addAttribute("totalAmounts", totalAmounts);
        model.addAttribute("reportPeriodType", ReportPeriodTypes.CURRENT_MONTH);
        model.addAttribute("listType", "orders");
        return "orders/list";
    }

    @GetMapping("/add/{listType}")
    public String addOrder(@PathVariable("listType") String listType, Model model) throws CoreException {
        log.info("[START] {} request", "ORDER_ADD");



        DtoOrder dtoOrder = new DtoOrder();
        dtoOrder.setOrderDate(LocalDate.of(2024, 5, 31));
        /*
        DtoCustomer customer = new DtoCustomer(CustomerTypes.CUSTOMER);
        DtoPerson person = new DtoPerson();
        customer.setPerson(person);
        dtoOrder.setCustomer(customer);
        dtoOrder.setOrderNo(orderService.nextOrderNo());
        dtoOrder.setOrderDate(LocalDate.now());
        //dtoOrder.setProductCategory(wikiProductService.getCategoryById(0L));
        dtoOrder.getItems().add(new DtoOrderItem());
        */
        populateDefaultModel(model);
        String orderHeader = getMessageSource().getMessage("order.form.new.headers.h1", null, LocaleContextHolder.getLocale());
        String customerHeader = getMessageSource().getMessage("order.form.new.headers.h2", null, LocaleContextHolder.getLocale());

        model.addAttribute("orderHeader", orderHeader);
        model.addAttribute("customerHeader", customerHeader);

        model.addAttribute("order", dtoOrder);
        model.addAttribute("listType", listType);
        return "orders/edit";
    }

    @PostMapping("/add")
    public String saveAddOrder(Model model, @ModelAttribute("order") @Validated DtoOrder dtoOrder) throws CoreException {
        log.info("[START] {} request", "ORDER_ADD_SAVE");

        try {
            DtoOrderMessage request = DtoOrderMessage.builder().order(dtoOrder).build();
            /*
            DtoOrderMessage responseOrderMessage = webClient.post()
                    .uri(new URI(serviceDispatcherUrl + "/rest/v1/orders/add"))
                    //.header(HttpHeaders.AUTHORIZATION, access.getSecret())
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(DtoOrderMessage.class)
                    .log()
                    .block();
            log.debug("result: {}", responseOrderMessage);
            */
        } catch (Exception e) {
            log.error("result:", e);
        }
        return "redirect:/web/v1/orders/";
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

    @Override
    protected void populateDefaultModel(Model model) {
        super.populateDefaultModel(model);
    }



    @GetMapping("/demo/orders/add")
    public String showForm(Model model) {
        DtoOrder order = new DtoOrder();

        model.addAttribute("order", order);

        List<String> listProfession = Arrays.asList("Developer", "Tester", "Architect");
        model.addAttribute("listProfession", listProfession);

        return "demo/order_demo";
    }

    @PostMapping("/demo/orders/add")
    public String submitForm(@ModelAttribute("order") DtoOrder order) {
        System.out.println(order);
        return "demo/index";
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
}


