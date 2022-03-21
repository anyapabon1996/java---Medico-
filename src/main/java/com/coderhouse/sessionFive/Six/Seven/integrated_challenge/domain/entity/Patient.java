package com.coderhouse.sessionFive.Six.Seven.integrated_challenge.domain.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private LocalDate birthDay;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "patient")
    private List<Vital_Signs> vitalSignsList;

    public Patient() {
    }

    public Patient(Integer id, String name, LocalDate birthDay) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
    }

    public Patient(Integer id, String name, LocalDate birthDay, List<Vital_Signs> vitalSignsList) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
        this.vitalSignsList = vitalSignsList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public List<Vital_Signs> getVitalSignsList() {
        return vitalSignsList;
    }

    public void setVitalSignsList(Vital_Signs vitalSigns) {
        if(this.vitalSignsList == null)  this.vitalSignsList = new ArrayList<>();

        this.vitalSignsList.add(vitalSigns);

        vitalSigns.setPatient(this);
    }
}
