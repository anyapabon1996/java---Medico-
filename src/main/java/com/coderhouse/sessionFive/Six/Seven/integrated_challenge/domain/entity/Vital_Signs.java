package com.coderhouse.sessionFive.Six.Seven.integrated_challenge.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "vital_signs")
public class Vital_Signs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "blood_pressure")
    private Float bloodPressure;

    @Column(name = "heart_rate")
    private Float heartRate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Vital_Signs(){

    }

    public Vital_Signs(Float bloodPressure, Float heartRate) {
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
    }

    public Vital_Signs(Float bloodPressure, Float heartRate, Patient patient) {
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
        this.patient = patient;
    }

    public Float getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(Float bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public Float getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Float heartRate) {
        this.heartRate = heartRate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
