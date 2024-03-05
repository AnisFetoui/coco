package com.example.noussa.controllers;

import com.example.noussa.models.Absence;
import com.example.noussa.models.Conge;
import com.example.noussa.services.interfaces.IServiceAbsence;
import com.example.noussa.services.interfaces.IServiceConge;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/Conge")
@CrossOrigin(origins = "http://localhost:4200")

public class congeController {
    IServiceConge iServiceConge;
    @PostMapping("/saveConge/{id}")
    public ResponseEntity<String> saveCongeAffectAEmpl(@RequestBody Conge p, @PathVariable ("id") Long id){
        return iServiceConge.saveConge(p,id);
    }

    @PutMapping("/updateConge/{id}")
    public ResponseEntity<String> updateConge(@PathVariable ("id") Long id,@RequestBody Conge p) throws Exception {
        return iServiceConge.updateConge(id,p);
    }
    @DeleteMapping("/deleteConge/{p}")
    public void delete(@PathVariable("p") Long p) {
        iServiceConge.deleteConge(p);
    }
    @GetMapping("/getConge/{id}")
    public Conge getConge(@PathVariable("id") Long id){
        return iServiceConge.getConge(id);
    }
    @GetMapping("/retrieveAll")
    public List<Conge> retrieveAll(){
        return iServiceConge.retrieveAll();
    }
}
