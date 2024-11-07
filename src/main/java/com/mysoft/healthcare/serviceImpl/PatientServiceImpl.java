package com.mysoft.healthcare.serviceImpl;

import com.mysoft.healthcare.attachment.FileAttachment;
import com.mysoft.healthcare.model.Employee;
import com.mysoft.healthcare.model.Patient;
import com.mysoft.healthcare.repository.PatientRepository;
import com.mysoft.healthcare.service.FileAttachmentService;
import com.mysoft.healthcare.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    @Override
    public Patient savePatient(Patient patient) throws IOException {
        return patientRepository.save(patient);
    }

    @Override
    public Optional<Patient> getPatientById(Long id) {
        return Optional.ofNullable(patientRepository.findById(id).orElseThrow(()-> new RuntimeException(id+" is not found!!!")));
    }

    @Override
    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }

    @Override
    public Patient updatePatient(long id, Patient patient) {
        Patient patientUpdate = patientRepository.findById(id).get();
        patientUpdate.setName(patient.getName());
        patientUpdate.setGender(patient.getGender());
        patientUpdate.setAge(patient.getAge());
        patientUpdate.setDob(patient.getDob());
        patientUpdate.setPhone(patient.getPhone());
        patientUpdate.setEmail(patient.getEmail());
        patientUpdate.setAddress(patient.getAddress());
        patientUpdate.setImage(patient.getImage());


        return patientRepository.save(patientUpdate);

    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);

    }
}
