package com.example.noussa.services.interfaces;

import com.example.noussa.models.Departement;
import com.example.noussa.models.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface IServiceDepartement {
    public ResponseEntity<Double> calculateAvailablePercentage();
    long addDepartment(Departement departement);
    void removeDepartment (Long idDepartment);
    Departement retrieveDepartment(Long idDepartment) ;
    List<Departement> retrieveAllDepartments();

    Set<Employee> retrieveEmployeesByDepartement(Long idDepartment);
    Departement updateDepartment(Long id,Departement departement);
    public void affecterEmplADep(Set<Employee> ListEmpls,Long idDepartment);
    public List<Departement> searchUsersByEmailStartingWithLetter(String startingLetter);

}
