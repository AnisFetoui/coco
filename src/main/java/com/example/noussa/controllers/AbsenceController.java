package com.example.noussa.controllers;

import com.example.noussa.models.Absence;
import com.example.noussa.services.interfaces.IServiceAbsence;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/absence")
public class AbsenceController {
    IServiceAbsence iServiceAbsence;
    @PostMapping("/addAbsence/{id}")
    public void addAbsence(@RequestBody Absence p,@PathVariable ("id") Long id){
         iServiceAbsence.addAbsence(p,id);
    }
    @PutMapping("/updateAbsence/{id}")
    public void updateAbsence(@PathVariable ("id") Long id, @RequestBody Absence updatedAbsence) {
        iServiceAbsence.updateAbsence(id, updatedAbsence);
    }

    @DeleteMapping("/deleteAbsence/{p}")
    public void delete(@PathVariable("p") Long p) {
        iServiceAbsence.deleteAbsence(p);
    }

    @GetMapping("/getAbsences/{id}")
    public Set<Absence> getAbsencesByEmp(@PathVariable("id") Long id){
        return iServiceAbsence.getAbsencesByEmp(id);
    }

    @GetMapping("/getAbsencesToday")
    public List<Absence> retrieveAbsencesForToday(){
        return iServiceAbsence.retrieveAbsencesForToday();
    }
    @GetMapping("/retrieveAll")
    public List<Absence> retrieveAll(){
        return iServiceAbsence.retrieveAll();
    }

    @GetMapping("/getAbsence/{id}")
    public Absence getAbsence(@PathVariable("id") Long id){
        return iServiceAbsence.getAbsence(id);
    }
}
