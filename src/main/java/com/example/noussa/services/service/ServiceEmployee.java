package com.example.noussa.services.service;

import com.example.noussa.models.*;
import com.example.noussa.repos.DepartementRepo;
import com.example.noussa.repos.EmployeeRepo;
import com.example.noussa.repos.TeamRepo;
import com.example.noussa.services.interfaces.IServiceEmployee;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceEmployee implements IServiceEmployee {
    EmployeeRepo employeeRepo;
    DepartementRepo departementRepo;
    TeamRepo teamRepo;

    @Override
//    public void addEmployeeEtAffectDepartement(Employee employee,Long id,Long idT) {
    public void addEmployeeEtAffectDepartement(Employee employee,Long id) {
        Departement departement = departementRepo.findById(id).get();
//        Team team = teamRepo.findById(idT).get();
//        int saturationTeam = team.getEmployees().size();

       int max = departement.getMaxSaturation();
       int saturation = departement.getNbreEmpl();
       if(max<=saturation ){

           log.info("vous atteint le max");
       }else{
           employee.setDepartement(departement);
           departement.setNbreEmpl(saturation+1);
           employeeRepo.save(employee);
           departementRepo.save(departement);
       }
    }

    @Override
    public ResponseEntity<Long> updateEmployee(Long id, Employee updatedEmployee,Long p) {
        Employee emp = employeeRepo.findById(id).get();
        Departement depart = departementRepo.findById(p).get();

        if (emp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
        }

        int max = depart.getMaxSaturation();
        int saturation = depart.getNbreEmpl();

        if (max <= saturation) {
            log.error("Validation failed for Employee update. ID: {} - Departement is saturated.", id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(id + 100);
        } else {
            emp.setDepartement(depart);
            depart.setNbreEmpl(saturation + 1);
            emp.setPosteEmployee(updatedEmployee.getPosteEmployee());
            emp.setDate_embauche(updatedEmployee.getDate_embauche());
            employeeRepo.save(emp);
            departementRepo.save(depart);

            return ResponseEntity.ok(id);
        }
    }


    @Override
    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
    }

    @Override
    public Employee getEmployee(Long id) {
        return employeeRepo.findById(id).get();
    }

    @Override
    public List<Employee> retrieveAll() {
        return employeeRepo.findAll();
    }

    public List<Employee> findAllByPosteEmployee(PosteEmployee posteEmployee) {
        List<Employee> anis = new ArrayList<>();
        List<Employee> noussa = employeeRepo.findAll();
        for(Employee em : noussa){
            if(em.getPosteEmployee().equals(posteEmployee)){
                anis.add(em);
            }

        }
        return anis;
    }

    public Employee assignEmToDep(Long idE,Long idDep){
        Employee e = employeeRepo.findById(idE).orElse(null);
        Departement d = departementRepo.findById(idDep).orElse(null);
        e.setDepartement(d);
        return  employeeRepo.save(e);
    }
    public Employee assignEmToEquipe(Long idE,Long idEquipe){
        Employee e = employeeRepo.findById(idE).orElse(null);
//        Team eq  = teemRepo.findById(idEquipe).orElse(null);
//            e.setTeam(eq);
        return  employeeRepo.save(e);
    }


}
