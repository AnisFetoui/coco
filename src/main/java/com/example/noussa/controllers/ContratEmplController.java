package com.example.noussa.controllers;

import com.example.noussa.models.Conge;
import com.example.noussa.models.ContratEmployee;
import com.example.noussa.services.interfaces.IServiceConge;
import com.example.noussa.services.interfaces.IServiceContratEmpl;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/contratEmpl")
public class ContratEmplController {
    IServiceContratEmpl iServiceContratEmpl;

    @PostMapping("/saveContratEmployee")
    public void addContratEmployee(@RequestBody ContratEmployee p){
        iServiceContratEmpl.addContratEmployee(p);
    }

    @PutMapping("/updateContratEmployee")
    public ContratEmployee updateContratEmployee(@RequestBody ContratEmployee p) throws Exception {
        return iServiceContratEmpl.updateContratEmployee(p);
    }

    @DeleteMapping("/deleteContratEmployee/{p}")
    public void deleteContratEmployee(@PathVariable("p") Long p) {
        iServiceContratEmpl.deleteContratEmployee(p);
    }
    @GetMapping("/getContratEmployee/{id}")
    public ContratEmployee getContratEmployee(@PathVariable("id") Long id){
        return iServiceContratEmpl.getContratEmployee(id);
    }
    @GetMapping("/retrieveAll")
    public List<ContratEmployee> retrieveAll(){
        return iServiceContratEmpl.retrieveAll();
    }

    @GetMapping("/countByIsArchiveIsFalseAndDate_debutBetween/{startDate}/{endDate}")
    public Integer countByIsArchiveIsFalseAndDate_debutBetween(@PathVariable("startDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date startDate,
                                                               @PathVariable("endDate")@DateTimeFormat(pattern="yyyy-MM-dd") Date endDate
    ){
        return iServiceContratEmpl.countByIsArchiveIsFalseAndDate_debutBetween(startDate,endDate);
    }
}
