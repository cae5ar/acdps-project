package com.pstu.acdps.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Entity
@Audited
@Table(name = "T_USER_CREDENTIALS")
public class UserCredentials extends AbstractEntity {

    @Column(nullable = false, unique = true, length = 255)
    private String login = "";

    @NotAudited
    @Column(nullable = false, length = 255)
    private String password = "";

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
