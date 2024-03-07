package com.example.noussa.controllers;

import com.example.noussa.models.Absence;
import com.example.noussa.models.Employee;
import com.example.noussa.models.PosteEmployee;
import com.example.noussa.services.interfaces.IServiceAbsence;
import com.example.noussa.services.interfaces.IServiceEmployee;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
    IServiceEmployee iServiceEmployee;
    @PostMapping("/addEmployee/{id}")
    public void addEmployeeEtAffectDepartement(@RequestBody Employee p,@PathVariable("id") Long id){
        iServiceEmployee.addEmployeeEtAffectDepartement(p,id);
    }
    @PostMapping("/assignEmToDep/{e}/{d}")
    public void assignEmToDep(@PathVariable("e") Long e,@PathVariable("d") Long d){
        iServiceEmployee.assignEmToDep(e,d);
    }

    @PutMapping("/updateEmployee/{e}")
    public ResponseEntity<Long> updateEmployee(@PathVariable("e") Long e, @RequestBody Employee p) {
        return iServiceEmployee.updateEmployee(e,p);
    }

    @DeleteMapping("/DeleteEmployee/{p}")
    public void delete(@PathVariable("p") Long p) {
        iServiceEmployee.deleteEmployee(p);
    }

    @GetMapping("/retrieveAll")
    public List<Employee> retrieveAll(){
        return iServiceEmployee.retrieveAll();
    }

    @GetMapping("/getEmployee/{p}")
        public Employee getEmployee(@PathVariable("p") Long p){
        return iServiceEmployee.getEmployee(p);
    }

    @GetMapping("/findAllByPosteEmployee/{posteEmployee}")
    public List<Employee> findAllByPosteEmployee(@PathVariable("posteEmployee") PosteEmployee posteEmployee){
        return iServiceEmployee.findAllByPosteEmployee(posteEmployee);
    }


}
