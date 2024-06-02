package com.afa.devicer.core.rest.dto;

import com.afa.devicer.core.model.types.ENCustomerTypes;
import com.afa.devicer.core.utils.helpers.CustomerHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DtoCustomer {

    private Long id;
    private ENCustomerTypes type;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DtoCompanyCustomer company;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DtoPerson person;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DtoAddress address;

    public DtoCustomer(ENCustomerTypes type) {
        this.type = type;
    }

    @JsonIgnore
    public String getViewInn() {
        if (type.isPerson()) {
            return "";
        } else if (type.isCompany()) {
            return company.getInn();
        } else {
            return "";
        }
    }

    @JsonIgnore
    public String getViewShortName() {
        if (type.isPerson()) {
            return CustomerHelper.getCustomerShortName(person.getFirstName(), person.getMiddleName(), person.getLastName());
        } else if (type == ENCustomerTypes.COMPANY) {
            return company.getShortName();
        } else {
            return "Unknown";
        }
    }

    @JsonIgnore
    public String getViewLongName() {
        if (type.isPerson()) {
            return person.getViewLongName();
        } else if (type.isCompany()) {
            return company.getViewLongName();
        } else {
            return "Unknown";
        }
    }

    @JsonIgnore
    public String getViewLongNameWithContactInfo() {
        String viewLongName = getViewLongName();
        if (type.isPerson()) {
            return viewLongName + ", " + person.getPhoneNumber();
        } else if (type.isCompany()) {
            String contact = company.getMainContact().getViewLongName() + " " + company.getMainContact().getEmail();
            return viewLongName.trim() + ", " + contact;
        } else {
            return viewLongName;
        }
    }

    @JsonIgnore
    public String getViewPhoneNumber() {
        if (type.isPerson()) {
            return person.getPhoneNumber();
        } else if (type.isCompany()) {
            if (!company.getContacts().isEmpty()) {
                return company.getContacts().get(0).getPhoneNumber();
            } else {
                return "(000) 000-00-00";
            }
        } else {
            return "(000) 000-00-00";
        }
    }

    @JsonIgnore
    public String getViewEmail() {
        if (type.isPerson()) {
            return person.getEmail();
        } else if (type.isCompany()) {
            if (!company.getContacts().isEmpty()) {
                return company.getContacts().get(0).getEmail();
            } else {
                return "unknown@gmail.com";
            }
        } else {
            return "unknown@gmail.com";
        }
    }
}
