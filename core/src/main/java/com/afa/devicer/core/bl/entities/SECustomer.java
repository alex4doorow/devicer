package com.afa.devicer.core.bl.entities;

import com.afa.devicer.core.bl.entities.dictionaries.SECustomerType;
import com.afa.devicer.core.bl.entities.sys.SEUser;
import com.afa.devicer.core.utils.DateTimeUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "BP_CUSTOMER")
@Setter
@Getter
@NoArgsConstructor
public class SECustomer implements BaseEntity<Long>, Serializable {

    @Serial
    private static final long serialVersionUID = 7116303943090207902L;

    @Id
    @SequenceGenerator(name = "SR_SEQUENCE", sequenceName = "SR_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SR_SEQUENCE")
    @Column(name = "ID", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TYPE_ID", referencedColumnName = "ID")
    private SECustomerType type;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
    private SEPerson person;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<SECustomerCompany> customerCompanies;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "BP_CUSTOMER_ADDRESS",
            joinColumns = { @JoinColumn(name = "CUSTOMER_ID") },
            inverseJoinColumns = { @JoinColumn(name = "ADDRESS_ID") })
    private Set<SEAddress> addresses = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "BP_CUSTOMER_CONTACT",
            joinColumns = { @JoinColumn(name = "CUSTOMER_ID") },
            inverseJoinColumns = { @JoinColumn(name = "PERSON_ID") })
    private Set<SEPerson> contacts = new HashSet<>();

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
