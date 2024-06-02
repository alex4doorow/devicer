package com.afa.devicer.web.controllers;

import com.afa.devicer.core.errors.CoreException;
import com.afa.devicer.core.model.types.ENOrderAmountTypes;
import com.afa.devicer.core.model.types.ENReportPeriodTypes;
import com.afa.devicer.core.rest.dto.DtoOrder;
import com.afa.devicer.core.rest.dto.DtoOrderMessage;
import com.afa.devicer.core.rest.dto.view.DtoOrdersByConditionsParams;
import com.afa.devicer.core.rest.dto.view.DtoOrdersByConditionsRequestModel;
import com.afa.devicer.core.rest.dto.view.DtoOrdersByConditionsResponseModel;
import com.afa.devicer.core.services.JsonMapper;
import com.afa.devicer.core.utils.Defaults;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        serviceDispatcherUrl = environment.getProperty("url.service.dispatcher") + "/rest/v1/";
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
        DtoOrdersByConditionsRequestModel request = DtoOrdersByConditionsRequestModel.builder()
                .params(DtoOrdersByConditionsParams
                        .builder()
                        .stDtm(LocalDateTime.now())
                        .endDtm(LocalDateTime.now())
                        .build())
                .build();
        log.info("[START] {} request: {}", "FIND_ORDERS_BY_REQUEST", request);

/*
        OrderConditions orderConditions = configService.loadOrderConditions(getCurrentUser());

        Collection<Order> orders = orderService.findAll(orderConditions);
        Collection<DtoOrder> dtoOrders = outDtoOrderConverter.convertTo(orders);

        Map<OrderAmountTypes, BigDecimal> totalAmounts = orderService.calcTotalOrdersAmountsByConditions(orders,
                orderConditions.getPeriod());

 */
        DtoOrdersByConditionsResponseModel responseModel;
        try {
            responseModel = webClient.post()
                    .uri(new URI(serviceDispatcherUrl + "orders/by-conditions"))
                    .header(Defaults.X_Request_ID, UUID.randomUUID().toString())
                    .header(Defaults.X_Client_ID, "user")
                    .header(Defaults.X_Token, "token")
                    .body(BodyInserters.fromValue(request))
                    .retrieve()
                    .bodyToMono(DtoOrdersByConditionsResponseModel.class)
                    .block();
            log.debug("result: {}", responseModel);
        } catch (Exception e) {
            log.error("result:", e);
            responseModel = DtoOrdersByConditionsResponseModel.builder()
                    .orders(new ArrayList<>())
                    .build();
        }
        BigDecimal billAmount = BigDecimal.ZERO;
        BigDecimal supplierAmount = BigDecimal.ZERO;
        BigDecimal marginWithoutAdvertAmount = BigDecimal.ZERO;

        Map<ENOrderAmountTypes, BigDecimal> totalAmounts = new HashMap<>();
        totalAmounts.put(ENOrderAmountTypes.BILL, billAmount);
        totalAmounts.put(ENOrderAmountTypes.SUPPLIER, supplierAmount);
        totalAmounts.put(ENOrderAmountTypes.ADVERT_BUDGET, BigDecimal.ZERO);
        totalAmounts.put(ENOrderAmountTypes.MARGIN, marginWithoutAdvertAmount);
        totalAmounts.put(ENOrderAmountTypes.POSTPAY, BigDecimal.ZERO);
        totalAmounts.put(ENOrderAmountTypes.COUNT_REAL_ORDERS, BigDecimal.ZERO);

        totalAmounts.put(ENOrderAmountTypes.POSTPAY_COMPANY, BigDecimal.ZERO);
        totalAmounts.put(ENOrderAmountTypes.POSTPAY_CDEK, BigDecimal.ZERO);
        totalAmounts.put(ENOrderAmountTypes.POSTPAY_POST, BigDecimal.ZERO);
        totalAmounts.put(ENOrderAmountTypes.POSTPAY_OZON_MARKET, BigDecimal.ZERO);
        totalAmounts.put(ENOrderAmountTypes.POSTPAY_YANDEX_MARKET, BigDecimal.ZERO);
        totalAmounts.put(ENOrderAmountTypes.POSTPAY_YANDEX_GO, BigDecimal.ZERO);

        assert responseModel != null;
        Collection<DtoOrder> dtoOrders = responseModel.getOrders();

        populateDefaultModel(model);
        model.addAttribute("orders", dtoOrders);
        model.addAttribute("totalAmounts", totalAmounts);
        model.addAttribute("totalAmountPostpayCompany", totalAmounts.get(ENOrderAmountTypes.POSTPAY_COMPANY));
        model.addAttribute("totalAmountPostpayCdek", totalAmounts.get(ENOrderAmountTypes.POSTPAY_CDEK));
        model.addAttribute("totalAmountPostpayPost", totalAmounts.get(ENOrderAmountTypes.POSTPAY_POST));
        model.addAttribute("totalAmountPostpayOzonMarket", totalAmounts.get(ENOrderAmountTypes.POSTPAY_OZON_MARKET));
        model.addAttribute("totalAmountPostpayYandexMarket", totalAmounts.get(ENOrderAmountTypes.POSTPAY_YANDEX_MARKET));
        model.addAttribute("totalAmountPostpayYandexGo", totalAmounts.get(ENOrderAmountTypes.POSTPAY_YANDEX_GO));

        model.addAttribute("reportPeriodType", ENReportPeriodTypes.CURRENT_MONTH);
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
                    .uri(new URI(serviceDispatcherUrl + "orders/add"))
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


