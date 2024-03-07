package com.example.noussa.services.interfaces;

import com.example.noussa.models.Employee;
import com.example.noussa.models.PosteEmployee;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IServiceEmployee {
    public void addEmployeeEtAffectDepartement(Employee employee,Long id);
    public ResponseEntity<Long> updateEmployee(Long id, Employee employee);
    public void deleteEmployee(Long id);
    public Employee getEmployee(Long id);
    List<Employee> retrieveAll();
    public Employee assignEmToEquipe(Long idE,Long idEquipe);
    public List<Employee> findAllByPosteEmployee(PosteEmployee posteEmployee);
    public Employee assignEmToDep(Long idE,Long idDep);

}
