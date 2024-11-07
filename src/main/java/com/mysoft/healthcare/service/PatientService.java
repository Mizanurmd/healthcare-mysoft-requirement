package com.mysoft.healthcare.service;


import com.mysoft.healthcare.model.Employee;
import com.mysoft.healthcare.model.Patient;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PatientService {
    Patient savePatient(Patient patient) throws IOException;

    Optional<Patient> getPatientById(Long id);

    List<Patient> getAllPatient();

    Patient updatePatient(long id, Patient patient);

    void deletePatient(Long id);
}
