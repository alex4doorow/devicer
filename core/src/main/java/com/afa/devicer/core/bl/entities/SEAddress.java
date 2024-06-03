package com.afa.devicer.core.bl.entities;

import com.afa.devicer.core.bl.entities.dictionaries.SEAddressType;
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
import java.util.Objects;

@Entity
@Table(name = "BP_ADDRESS")
@Setter
@Getter
@NoArgsConstructor
public class SEAddress implements BaseEntity<Long>, Serializable {

    @Serial
    private static final long serialVersionUID = 518746571848990438L;

    @Id
    @SequenceGenerator(name = "D_SEQUENCE", sequenceName = "D_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "D_SEQUENCE")
    @Column(name = "ID", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TYPE_ID", referencedColumnName = "ID")
    private SEAddressType type;

    @Column(name = "COUNTRY_ISO_CODE_2")
    private String countryIsoCode;

    @Column(name = "POST_CODE")
    private String postCode;

    @Column(name = "STREET")
    private String street;

    @Column(name = "HOUSE")
    private String house;

    @Column(name = "FLAT")
    private String flat;

    @Column(name = "ADDRESS")
    private String address;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SEAddress seAddress = (SEAddress) o;
        return Objects.equals(id, seAddress.id) && Objects.equals(type, seAddress.type)
                && Objects.equals(countryIsoCode, seAddress.countryIsoCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, countryIsoCode);
    }
}
