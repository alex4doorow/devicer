package com.afa.devicer.core.bl.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "BP_CUSTOMER_COMPANY")
@Setter
@Getter
@NoArgsConstructor
public class SECustomerCompany implements BaseEntity<Long>, Serializable {

    @Serial
    private static final long serialVersionUID = 8217509886653710506L;

    @Id
    @SequenceGenerator(name = "SR_SEQUENCE", sequenceName = "SR_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SR_SEQUENCE")
    @Column(name = "ID", updatable = false)
    private Long id;

    @Column(name = "INN")
    private String inn;

    @Column(name = "SHORT_NAME", length = 30, nullable = false)
    private String shortName;

    @Column(name = "LONG_NAME", length = 100)
    private String longName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID")
    private SECustomer customer;
}
