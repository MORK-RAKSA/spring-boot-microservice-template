package com.raksa.app.model;

import com.raksa.app.entity.AuditableEntity;
import com.raksa.app.enumz.Role;
import com.raksa.app.services.annatation.PhoneKH;
import com.raksa.app.utils.IdGenerator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "USER_ACCOUNT")
public class UserEntity extends AuditableEntity {

    @Id
    @Column(name = "id", unique = true)
    private String id;

    @PrePersist
    public void prePersists() {
        if (this.id == null) {
            this.id = IdGenerator.generate("COS");
        }
    }

    @Column(name = "username", unique = true)
    private String username;

    @PhoneKH
    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Embedded
    private UserDetails userDetails;
}
