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
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@Table(name = "D_SYS_ROLE")
@Setter
@Getter
public class SERole implements BaseEntity<Long>, Serializable {

    @Serial
    private static final long serialVersionUID = 4015927624137752492L;

    @Id
    @SequenceGenerator(name = "D_SEQUENCE", sequenceName = "D_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "D_SEQUENCE")
    @Column(name = "ID", updatable = false)
    private Long id;

    @Column(name = "NAME", length = 50, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<SEUser> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SERole seRole = (SERole) o;
        return Objects.equals(id, seRole.id) && Objects.equals(name, seRole.name) && Objects.equals(users, seRole.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, users);
    }

    @Override
    public String toString() {
        return "SERole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
