package com.example.noussa.services.service;

import com.example.noussa.models.Note;
import com.example.noussa.models.PerformanceEmployee;
import com.example.noussa.repos.PerfermanceEmplRepo;
import com.example.noussa.services.interfaces.ISerivcePerformance;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ServicePerformance implements ISerivcePerformance {

    PerfermanceEmplRepo performanceEmployeeRepository;

    public List<PerformanceEmployee> getAllPerformances() {
        return performanceEmployeeRepository.findAll();
    }

    public PerformanceEmployee getPerformanceById(Long id) {
        return performanceEmployeeRepository.findById(id).get();
    }

    public PerformanceEmployee savePerformance(PerformanceEmployee performance) {
        List<Note> notes = performance.getNotes();
        float somme = 0.0f;
        for (Note n : notes){
            somme += n.getNote();
        }
        float moyenne = somme/notes.size();
        if (moyenne < 0 || moyenne > 100) {
            throw new IllegalArgumentException("La note doit être comprise entre 0 et 100.");
        }else{
            performance.setCommentaire(calculatePerformanceGlobale(moyenne));
            performance.setNote(moyenne);
        }
        return performanceEmployeeRepository.save(performance);
    }

    public void deletePerformance(Long id) {
        performanceEmployeeRepository.deleteById(id);
    }

    private String calculatePerformanceGlobale(Float note) {
        // Logique de calcul de la performance globale (exemple simple : "Mauvaise", "Moyenne", "Bonne", etc.)
        if (note < 50) {
            return "Mauvaise";
        } else if (note < 80) {
            return "Moyenne";
        } else {
            return "Bonne";
        }
    }

}
