package com.afa.devicer.core.services.datalayers;

import com.afa.devicer.core.bl.entities.SEOrder;
import com.afa.devicer.core.errors.CoreException;
import com.afa.devicer.core.integration.Msg;
import com.afa.devicer.core.integration.Payload;
import com.afa.devicer.core.integration.Processor;
import com.afa.devicer.core.model.types.ENFormat;
import com.afa.devicer.core.model.types.ENOrderAmountTypes;
import com.afa.devicer.core.model.types.ENOrderStatuses;
import com.afa.devicer.core.rest.dto.DtoOrder;
import com.afa.devicer.core.rest.dto.view.DtoOrdersByConditionsResponseModel;
import com.afa.devicer.core.services.JsonMapper;
import com.afa.devicer.core.services.converters.out.OutDtoOrdersConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class VOrdersByConditionsProcessor implements Processor {

    @Autowired
    private JsonMapper jsonMapper;
    @Autowired
    private OutDtoOrdersConverter outDtoOrdersConverter;

    @Override
    public void process(Payload payload) throws CoreException {

        DtoOrdersByConditionsResponseModel response = convertSEOrdersToResponseModel((Collection<SEOrder>) payload.msgOut.getBody());

        String jsonResponse = jsonMapper.toJSON(response, CoreException.THROWS);

        payload.msgOut = new Msg<>(ENFormat.JSON,
                VOrdersByConditionsProducer.outType,
                payload.msgIn.getRequestId(),
                payload.msgIn.getReciever(),
                payload.msgIn.getSender(),
                payload.msgIn.getSignature(),
                jsonResponse);
    }

    private DtoOrdersByConditionsResponseModel convertSEOrdersToResponseModel(Collection<SEOrder> seOrders) {

        Collection<DtoOrder> dtoOrders = outDtoOrdersConverter.convertTo(seOrders);
        Map<ENOrderAmountTypes, BigDecimal> totalAmounts = calcTotalAmounts(dtoOrders);
        DtoOrdersByConditionsResponseModel result = new DtoOrdersByConditionsResponseModel();
        result.setOrders(dtoOrders);
        result.setTotalAmounts(totalAmounts);

        return result;
    }

    private Map<ENOrderAmountTypes, BigDecimal> calcTotalAmounts(Collection<DtoOrder> dtoOrders) {
        Map<ENOrderAmountTypes, BigDecimal> results = new HashMap<>();

        BigDecimal billAmount = BigDecimal.ZERO;
        BigDecimal supplierAmount = BigDecimal.ZERO;
        BigDecimal marginAmount = BigDecimal.ZERO;
        BigDecimal marginWithoutAdvertAmount = BigDecimal.ZERO;
        BigDecimal approvedConversion = BigDecimal.ZERO;
        BigDecimal bidConversion = BigDecimal.ZERO;
        int realOrdersCount = 0;
        for (DtoOrder order : dtoOrders) {
            if (order.isBillAmount()) {
                realOrdersCount++;
                billAmount = billAmount.add(order.getAmountBill());
                supplierAmount = supplierAmount.add(order.getAmountSupplier());
                marginAmount = marginAmount.add(order.getAmountMargin());
            }
        }

//        BigDecimal advertAmount = wikiDao.ejectTotalAmountsByConditions(ENOrderAmountTypes.ADVERT_BUDGET, period);
//        BigDecimal clickCount = wikiDao.ejectTotalAmountsByConditions(ENOrderAmountTypes.COUNT_VISITS, period);

        BigDecimal advertAmount = BigDecimal.ZERO;
        BigDecimal clickCount = BigDecimal.ZERO;
/*
        if (clickCount == null || clickCount.equals(BigDecimal.ZERO)) {
            approvedConversion = BigDecimal.ZERO;
            bidConversion = BigDecimal.ZERO;

        } else {
            approvedConversion = BigDecimal.valueOf(realOrdersCount).divide(clickCount, 4, RoundingMode.HALF_UP);
            bidConversion = BigDecimal.valueOf(orders.size()).divide(clickCount, 4, RoundingMode.HALF_UP);
        }
        
 */
        marginWithoutAdvertAmount = marginAmount.subtract(advertAmount);
        results.put(ENOrderAmountTypes.BILL, billAmount);
        results.put(ENOrderAmountTypes.SUPPLIER, supplierAmount);
        results.put(ENOrderAmountTypes.MARGIN, marginWithoutAdvertAmount);
        
        /*
        Map<ENOrderAmountTypes, BigDecimal> postpayAmounts = new HashMap<>();

        results.put(ENOrderAmountTypes.POSTPAY, postpayAmounts.get(ENOrderAmountTypes.POSTPAY));
        results.put(ENOrderAmountTypes.POSTPAY_SDEK, postpayAmounts.get(ENOrderAmountTypes.POSTPAY_SDEK));
        results.put(ENOrderAmountTypes.POSTPAY_POST, postpayAmounts.get(ENOrderAmountTypes.POSTPAY_POST));
        results.put(ENOrderAmountTypes.POSTPAY_COMPANY, postpayAmounts.get(ENOrderAmountTypes.POSTPAY_COMPANY));
        results.put(ENOrderAmountTypes.POSTPAY_YANDEX_MARKET, postpayAmounts.get(ENOrderAmountTypes.POSTPAY_YANDEX_MARKET));
        results.put(ENOrderAmountTypes.POSTPAY_OZON_MARKET, postpayAmounts.get(ENOrderAmountTypes.POSTPAY_OZON_MARKET));
        results.put(ENOrderAmountTypes.POSTPAY_OZON_ROCKET, postpayAmounts.get(ENOrderAmountTypes.POSTPAY_OZON_ROCKET));
        results.put(ENOrderAmountTypes.POSTPAY_YANDEX_GO, postpayAmounts.get(ENOrderAmountTypes.POSTPAY_YANDEX_GO));
         */
        results.put(ENOrderAmountTypes.ADVERT_BUDGET, advertAmount);
        results.put(ENOrderAmountTypes.COUNT_REAL_ORDERS, BigDecimal.valueOf(realOrdersCount));
        results.put(ENOrderAmountTypes.CONVERSION_APPROVED, approvedConversion);
        results.put(ENOrderAmountTypes.CONVERSION_BID, bidConversion);


        results.put(ENOrderAmountTypes.BILL, billAmount);
        results.put(ENOrderAmountTypes.SUPPLIER, supplierAmount);
        results.put(ENOrderAmountTypes.ADVERT_BUDGET, BigDecimal.ZERO);
        results.put(ENOrderAmountTypes.MARGIN, marginWithoutAdvertAmount);
        results.put(ENOrderAmountTypes.POSTPAY, BigDecimal.ZERO);
        results.put(ENOrderAmountTypes.COUNT_REAL_ORDERS, BigDecimal.ZERO);

        results.put(ENOrderAmountTypes.POSTPAY_COMPANY, BigDecimal.ZERO);
        results.put(ENOrderAmountTypes.POSTPAY_CDEK, BigDecimal.ZERO);
        results.put(ENOrderAmountTypes.POSTPAY_POST, BigDecimal.ZERO);
        results.put(ENOrderAmountTypes.POSTPAY_OZON_MARKET, BigDecimal.ZERO);
        results.put(ENOrderAmountTypes.POSTPAY_YANDEX_MARKET, BigDecimal.ZERO);
        results.put(ENOrderAmountTypes.POSTPAY_YANDEX_GO, BigDecimal.ZERO);
        return results;
    }


}
