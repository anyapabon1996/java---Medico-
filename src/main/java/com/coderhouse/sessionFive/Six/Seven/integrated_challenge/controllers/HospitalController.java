package com.coderhouse.sessionFive.Six.Seven.integrated_challenge.controllers;

import com.coderhouse.sessionFive.Six.Seven.integrated_challenge.domain.entity.Patient;
import com.coderhouse.sessionFive.Six.Seven.integrated_challenge.domain.entity.Vital_Signs;
import com.coderhouse.sessionFive.Six.Seven.integrated_challenge.exception.SQLException;
import com.coderhouse.sessionFive.Six.Seven.integrated_challenge.exception.notFoundException;
import com.coderhouse.sessionFive.Six.Seven.integrated_challenge.exception.notFoundExceptionByName;
import com.coderhouse.sessionFive.Six.Seven.integrated_challenge.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/sanmungo")
public class HospitalController {

    private final PatientService patientService;

    public HospitalController(PatientService patientService){
        this.patientService = patientService;
    }

    //crea paciente
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    @PostMapping("/createPatient")
    @ResponseStatus(HttpStatus.CREATED)
    public Patient createPatient(@RequestBody Patient patient) throws Exception {
        return this.patientService.createPatient(patient);
    }

    //obtiene paciente por id
    @PreAuthorize("hasAuthority('SCOPE_ROLE_DOCTOR')")
    @GetMapping("/getPatientById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient getPatientById(@PathVariable Integer id) throws notFoundException {
        return this.patientService.findPatientById(id);
    }

    //obtiene paciente por nombre
    @PreAuthorize("hasAuthority('SCOPE_ROLE_DOCTOR')")
    @GetMapping("/getPatientByName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Patient getPatientByName(@PathVariable String name) throws notFoundExceptionByName {
        return this.patientService.findPatientByName(name);
    }

    //Consulta paginada estandar
    @PreAuthorize("hasAuthority('SCOPE_ROLE_DOCTOR')")
    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public Page<Patient> getAllPatient(@PageableDefault(page = 0, size = 10) Pageable pageable){
        Page<Patient> result = this.patientService.findAll(pageable);

        return result.isEmpty() ? null : result;
    }

    //agrega signos vitales a aun paciente existente
    @PreAuthorize("hasAuthority('SCOPE_ROLE_DOCTOR')")
    @PostMapping("/addVitalSign/{patienId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Vital_Signs addVitalSigns(@PathVariable Integer patienId, @RequestBody Vital_Signs vitalSigns) throws notFoundException {
        return this.patientService.addVitalSignsById(patienId, vitalSigns);
    }

    //actualiza nombre y edad
    @PreAuthorize("hasAuthority('SCOPE_ROLE_DOCTOR')")
    @PutMapping("/updateNameAndBirthday/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient updatePatientByName(@RequestBody Patient patient, @PathVariable Integer id) throws notFoundException {
        return this.patientService.updatePatientById(patient, id);
    }

    //actualiza un signo vital
    @PreAuthorize("hasAuthority('SCOPE_ROLE_DOCTOR')")
    @PutMapping("/updateVitalSign/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Vital_Signs updateVitalSignById(@RequestBody Vital_Signs vitalSigns, @PathVariable Integer id) throws notFoundException {
        return this.patientService.updateVitalSign(vitalSigns, id);
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_DOCTOR')")
    @DeleteMapping("/deleteVitalSign/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVitalSign(@PathVariable Integer id){
        this.patientService.deleteVitalSign(id);
    }

    //elimina a un paciente
    @PreAuthorize("hasAuthority('SCOPE_ROLE_DOCTOR')")
    @DeleteMapping("/deletePatient/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable Integer id) throws notFoundException {
        this.patientService.deletePatienById(id);
    }
}
