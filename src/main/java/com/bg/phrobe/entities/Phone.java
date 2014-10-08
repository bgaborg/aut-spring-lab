package com.bg.phrobe.entities;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.util.DigestUtils;

import javax.persistence.*;
import java.io.Serializable;


/**
 * A Phone.
 */
@Entity
@Table(name = "T_PHONE")
public class Phone implements Serializable {

    @Id
    @Column(name = "api_key")
    private String api_key;

    @Column(name = "nickname")
    private String nickname;

    public Phone() {
    }

    public Phone(String api, String nick) {
        this.api_key = api;
        this.nickname = nick;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Phone phone = (Phone) o;

        if (api_key != null ? !api_key.equals(phone.api_key) : phone.api_key != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "Phone{" +
                ", api_key='" + api_key + "'" +
                ", nickname='" + nickname + "'" +
                '}';
    }
}