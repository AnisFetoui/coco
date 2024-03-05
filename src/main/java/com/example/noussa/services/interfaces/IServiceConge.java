package com.example.noussa.services.interfaces;

import com.example.noussa.models.Conge;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IServiceConge {
    public ResponseEntity<String> saveConge(Conge conge, Long id);
    public ResponseEntity<String> updateConge(Long id,Conge absence);
    public void deleteConge(Long id);
    public Conge getConge(Long id);
    List<Conge> retrieveAll();

}
