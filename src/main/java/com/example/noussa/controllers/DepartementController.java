package com.example.noussa.controllers;

import com.example.noussa.models.Absence;
import com.example.noussa.models.Departement;
import com.example.noussa.models.Employee;
import com.example.noussa.services.interfaces.IServiceAbsence;
import com.example.noussa.services.interfaces.IServiceDepartement;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/departement")
@CrossOrigin(origins = "http://localhost:4200")

public class DepartementController {
    IServiceDepartement iServiceDepartement;
    @PostMapping("/addDepartment")
    public long add(@RequestBody Departement p){
        return iServiceDepartement.addDepartment(p);
    }
    @PutMapping("/updateDepartment/{id}")
    public Departement updateDepartement(@PathVariable("id") Long id,@RequestBody Departement p) throws Exception {
        return iServiceDepartement.updateDepartment(id,p);
    }
    @PutMapping("/affecterEmplADep/{idD}")
    public void affecterEmplADep(@RequestBody Set<Employee> p,@PathVariable("idD") Long idD) {
         iServiceDepartement.affecterEmplADep(p,idD);
    }
    @DeleteMapping("/deleteDepartment/{p}")
    public void removeDepartment(@PathVariable("p") Long p) {
        iServiceDepartement.removeDepartment(p);
    }
    @GetMapping("/getDepartment/{id}")
    public Departement retrieveDepartment(@PathVariable("id") Long id){
        return iServiceDepartement.retrieveDepartment(id);
    }
    @GetMapping("/getAllDepartments")
    public List<Departement> retrieveAllDepartments(){
        return iServiceDepartement.retrieveAllDepartments();
    }

    @GetMapping("/retrieveEmployeesByDepartement/{id}")
    public Set<Employee> retrieveEmployeesByDepartement(@PathVariable("id") Long id){
        return iServiceDepartement.retrieveEmployeesByDepartement(id);
    }
}
