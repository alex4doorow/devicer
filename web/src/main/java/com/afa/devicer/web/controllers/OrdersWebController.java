package com.afa.devicer.web.controllers;

import com.afa.devicer.core.bl.entities.sys.SEUser;
import com.afa.devicer.core.errors.CoreException;
import com.afa.devicer.core.model.conditions.OrderConditions;
import com.afa.devicer.core.model.types.ENOrderAmountTypes;
import com.afa.devicer.core.model.types.ENPeriodTypes;
import com.afa.devicer.core.model.types.ENReportOrdersType;
import com.afa.devicer.core.rest.dto.DtoOrder;
import com.afa.devicer.core.rest.dto.DtoOrderMessage;
import com.afa.devicer.core.rest.dto.view.DtoOrdersByConditionsParams;
import com.afa.devicer.core.rest.dto.view.DtoOrdersByConditionsRequestModel;
import com.afa.devicer.core.rest.dto.view.DtoOrdersByConditionsResponseModel;
import com.afa.devicer.core.services.JsonMapper;
import com.afa.devicer.core.utils.DateTimeUtils;
import com.afa.devicer.core.utils.Defaults;
import com.afa.devicer.web.dto.DtoWebOrder;
import com.afa.devicer.web.dto.view.DtoWebOrdersFilter;
import com.afa.devicer.web.services.converters.out.OutDtoWebOrdersConverter;
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
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @Autowired
    private OutDtoWebOrdersConverter webDtoOrdersConverter;

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
    public String findOrdersByCurrentRequest(Model model) {

        log.debug("showAllOrders()");

        DtoOrdersByConditionsRequestModel request = DtoOrdersByConditionsRequestModel.builder()
                .params(DtoOrdersByConditionsParams.builder()
                        .reportOrdersType(ENReportOrdersType.BY_CURRENT_FILTER)
                        .build())
                .build();
        log.info("[START] {} request: {}", "FIND_ORDERS_BY_CURRENT_FILTER_REQUEST", request);

        DtoOrdersByConditionsResponseModel responseModel = ordersByRequest(request);

        assert responseModel != null;

        Collection<DtoWebOrder> dtoWebOrders = webDtoOrdersConverter.convertTo(responseModel.getOrders());
        Map<ENOrderAmountTypes, BigDecimal> totalAmounts = responseModel.getTotalAmounts();

        populateDefaultModel(model);
        populateDefaultModelForOrders(model, dtoWebOrders, totalAmounts, ENPeriodTypes.CURRENT_MONTH, "orders");
        return "orders/list";
    }

    protected void populateDefaultModelForOrders(Model model,
                                                 Collection<DtoWebOrder> dtoWebOrders,
                                                 Map<ENOrderAmountTypes, BigDecimal> totalAmounts,
                                                 ENPeriodTypes reportPeriodType,
                                                 String listType) {
        model.addAttribute("orders", dtoWebOrders);
        model.addAttribute("totalAmounts", totalAmounts);
        model.addAttribute("totalAmountPostpayCompany", totalAmounts.get(ENOrderAmountTypes.POSTPAY_COMPANY));
        model.addAttribute("totalAmountPostpayCdek", totalAmounts.get(ENOrderAmountTypes.POSTPAY_CDEK));
        model.addAttribute("totalAmountPostpayPost", totalAmounts.get(ENOrderAmountTypes.POSTPAY_POST));
        model.addAttribute("totalAmountPostpayOzonMarket", totalAmounts.get(ENOrderAmountTypes.POSTPAY_OZON_MARKET));
        model.addAttribute("totalAmountPostpayYandexMarket", totalAmounts.get(ENOrderAmountTypes.POSTPAY_YANDEX_MARKET));
        model.addAttribute("totalAmountPostpayYandexGo", totalAmounts.get(ENOrderAmountTypes.POSTPAY_YANDEX_GO));

        model.addAttribute("reportPeriodType", reportPeriodType);
        model.addAttribute("listType", listType);
    }

    private DtoOrdersByConditionsResponseModel ordersByRequest(DtoOrdersByConditionsRequestModel request) {

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
        return responseModel;
    }

    @GetMapping(value = "/orders/trouble-load")
    public String findTroubleOrders(Model model) {

        log.debug("showTroubleOrders()");
        DtoOrdersByConditionsRequestModel request = DtoOrdersByConditionsRequestModel.builder()
                .params(DtoOrdersByConditionsParams.builder()
                        .reportOrdersType(ENReportOrdersType.TROUBLE)
                        .build())
                .build();
        log.info("[START] {} request: {}", "FIND_TROUBLE_ORDERS_REQUEST", request);

        DtoOrdersByConditionsResponseModel responseModel = ordersByRequest(request);

        assert responseModel != null;

        Collection<DtoWebOrder> dtoWebOrders = webDtoOrdersConverter.convertTo(responseModel.getOrders());
        Map<ENOrderAmountTypes, BigDecimal> totalAmounts = responseModel.getTotalAmounts();

        populateDefaultModel(model);
        populateDefaultModelForOrders(model, dtoWebOrders, totalAmounts, ENPeriodTypes.CURRENT_MONTH, "trouble-load");

        return "orders/list";
    }

    @GetMapping(value = "/conditions/period/{period}")
    public String findOrdersByPeriod(@PathVariable("period") String period, Model model) {

        log.debug("showOrdersByPeriod()");
        DtoOrdersByConditionsRequestModel request = DtoOrdersByConditionsRequestModel.builder()
                .params(DtoOrdersByConditionsParams.builder()
                        .reportOrdersType(ENReportOrdersType.BY_PERIOD)
                        .build())
                .build();
        log.info("[START] {} request: {}", "FIND_ORDERS_BY_PERIOD_REQUEST", request);

        ENPeriodTypes reportPeriodType = ENPeriodTypes.getValueByCode(period);

        DtoOrdersByConditionsResponseModel responseModel = ordersByRequest(request);

        assert responseModel != null;

        Collection<DtoWebOrder> dtoWebOrders = webDtoOrdersConverter.convertTo(responseModel.getOrders());
        Map<ENOrderAmountTypes, BigDecimal> totalAmounts = responseModel.getTotalAmounts();

        populateDefaultModel(model);
        populateDefaultModelForOrders(model, dtoWebOrders, totalAmounts, reportPeriodType, "orders");

        return "orders/list";
    }

    @GetMapping("/conditions/filter")
    public String findOrdersByConditionsRequest(Model model) {

        log.debug("showOrdersByConditions()");
        DtoOrdersByConditionsRequestModel request = DtoOrdersByConditionsRequestModel.builder()
                .params(DtoOrdersByConditionsParams.builder()
                        .reportOrdersType(ENReportOrdersType.BY_CUSTOM_FILTER)
                        .build())
                .build();
        log.info("[START] {} request: {}", "FIND_ORDERS_BY_CUSTOM_FILTER_REQUEST", request);
/*
        DtoOrdersByConditionsResponseModel responseModel = ordersByRequest(request);

        assert responseModel != null;

        Collection<DtoWebOrder> dtoWebOrders = webDtoOrdersConverter.convertTo(responseModel.getOrders());
        Map<ENOrderAmountTypes, BigDecimal> totalAmounts = responseModel.getTotalAmounts();
*/
        populateDefaultModel(model);
//        populateDefaultModelForOrders(model, dtoWebOrders, totalAmounts, ENPeriodTypes.CURRENT_MONTH, "orders");
//        OrderConditions orderConditionsForm = wikiService.getConfig().loadOrderConditions(getUserIdByPrincipal());
        //        model.addAttribute("orderConditionsForm", orderConditionsForm);
        model.addAttribute("reportPeriodTypes", ENPeriodTypes.getListOrderValues());
        model.addAttribute("reportPeriodMonths", DateTimeUtils.getMonths());

        return "orders/order-conditions-form";
    }

    @PostMapping(value = "/conditions/filter/exec")
    public String execOrdersByConditions(@ModelAttribute("orderConditionsForm") DtoWebOrdersFilter orderConditionsForm,
                                         Model model, final RedirectAttributes redirectAttributes) {

        log.debug("execOrdersByConditions():{}", orderConditionsForm);
        DtoOrdersByConditionsRequestModel request = DtoOrdersByConditionsRequestModel.builder()
                .params(DtoOrdersByConditionsParams.builder()
                        .reportOrdersType(ENReportOrdersType.BY_CUSTOM_FILTER)
                        .build())
                .build();
        log.info("[START] {} request: {}", "FIND_ORDERS_BY_CUSTOM_FILTER_REQUEST", request);

        DtoOrdersByConditionsResponseModel responseModel = ordersByRequest(request);

        assert responseModel != null;

        Collection<DtoWebOrder> dtoWebOrders = webDtoOrdersConverter.convertTo(responseModel.getOrders());
        Map<ENOrderAmountTypes, BigDecimal> totalAmounts = responseModel.getTotalAmounts();

        populateDefaultModel(model);
        populateDefaultModelForOrders(model, dtoWebOrders, totalAmounts, ENPeriodTypes.CURRENT_MONTH, "orders");

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


