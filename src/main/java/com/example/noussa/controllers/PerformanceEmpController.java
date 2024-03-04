package com.example.noussa.controllers;

import com.example.noussa.models.Absence;
import com.example.noussa.models.PerformanceEmployee;
import com.example.noussa.services.interfaces.ISerivcePerformance;
import com.example.noussa.services.interfaces.IServiceAbsence;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/perfomanceEmpl")
public class PerformanceEmpController {
    ISerivcePerformance iSerivcePerformance;
    @PostMapping("/addperfomanceEmpl")
    public void savePerformance(@RequestBody PerformanceEmployee p){
        iSerivcePerformance.savePerformance(p);
    }
    @PutMapping("/updatePerformance")
    public PerformanceEmployee updatePerformance(@RequestBody PerformanceEmployee p) throws Exception {
        return iSerivcePerformance.savePerformance(p);
    }
    @DeleteMapping("/deletePerformance/{p}")
    public void deletePerformance(@PathVariable("p") Long p) {
        iSerivcePerformance.deletePerformance(p);
    }
    @GetMapping("/getPerformanceById/{id}")
    public PerformanceEmployee getPerformanceById(@PathVariable("id") Long id){
        return iSerivcePerformance.getPerformanceById(id);
    }
    @GetMapping("/retrieveAbsencesForToday")
    public List<PerformanceEmployee> retrieveAbsencesForToday(){
        return iSerivcePerformance.getAllPerformances();
    }
}
