package com.example.noussa.controllers;

import com.example.noussa.models.PerformanceEmployee;
import com.example.noussa.models.PosteEmployee;
import com.example.noussa.models.SalaireEmployee;
import com.example.noussa.services.interfaces.ISerivcePerformance;
import com.example.noussa.services.interfaces.IServiceSalaire;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/SalaireEmpl")
public class SalaireEmplController {
    IServiceSalaire iServiceSalaire;
    @PostMapping("/addpSalaireEmpl")
    public void addSalaire(@RequestBody SalaireEmployee p){
        iServiceSalaire.addSalaire(p);
    }
    @PutMapping("/updateSalaire")
    public SalaireEmployee updateSalaire(@RequestBody SalaireEmployee p) throws Exception {
        return iServiceSalaire.updateSalaire(p);
    }
    @DeleteMapping("/deleteSalaire/{p}")
    public void deleteSalaire(@PathVariable("p") Long p) {
        iServiceSalaire.deleteSalaire(p);
    }
    @GetMapping("/getSalaire/{id}")
    public SalaireEmployee getSalaire(@PathVariable("id") Long id){
        return iServiceSalaire.getSalaire(id);
    }
    @GetMapping("/retrieveAll")
    public List<SalaireEmployee> retrieveAll(){
        return iServiceSalaire.retrieveAll();
    }

    @GetMapping("/findBySalaireBaseGreaterThan/{minSalaire}")
    public List<SalaireEmployee> retrieveAll(@PathVariable("minSalaire") Float minSalaire){
        return iServiceSalaire.findBySalaireBaseGreaterThan(minSalaire);
    }
    @GetMapping("/calculateAverageSalaryByPoste/{posteEmployee}")
    public Float calculateAverageSalaryByPoste(@PathVariable("posteEmployee") PosteEmployee posteEmployee){
        return iServiceSalaire.calculateAverageSalaryByPoste(posteEmployee);
    }
}
