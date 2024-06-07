package com.afa.devicer.core.bl.entities;

import com.afa.devicer.core.bl.entities.dictionaries.*;
import com.afa.devicer.core.bl.entities.sys.SEStore;
import com.afa.devicer.core.bl.entities.sys.SEUser;
import com.afa.devicer.core.model.types.ENOrderTypes;
import com.afa.devicer.core.utils.DateTimeUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Table(name = "BP_ORDER")
@Setter
@Getter
@EqualsAndHashCode(of ="id")
@ToString
public class SEOrder implements BaseEntity<Long>, Serializable {

    @Serial
    private static final long serialVersionUID = 4719683008350692198L;

    @Id
    @SequenceGenerator(name = "D_SEQUENCE", sequenceName = "D_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "D_SEQUENCE")
    @Column(name = "ID", updatable = false)
    private Long id;

    @Column(name = "ORDER_NUM", nullable = false)
    private Long orderNum;

    @Column(name = "ORDER_DATE", nullable = false)
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "ORDER_TYPE_ID", referencedColumnName = "ID")
    private SEOrderType type;

    @ManyToOne
    @JoinColumn(name = "SOURCE_TYPE_ID", referencedColumnName = "ID")
    private SEOrderSourceType sourceType;

    @ManyToOne
    @JoinColumn(name = "ADVERT_TYPE_ID", referencedColumnName = "ID")
    private SEOrderAdvertType advertType;

    @ManyToOne
    @JoinColumn(name = "PAYMENT_TYPE_ID", referencedColumnName = "ID")
    private SEOrderPaymentType paymentType;

    @ManyToOne
    @JoinColumn(name = "STORE_ID", referencedColumnName = "ID")
    private SEStore store;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_PRODUCT_ID", referencedColumnName = "ID")
    private SEProductCategory productCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID")
    private SECustomer customer;

    @ManyToOne
    @JoinColumn(name = "STATUS_ID", referencedColumnName = "ID")
    private SEOrderStatusType status;

    @ManyToOne
    @JoinColumn(name = "STATUS_EMAIL_ID", referencedColumnName = "ID")
    private SEOrderEmailStatusType emailStatus;

    @Column(name = "AMOUNT_TOTAL", nullable = false)
    private BigDecimal amountTotal;

    @Column(name = "OFFER_COUNT_DAY")
    private Long offerCountDay;

    @Column(name = "ANNOTATION")
    private String annotation;

    @Column(name = "REC_STATUS", nullable = false)
    private Character recStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ADDED", referencedColumnName = "ID")
    private SEUser userAdded;

    @Column(name = "DATE_ADDED", nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtils.DATE_FMT_ISO8601)
    private LocalDateTime dateAdded;

    @Column(name = "DATE_MODIFIED")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtils.DATE_FMT_ISO8601)
    private LocalDateTime dateModified;

}
