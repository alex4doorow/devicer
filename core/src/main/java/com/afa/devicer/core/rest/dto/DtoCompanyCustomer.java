package com.afa.devicer.core.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DtoCompanyCustomer {
    private String inn;
    private String shortName;
    private String longName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<DtoPerson> contacts;

    @JsonIgnore
    public String getViewLongName() {
        String viewLongName = StringUtils.isNotEmpty(this.longName) ? longName : this.shortName;
        viewLongName = viewLongName.trim();
        if (StringUtils.isEmpty(this.inn)) {
            return viewLongName;
        } else {
            return "ИНН " + this.inn + " " + StringUtils.defaultString(viewLongName);
        }
    }

    @JsonIgnore
    public DtoPerson getMainContact() {
        if (contacts == null || contacts.isEmpty()) {
            return DtoPerson.createEmpty();
        } else {
            return contacts.get(0);
        }
    }
}
