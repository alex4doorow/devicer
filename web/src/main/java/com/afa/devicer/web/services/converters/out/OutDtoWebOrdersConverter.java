package com.afa.devicer.web.services.converters.out;

import com.afa.devicer.core.bl.entities.SEAddress;
import com.afa.devicer.core.bl.entities.SECustomerCompany;
import com.afa.devicer.core.bl.entities.SEOrder;
import com.afa.devicer.core.bl.entities.SEPerson;
import com.afa.devicer.core.bl.entities.dictionaries.SEProductCategory;
import com.afa.devicer.core.model.types.*;
import com.afa.devicer.core.rest.dto.*;
import com.afa.devicer.core.services.converters.IOConverter;
import com.afa.devicer.core.services.converters.IOConverterOfList;
import com.afa.devicer.core.utils.DateTimeUtils;
import com.afa.devicer.web.dto.DtoWebOrder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class OutDtoWebOrdersConverter implements IOConverter<DtoOrder, DtoWebOrder>, IOConverterOfList<DtoOrder, DtoWebOrder> {

    @Override
    public Collection<DtoWebOrder> convertTo(Collection<DtoOrder> inputList) {
        return inputList
                .stream()
                .sorted(Comparator.comparing(DtoOrder::getId))
                .map(this::convertTo)
                .toList();
    }

    @Override
    public DtoWebOrder convertTo(DtoOrder input) {

        DtoWebOrder result = new DtoWebOrder();
        result.setId(input.getId());
        result.setOrderNum(input.getOrderNum());
        result.setOrderDate(input.getOrderDate());
        result.setCustomer(input.getCustomer());
        result.setType(input.getType());
        result.setSource(input.getSource());
        result.setAdvert(input.getAdvert());
        result.setPayment(input.getPayment());
        result.setStore(input.getStore());
        result.setEmailStatus(input.getEmailStatus());
        result.setStatus(input.getStatus());
        result.setProductCategory(input.getProductCategory());
        result.setDelivery(input.getDelivery());
        result.setAmounts(input.getAmounts());
        result.setAnnotation(input.getAnnotation());
        result.setDateAdded(input.getDateAdded());
        result.setDateModified(input.getDateModified());
        return result;
    }
}
