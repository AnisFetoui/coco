package com.example.noussa.services.service;

import com.example.noussa.models.Departement;
import com.example.noussa.models.Employee;
import com.example.noussa.repos.DepartementRepo;
import com.example.noussa.repos.EmployeeRepo;
import com.example.noussa.services.interfaces.IServiceDepartement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceDepartement implements IServiceDepartement {
    DepartementRepo departementRepo;
    EmployeeRepo employeeRepo;


    @Override
    public long addDepartment(Departement departement) {

            departementRepo.save(departement);
            return departement.getId_departement();

    }
    @Override
    public Departement updateDepartment(Long id,Departement updatedDepartement) {
        Departement existingDepartement = departementRepo.findById(id).get();
        existingDepartement.setNbreEmpl(updatedDepartement.getEmployees().size());
        existingDepartement.setLibelle(updatedDepartement.getLibelle());
        existingDepartement.setMaxSaturation(updatedDepartement.getMaxSaturation());
        existingDepartement.setEmployees(updatedDepartement.getEmployees());
        return departementRepo.save(existingDepartement);
    }

    @Override
    public void removeDepartment(Long idDepartment) {
        departementRepo.deleteById(idDepartment);
    }
    @Override
    public void affecterEmplADep(Set<Employee> ListEmpls,Long idDepartment) {
        Departement existingDepartement = departementRepo.findById(idDepartment).get();

        if(ListEmpls.size()>existingDepartement.getMaxSaturation()){
            log.info("you pass the limit");
        }else {
            for (Employee employee : ListEmpls) {

                employee.setDepartement(existingDepartement);
                employeeRepo.save(employee);
            }
            existingDepartement.setEmployees(ListEmpls);
            Set<Employee> anis = existingDepartement.getEmployees();
            existingDepartement.setNbreEmpl(anis.size());
            departementRepo.save(existingDepartement);
        }


    }
    @Override
    public Departement retrieveDepartment(Long idDepartment) {
        return departementRepo.findById(idDepartment).get();
    }

    @Override
    public List<Departement> retrieveAllDepartments() {
        return departementRepo.findAll();
    }

    @Override
    public Set<Employee> retrieveEmployeesByDepartement(Long idDepartment) {
        Departement departement = departementRepo.findById(idDepartment).get();

        return departement.getEmployees() ;
    }
    public List<Departement> searchUsersByEmailStartingWithLetter(String startingLetter) {
        return departementRepo.findByLibelleStartingWith(startingLetter);
    }
}
