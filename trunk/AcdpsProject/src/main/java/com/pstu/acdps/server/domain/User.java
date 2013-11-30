package com.pstu.acdps.server.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.pstu.acdps.shared.dto.UserDto;

@Audited
@Entity
@Table(name = "T_USER")
public class User extends AbstractEntity {

    @Column(nullable = false, length = 4096)
    private String name;

    /** логин пароль */
    @JoinColumn(nullable = true)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserCredentials credentials = null;

    /** Флажок о том что пользователь админ */
    @Column(name = "c_admin", nullable = false)
    private Boolean admin = false;

    public Boolean isAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(UserCredentials credentials) {
        this.credentials = credentials;
    }

    public UserDto toDto() {
        UserDto dto = new UserDto(id, name, credentials.getLogin());
        return dto;
    }

}
