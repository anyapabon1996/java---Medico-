package com.coderhouse.sessionFive.Six.Seven.integrated_challenge.domain.entity;

import javax.persistence.*;


@Entity
@Table(name = "authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "AUTHORITY")
    private String authority;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username",referencedColumnName = "USERNAME")
    private Users user;

    //Constructores
    public Authority() {
    }

    public Authority(String authority, Users user) {
        this.authority = authority;
        this.user = user;
    }

    public Authority(String authority) {
        this.authority = authority;
        this.user = null;
    }

    //getters and setters
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
