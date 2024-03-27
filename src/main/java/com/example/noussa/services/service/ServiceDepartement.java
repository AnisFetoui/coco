package com.example.noussa.services.service;

import com.example.noussa.models.Departement;
import com.example.noussa.models.Employee;
import com.example.noussa.repos.DepartementRepo;
import com.example.noussa.repos.EmployeeRepo;
import com.example.noussa.services.interfaces.IServiceDepartement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceDepartement implements IServiceDepartement {
    DepartementRepo departementRepo;
    EmployeeRepo employeeRepo;

    public ResponseEntity<Double> calculateAvailablePercentage() {
        List<Departement> departments = departementRepo.findAll();
        int total = 0; // Initialize total to 0
        for (Departement department : departments) {
            total += department.getMaxSaturation(); // Accumulate the total maximum saturation of all departments
        }

        int availablePlaces = departments.stream()
                .mapToInt(department -> {
                    int maxSaturation = department.getMaxSaturation();
                    int nbreEmpl = department.getNbreEmpl();
                    int available = maxSaturation - nbreEmpl;
                    System.out.println("Max Saturation: " + maxSaturation + ", Employees: " + nbreEmpl + ", Available: " + available);
                    return available;
                })
                .sum();

        System.out.println("Total Max Saturation: " + total);
        System.out.println("Total Available Places: " + availablePlaces);

        return ResponseEntity.ok((double) availablePlaces / total * 100); // Calculate percentage
    }

    @Override
    public long addDepartment(Departement departement) {

            departementRepo.save(departement);
            return departement.getId_departement();

    }
    @Override
    public Departement updateDepartment(Long id,Departement updatedDepartement) {
        Departement existingDepartement = departementRepo.findById(id).get();

        existingDepartement.setLibelle(updatedDepartement.getLibelle());
        existingDepartement.setMaxSaturation(updatedDepartement.getMaxSaturation());
        existingDepartement.setEmployees(updatedDepartement.getEmployees());
        return departementRepo.save(existingDepartement);
    }

    @Override
    public void removeDepartment(Long idDepartment) {
        Departement departement = departementRepo.findById(idDepartment).get();
        Set<Employee> anis =  departement.getEmployees();
        for (Employee em : anis){
            em.setDepartement(null);
            employeeRepo.save(em);
        }
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
//        return departementRepo.findByLibelleStartingWithAndNbreEmplAndMaxSaturation(startingLetter,nbreEmployees,maxSaturation);

    }
}
