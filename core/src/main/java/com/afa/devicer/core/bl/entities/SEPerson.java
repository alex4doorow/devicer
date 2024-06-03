package com.afa.devicer.core.bl.entities;

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
@Table(name = "BP_PERSON")
@Setter
@Getter
@NoArgsConstructor
public class SEPerson implements BaseEntity<Long>, Serializable {

    @Serial
    private static final long serialVersionUID = 518746571848990438L;

    @Id
    @SequenceGenerator(name = "D_SEQUENCE", sequenceName = "D_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "D_SEQUENCE")
    @Column(name = "ID", updatable = false)
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "COUNTRY_ISO_CODE_2")
    private String countryIsoCode;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "EMAIL")
    private String email;

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
        SEPerson sePerson = (SEPerson) o;
        return Objects.equals(id, sePerson.id) && Objects.equals(firstName, sePerson.firstName)
                && Objects.equals(lastName, sePerson.lastName) && Objects.equals(middleName, sePerson.middleName)
                && Objects.equals(countryIsoCode, sePerson.countryIsoCode)
                && Objects.equals(phoneNumber, sePerson.phoneNumber)
                && Objects.equals(email, sePerson.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, middleName, countryIsoCode, phoneNumber, email);
    }
}
