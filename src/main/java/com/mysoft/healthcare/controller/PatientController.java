package com.mysoft.healthcare.controller;


import com.mysoft.healthcare.model.Patient;
import com.mysoft.healthcare.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;

    }

    @PostMapping
    public ResponseEntity<Patient> savePatient(
            @RequestParam("name") String name,
            @RequestParam("gender") String gender,
            @RequestParam("age") int age,
            @RequestParam("dob") Date dob,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email,
            @RequestParam("address") String address,
            @RequestParam(value = "image", required = true, defaultValue = "No image") MultipartFile image
    ) throws IOException {
        Patient patient = new Patient();
        patient.setName(name);
        patient.setGender(gender);
        patient.setAge(age);
        patient.setDob(dob);
        patient.setPhone(phone);
        patient.setEmail(email);
        patient.setAddress(address);

        if (image !=null && !image.isEmpty()){
            patient.setImage(image.getBytes());
        }else {
            System.out.println("something is wrong");
        }
        Patient savePatient = patientService.savePatient(patient);

        return  new ResponseEntity<>(savePatient, HttpStatus.OK);

    }
    @GetMapping
    public ResponseEntity<List<Patient>>getAllPatient(){
        List<Patient>getAllPatient = patientService.getAllPatient();
        return  new ResponseEntity<>(getAllPatient, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient>getPatient(@PathVariable("id")Long id){
        Patient pId = patientService.getPatientById(id).get();
        return  new ResponseEntity<>(pId, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value ="/{id}", consumes = "multipart/form-data")
    public ResponseEntity<Patient> updatePatient(
            @RequestParam("name") String name,
            @RequestParam("gender") String gender,
            @RequestParam("age") int age,
            @RequestParam("dob") Date dob,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email,
            @RequestParam("address") String address,
            @RequestParam(value = "image", required = true, defaultValue = "No image") MultipartFile image
    ) throws IOException {
        Patient patient = new Patient();
        patient.setName(name);
        patient.setGender(gender);
        patient.setAge(age);
        patient.setDob(dob);
        patient.setPhone(phone);
        patient.setEmail(email);
        patient.setAddress(address);

        if (image !=null && !image.isEmpty()){
            patient.setImage(image.getBytes());
        }else {
            System.out.println("something is wrong");
        }
        Patient savePatient = patientService.savePatient(patient);

        return  new ResponseEntity<>(savePatient, HttpStatus.OK);

    }


}
