package com.afa.devicer.core.bl.entities;

import com.afa.devicer.core.utils.DateTimeUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Column(name = "ORDER_NO", nullable = false)
    private Long orderNum;

    @Column(name = "ORDER_DATE", nullable = false)
    private LocalDate orderDate;

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
