package com.afa.devicer.web.dto;

import com.afa.devicer.core.rest.dto.DtoOrder;
import com.afa.devicer.web.dto.view.DtoWebViewOrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Data
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class DtoWebOrder extends DtoOrder {

    @JsonIgnore
    public String getViewNum() {
        String result = String.valueOf(this.getOrderNum());
        /*
        if (getExternalCrms() != null && getExternalCrms().size() > 0) {
            if (this.getAdvert() == OrderAdvertTypes.OZON) {
                String ozonMarketNo = "";
                for (DtoOrderExternalCrm externalCrm : getExternalCrms()) {
                    if (externalCrm.getType() == CrmTypes.OZON) {
                        ozonMarketNo = String.valueOf(externalCrm.getParentCode());
                    }
                }
                result += " (" + ozonMarketNo + ")";
                return result;

            } else if (this.getAdvert() == OrderAdvertTypes.YANDEX_MARKET) {
                String openCartNo = "";
                String yandexMarketNo = "";
                for (DtoOrderExternalCrm externalCrm : getExternalCrms()) {
                    if (externalCrm.getType() == CrmTypes.OPENCART) {
                        openCartNo = String.valueOf(externalCrm.getParentId());
                    }
                    if (externalCrm.getType() == CrmTypes.YANDEX_MARKET) {
                        yandexMarketNo = String.valueOf(externalCrm.getParentId());
                    }
                }
                result += " (" + yandexMarketNo + " / " + openCartNo + ")";
                return result;
            }
            for (DtoOrderExternalCrm externalCrm : getExternalCrms()) {
                if (externalCrm.getType() == CrmTypes.OPENCART) {
                    result += " (" + externalCrm.getParentId() + ")";
                    break;
                }
            }
            //Просмотр данных по заказу #10161 (197) от 01.03.2021 г.
        }

        */
        return result;
    }

    @JsonIgnore
    public DtoWebViewOrderStatus getViewStatus() {
        return DtoWebViewOrderStatus.createViewOrderStatus(this);
    }

    @JsonIgnore
    public String getViewDateInfo() {
        String result = this.getType().getAnnotation() + ", " + this.getStatus().getAnnotation();
        String expiredDate = this.getExpiredDate();
        if (StringUtils.isEmpty(expiredDate)) {
            return result;
        } else {
            return result + ", " + expiredDate;
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object obj = super.clone();

        DtoWebOrder result = (DtoWebOrder) obj;
        // ...
        return result;
    }
}
