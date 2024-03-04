package com.example.noussa.services.service;

import com.example.noussa.models.Absence;
import com.example.noussa.models.Employee;
import com.example.noussa.repos.AbsenceRepo;
import com.example.noussa.repos.EmployeeRepo;
import com.example.noussa.services.interfaces.IServiceAbsence;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceAbsence implements IServiceAbsence {

    AbsenceRepo absenceRepo;
    EmployeeRepo employeeRepo;

    @Override
    public void addAbsence(Absence absence) {
       // Employee emp = employeeRepo.findById(id).get();
        //absence.setEmp(emp);
       // absence.setValidee(true);
        absenceRepo.save(absence);

    }

    @Override
    public void updateAbsence(Long id, Absence updatedAbsence) {

        Absence existingAbsence = absenceRepo.findById(id).get();

        existingAbsence.setMotif(updatedAbsence.getMotif());
        existingAbsence.setJustification(updatedAbsence.getJustification());
        existingAbsence.setDate(updatedAbsence.getDate());
        existingAbsence.setValidee(updatedAbsence.isValidee());

        absenceRepo.save(existingAbsence);
    }

    @Override
    public void deleteAbsence(Long id) {
    absenceRepo.deleteById(id);
    }

    @Override
    public Set<Absence> getAbsencesByEmp(Long id) {
        Employee emp = employeeRepo.findById(id).get();
        return emp.getAbsences();
    }
    @Override
    public Absence getAbsence(Long id) {
        return absenceRepo.findById(id).get();
    }


    public List<Absence> retrieveAbsencesForToday() {
        LocalDate currentDate = LocalDate.now();  // Current local date
        return absenceRepo.findByDate(currentDate);
    }
    public List<Absence> retrieveAll() {
        return absenceRepo.findAll();
    }


//    public String verifNbreAbsencByempl(Long id){
//        Employee employee = employeeRepo.findById(id).get();
////        User user = userRepo.findById(employee.getUserId()).get();
//        Set<Absence> absences = employee.getAbsences();
//        if(absences.size()>=5){
//            return "this employe "+user.getNom()+"has many absence";
//        } else if (absences.size()<5 && absences.size()>1) {
//            return "this employe "+user.getNom()+"has few absence";
//
//        }else {
//            return "this employe "+user.getNom()+"has no absence";
//        }
//    }
}
