package com.afa.devicer.core.rest.dto;

import com.afa.devicer.core.utils.helpers.CustomerHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoPerson {
    private Long id;
    private String firstName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String middleName;

    private String phoneNumber;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;

    @JsonIgnore
    public String getShortName() {
        return CustomerHelper.getCustomerShortName(firstName, middleName, lastName);
    }

    @JsonIgnore
    public String getViewLongName() {
        String result = StringUtils.defaultString(this.lastName) + " " + StringUtils.defaultString(this.firstName)
                + " " + StringUtils.defaultString(this.middleName);
        return result.trim();
    }

    @JsonIgnore
    public static DtoPerson createEmpty() {
        return new DtoPerson();
    }

    @JsonIgnore
    @Override
    public String toString() {
        return "DtoPerson {" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
