package com.afa.devicer.core.services.datalayers;

import com.afa.devicer.core.bl.OrdersBL;
import com.afa.devicer.core.bl.entities.SEOrder;
import com.afa.devicer.core.bl.entities.sys.SEUser;
import com.afa.devicer.core.errors.CoreException;
import com.afa.devicer.core.integration.Consumer;
import com.afa.devicer.core.integration.Msg;
import com.afa.devicer.core.integration.Payload;
import com.afa.devicer.core.model.conditions.OrderConditions;
import com.afa.devicer.core.model.types.ENFormat;
import com.afa.devicer.core.model.types.ENReportOrdersType;
import com.afa.devicer.core.rest.dto.view.DtoOrdersByConditionsRequestModel;
import com.afa.devicer.core.services.UserService;
import com.afa.devicer.core.services.WikiService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;

public class VOrdersByConditionsConsumer implements Consumer {

    @Autowired
    private OrdersBL ordersBL;
    @Autowired
    private UserService userService;
    @Autowired
    private WikiService wikiService;

    @Override
    public void consume(Payload payload) throws CoreException {
        DtoOrdersByConditionsRequestModel request = (DtoOrdersByConditionsRequestModel) payload.msgIn.getBody();

        SEUser user = userService.getCurrentUser();

        OrderConditions orderConditions = wikiService.getConfig().loadOrderConditions(user.getId());
        Collection<SEOrder> seOrders;
        if (request.getParams().getReportOrdersType() == ENReportOrdersType.TROUBLE) {
            // TODO List<Order> orders = orderService.getOrderDao().listTroubleOrders();
            seOrders = new ArrayList<>();
        } else if (request.getParams().getReportOrdersType() == ENReportOrdersType.BY_CURRENT_FILTER) {
            seOrders = ordersBL.findOrdersByParams(orderConditions.getPeriod().getStart(), orderConditions.getPeriod().getEnd());
        } else if (request.getParams().getReportOrdersType() == ENReportOrdersType.BY_CUSTOM_FILTER) {
            seOrders = new ArrayList<>();
        } else if (request.getParams().getReportOrdersType() == ENReportOrdersType.BY_PERIOD) {
            orderConditions.setPeriodByType(request.getParams().getPeriodType(), request.getParams().getStDtm().toLocalDate());
            wikiService.getConfig().saveOrderConditions(user.getId(), orderConditions);
            seOrders = ordersBL.findOrdersByParams(orderConditions.getPeriod().getStart(), orderConditions.getPeriod().getEnd());
        } else {
            seOrders = new ArrayList<>();
        }

        payload.msgOut = new Msg<>(ENFormat.JSON,
                VOrdersByConditionsProducer.outType,
                payload.msgIn.getRequestId(),
                payload.msgIn.getReciever(),
                payload.msgIn.getSender(),
                null,
                seOrders);
    }
}
