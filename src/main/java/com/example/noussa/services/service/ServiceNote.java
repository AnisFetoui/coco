package com.example.noussa.services.service;

import com.example.noussa.models.Employee;
import com.example.noussa.models.Note;
import com.example.noussa.models.PerformanceEmployee;
import com.example.noussa.repos.EmployeeRepo;
import com.example.noussa.repos.NoteRepo;
import com.example.noussa.repos.PerfermanceEmplRepo;
import com.example.noussa.services.interfaces.ISerivceNote;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceNote implements ISerivceNote {

    NoteRepo noteRepo;
    EmployeeRepo employeeRepo;
    PerfermanceEmplRepo perfermanceEmplRepo;

    @Override
    public void addNote(Note note, Long id) {
        Employee employee = employeeRepo.findById(id).get();
        note.setEmployeee(employee);
        noteRepo.save(note);
        Employee employe = employeeRepo.findById(id).get();
//        addPerfermance(id);
    }
    public void addPerfermance(Long id){
        Employee employee = employeeRepo.findById(id).get();
        PerformanceEmployee performanceEmployee;
        if(employee.getPerformanceEmployee()!=null){
             performanceEmployee = employee.getPerformanceEmployee();
        }else
        {
             performanceEmployee= PerformanceEmployee.builder().build();
        }

        Set<Note> notes = employee.getNotes();
        float sum = 0.0f;

        for (Note n : notes) {
            sum += n.getNote();
        }
        float moyenne = 0.0f;

         moyenne = sum / notes.size();


        if (moyenne < 0 || moyenne > 100) {
            throw new IllegalArgumentException("La note doit être comprise entre 0 et 100.");
        } else {
            performanceEmployee.setCommentaire(calculatePerformanceGlobale(moyenne));
            performanceEmployee.setMoyenne(moyenne);
        }

        perfermanceEmplRepo.save(performanceEmployee);
        employee.setPerformanceEmployee(performanceEmployee);
        employeeRepo.save(employee);
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

    @Override
    public Note updateNote(Long id,Note updatedNote) {
        Note note = noteRepo.findById(id).get();
        note.setNote(updatedNote.getNote());
       return noteRepo.save(note);
    }

    @Override
    public void deleteNote(Long id) {
        noteRepo.deleteById(id);

    }

    @Override
    public Note getNote(Long id) {
        return noteRepo.findById(id).get();
    }

    @Override
    public List<Note> retrieveAll() {
        return noteRepo.findAll();
    }
}
