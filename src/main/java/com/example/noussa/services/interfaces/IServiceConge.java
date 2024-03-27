package com.example.noussa.services.interfaces;

import com.example.noussa.models.Conge;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IServiceConge {
    public ResponseEntity<?> saveConge(Conge conge, Long id);
    public ResponseEntity<?> updateConge(Long id,Conge absence);
    public void deleteConge(Long id);
    public Conge getConge(Long id);
    List<Conge> retrieveAll();
    public List<Conge> searchCongesByStartingLetters(String StartingLetter);
}
