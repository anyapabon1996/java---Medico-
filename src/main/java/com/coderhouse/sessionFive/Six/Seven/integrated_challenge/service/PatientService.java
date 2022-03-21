package com.coderhouse.sessionFive.Six.Seven.integrated_challenge.service;

import com.coderhouse.sessionFive.Six.Seven.integrated_challenge.domain.abstractClass.BaseService;
import com.coderhouse.sessionFive.Six.Seven.integrated_challenge.domain.entity.Patient;
import com.coderhouse.sessionFive.Six.Seven.integrated_challenge.domain.entity.Vital_Signs;
import com.coderhouse.sessionFive.Six.Seven.integrated_challenge.exception.SQLException;
import com.coderhouse.sessionFive.Six.Seven.integrated_challenge.exception.notFoundException;
import com.coderhouse.sessionFive.Six.Seven.integrated_challenge.exception.notFoundExceptionByName;
import com.coderhouse.sessionFive.Six.Seven.integrated_challenge.repository.PatientRepository;
import com.coderhouse.sessionFive.Six.Seven.integrated_challenge.repository.VitalSignsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class PatientService extends BaseService<Patient, Integer, PatientRepository> {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Autowired
    public VitalSignsRepository vitalSignsRepository;

    @Transactional(rollbackFor = SQLException.class, propagation = Propagation.REQUIRED)
    public Patient createPatient(Patient patient) throws Exception {
        if (patient.getName().isEmpty() || patient.getName() == null)
            throw new SQLException("Patient´s name is mandatory");

        Patient patientAlreadyExist = this.patientRepository.findPatientByName(patient.getName().toLowerCase());

        if(patientAlreadyExist != null)
            throw new Exception("This patient already exist");

        patient.setName(patient.getName().toLowerCase());

        return this.patientRepository.save(patient);
    }
    //No me sale el ID en la respuesta al cliente

    @Transactional(rollbackFor = notFoundException.class, propagation = Propagation.REQUIRED)
    public Patient findPatientById(Integer id) throws notFoundException {
        Patient patient = this.patientRepository.findById(id).orElse(null);

        if(patient == null)
            throw new notFoundException("There is not patient with id: {}", id);

        return patient;
    }

    @Transactional(rollbackFor = notFoundExceptionByName.class, propagation = Propagation.REQUIRED)
    public Patient findPatientByName(String name) throws notFoundExceptionByName {
        Patient patient = this.patientRepository.findPatientByName(name);

        if(patient == null)
            throw new notFoundExceptionByName("There is not patient with this name");

        return patient;
    }

    @Transactional(rollbackFor = notFoundException.class, propagation = Propagation.REQUIRED)
    public Vital_Signs addVitalSignsById(Integer id, Vital_Signs vitalSigns) throws notFoundException {
        Patient patient = this.findPatientById(id);

        if (patient == null)
            throw new notFoundException("There is not patient with id: {}", id);

        patient.setVitalSignsList(vitalSigns);

        vitalSigns.setPatient(patient);

        return vitalSigns;
    }

    @Transactional(rollbackFor = notFoundException.class, propagation = Propagation.REQUIRED)
    public List<Vital_Signs> allVitalSignsFromPatientById(Integer idPatient) throws notFoundException{
        return this.vitalSignsRepository.allVitalSignsFromPatientById(idPatient);
    }

    @Transactional(rollbackFor = notFoundExceptionByName.class, propagation = Propagation.REQUIRED)
    public Patient updatePatientById(Patient patient, Integer id) throws notFoundException {
        Patient patientInDB = this.findPatientById(id);

        if(patientInDB == null)
            throw new notFoundException("This patient does not exist ini our data base {}", id);

        patientInDB.setName(patient.getName().toLowerCase());
        patientInDB.setBirthDay(patient.getBirthDay());

        return this.patientRepository.saveAndFlush(patientInDB);
    }

    @Transactional(rollbackFor = notFoundException.class, propagation = Propagation.REQUIRED)
    public Vital_Signs updateVitalSign(Vital_Signs vitalSigns, Integer id) throws notFoundException{
        Vital_Signs vitalSigns1 = this.vitalSignsRepository.findById(id).orElse(null);

        if (vitalSigns1 == null)
            throw new notFoundException("Do not exist this register: {}", id);

        vitalSigns1.setBloodPressure(vitalSigns.getBloodPressure());
        vitalSigns1.setHeartRate(vitalSigns.getHeartRate());

        return this.vitalSignsRepository.saveAndFlush(vitalSigns1);
    }

    //si hago esto con el metodo deleteById me elina todos los signos vitales de una persona, y a esa persona misma
    //si lo hago con una query propia, me tira error de q no retorna nada el metodo
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteVitalSign(Integer id){
        this.vitalSignsRepository.deleteVitlSign(id);
    }

    @Transactional(rollbackFor = notFoundException.class, propagation = Propagation.REQUIRED)
    public void deletePatienById(Integer id) throws notFoundException {
        Patient patient = this.findPatientById(id);
        if(patient == null)
            throw new notFoundException("This patient does not exist ini our data base {}", id);

        this.patientRepository.deleteById(id);
    }


}
