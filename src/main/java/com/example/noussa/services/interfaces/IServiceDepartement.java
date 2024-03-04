package com.example.noussa.services.interfaces;

import com.example.noussa.models.Departement;
import com.example.noussa.models.Employee;

import java.util.List;
import java.util.Set;

public interface IServiceDepartement {
    long addDepartment(Departement departement);
    void removeDepartment (Long idDepartment);
    Departement retrieveDepartment(Long idDepartment) ;
    List<Departement> retrieveAllDepartments();

    Set<Employee> retrieveEmployeesByDepartement(Long idDepartment);
    Departement updateDepartment(Long id,Departement departement);
    public void affecterEmplADep(Set<Employee> ListEmpls,Long idDepartment);

}
