package com.afa.devicer.core.bl.entities.sys;

import com.afa.devicer.core.bl.entities.BaseEntity;
import com.afa.devicer.core.utils.DateTimeUtils;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "D_SYS_USER")
@Setter
@Getter
@EqualsAndHashCode(of ="id")
public class SEUser implements BaseEntity<Long>, Serializable {

    @Serial
    private static final long serialVersionUID = 4015927624137752492L;

    @Id
    @SequenceGenerator(name = "D_SEQUENCE", sequenceName = "D_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "D_SEQUENCE")
    @Column(name = "ID", updatable = false)
    private Long id;

    @Column(name = "NAME", length = 64, nullable = false)
    private String username;

    @Column(name = "PASSWORD", length = 100, nullable = false)
    private String password;

    @Transient
    private String passwordConfirm;

    @Column(name = "EMAIL", length = 100, nullable = false)
    private String email;

    @Column(name = "LAST_LOGIN")
    private LocalDateTime lastLogin;

/*
    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "D_SYS_USER_ROLES",
            joinColumns = { @JoinColumn(name = "USER_ID") },
            inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
    private Set<TeRole> roles = new HashSet<>();
*/

    @Override
    public String toString() {

        return "user: {" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", lastLogin=" + DateTimeUtils.formatLocalDateTime(lastLogin, DateTimeUtils.DATE_FORMAT_UTC) +
//                ", roles=" + roles.stream().map(TeRole::getName).toList() +
                "}";
    }
}
