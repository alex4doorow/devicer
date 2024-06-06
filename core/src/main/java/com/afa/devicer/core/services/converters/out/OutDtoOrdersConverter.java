package com.afa.devicer.core.services.converters.out;

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
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class OutDtoOrdersConverter implements IOConverter<SEOrder, DtoOrder>, IOConverterOfList<SEOrder, DtoOrder> {

    @Override
    public Collection<DtoOrder> convertTo(Collection<SEOrder> orders) {
        return orders
                .stream()
                .map(this::convertTo)
                .toList();
    }

    @Override
    public DtoOrder convertTo(SEOrder seOrder) {
        final Map<ENOrderAmountTypes, BigDecimal> amounts = new HashMap<>();


//        dtoOrder.getAmounts().put(OrderAmountTypes.TOTAL_WITH_DELIVERY, order.getAmounts().getValue(OrderAmountTypes.TOTAL_WITH_DELIVERY));
//        dtoOrder.getAmounts().put(OrderAmountTypes.BILL, order.getAmounts().getValue(OrderAmountTypes.BILL));
//        dtoOrder.getAmounts().put(OrderAmountTypes.SUPPLIER, order.getAmounts().getValue(OrderAmountTypes.SUPPLIER));
//        dtoOrder.getAmounts().put(OrderAmountTypes.MARGIN, order.getAmounts().getValue(OrderAmountTypes.MARGIN));
//        dtoOrder.getAmounts().put(OrderAmountTypes.POSTPAY, order.getAmounts().getValue(OrderAmountTypes.POSTPAY))

        amounts.put(ENOrderAmountTypes.BILL, BigDecimal.ZERO);
        amounts.put(ENOrderAmountTypes.SUPPLIER, BigDecimal.ZERO);
        amounts.put(ENOrderAmountTypes.MARGIN, BigDecimal.ZERO);
        amounts.put(ENOrderAmountTypes.POSTPAY, BigDecimal.ZERO);
        amounts.put(ENOrderAmountTypes.DELIVERY, BigDecimal.ZERO);
        amounts.put(ENOrderAmountTypes.TOTAL, seOrder.getAmountTotal());
        amounts.put(ENOrderAmountTypes.TOTAL_WITH_DELIVERY, BigDecimal.ZERO);

        ENCustomerTypes customerType = ENCustomerTypes.getValueById(seOrder.getCustomer().getType().getId());

        Set<SEAddress> seAddresses = seOrder.getCustomer().getAddresses();
        DtoAddress address = null;
        if (seAddresses != null && !seAddresses.isEmpty()) {
            SEAddress seAddress = seAddresses.stream().findFirst().get();
            address = DtoAddress.builder()
                    .id(seAddress.getId())
                    .type(ENAddressTypes.getValueById(seAddress.getType().getId()))
                    .address(seAddress.getAddress())
                    .build();
        }
        Set<SEPerson> seContacts = seOrder.getCustomer().getContacts();

        List<DtoPerson> contacts = new ArrayList<>();
        if (seContacts != null && !seContacts.isEmpty()) {
            SEPerson seContactPerson = seContacts.stream().findFirst().get();
            DtoPerson contact = DtoPerson.builder()
                    .id(seContactPerson.getId())
                    .firstName(seContactPerson.getFirstName())
                    .lastName(seContactPerson.getLastName())
                    .phoneNumber(seContactPerson.getPhoneNumber())
                    .email(seContactPerson.getEmail())
                    .build();
            contacts.add(contact);
        }
        DtoCustomer customer = null;
        if (customerType == ENCustomerTypes.CUSTOMER) {
            customer = DtoCustomer.builder()
                    .id(seOrder.getCustomer().getId())
                    .type(customerType)
                    .person(DtoPerson.builder()
                            .id(seOrder.getCustomer().getPerson().getId())
                            .firstName(seOrder.getCustomer().getPerson().getFirstName())
                            .lastName(seOrder.getCustomer().getPerson().getLastName())
                            .phoneNumber(seOrder.getCustomer().getPerson().getPhoneNumber())
                            .email(seOrder.getCustomer().getPerson().getEmail())
                            .build())
                    .address(address)
                    .build();
        } else if (customerType == ENCustomerTypes.COMPANY) {
            Optional<SECustomerCompany> seCustomerCompany = seOrder.getCustomer().getCustomerCompanies().stream().findFirst();
            DtoCompanyCustomer company = null;
            if (seCustomerCompany.isPresent()) {
                company = DtoCompanyCustomer.builder()
                        .shortName(seCustomerCompany.get().getShortName())
                        .longName(seCustomerCompany.get().getLongName())
                        .inn(seCustomerCompany.get().getInn())
                        .contacts(contacts)
                        .build();
            }
            customer = DtoCustomer.builder()
                    .id(seOrder.getCustomer().getId())
                    .type(customerType)
                    .company(company)
                    .address(address)
                    .build();
        }


        SEProductCategory seProductCategory = seOrder.getProductCategory();
        DtoProductCategory productCategory = DtoProductCategory.builder()
                .id(seProductCategory.getId())
                .name(seProductCategory.getAnnotation())
                .group(seProductCategory.getGroup())
                .build();

        DtoOrder dtoOrder = DtoOrder.builder()
                .id(seOrder.getId())
                .orderNum(seOrder.getOrderNum())
                .orderDate(seOrder.getOrderDate())
                .store(ENStores.getValueById(seOrder.getStore().getId()))
                .type(ENOrderTypes.getValueById(seOrder.getType().getId()))
                .source(ENOrderSourceTypes.getValueById(seOrder.getSourceType().getId()))
                .advert(ENOrderAdvertTypes.getValueById(seOrder.getAdvertType().getId()))
                .payment(ENPaymentTypes.getValueById(seOrder.getPaymentType().getId()))
                .productCategory(productCategory)
                .customer(customer)
                .status(ENOrderStatuses.getValueById(seOrder.getStatus().getId()))
                .emailStatus(ENOrderEmailStatuses.getValueById(seOrder.getEmailStatus().getId()))
                .payment(ENPaymentTypes.getValueById(seOrder.getPaymentType().getId()))
                .amounts(amounts)
                .dateAdded(DateTimeUtils.toOffsetDateTime(seOrder.getDateAdded()))
                .dateModified(DateTimeUtils.toOffsetDateTime(seOrder.getDateModified()))
                .build();

        dtoOrder.setDelivery(DtoOrderDelivery.builder()
                .parent(dtoOrder).address(DtoAddress.builder()
                        .address("Moscow, Green street 43, 34")
                        .build())
                .type(ENDeliveryTypes.CDEK_PVZ_TYPICAL)
                .trackCode("")
                .build());

        return dtoOrder;
    }

}
