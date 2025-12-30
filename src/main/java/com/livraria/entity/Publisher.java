package com.livraria.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "PUBLISHER")
public class Publisher implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "SITE", nullable = true)
    private String site;

    public Publisher() {

    }

    public Publisher(Long id, String name, String site) {
        this.id = id;
        this.name = name;
        this.site = site;
    }

    public Publisher(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Publisher(String name, String site) {
        this.name = name;
        this.site = site;
    }

    public Publisher(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
