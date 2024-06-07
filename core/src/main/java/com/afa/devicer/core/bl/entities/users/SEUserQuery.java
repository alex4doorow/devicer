package com.afa.devicer.core.bl.entities.users;

import com.afa.devicer.core.bl.entities.BaseEntity;
import com.afa.devicer.core.bl.entities.sys.SEUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "D_USER_QUERY")
@Setter
@Getter
@NoArgsConstructor
public class SEUserQuery implements BaseEntity<Long>, Serializable {

    @Serial
    private static final long serialVersionUID = -3278011480243725394L;

    @Id
    @SequenceGenerator(name = "D_SEQUENCE", sequenceName = "D_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "D_SEQUENCE")
    @Column(name = "ID", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private SEUser user;

    @Column(name = "SHELF", nullable = false)
    private String shelf;

    @Column(name = "CODE", nullable = false)
    private String code;

    @Column(name = "VAL")
    private String value;
}
