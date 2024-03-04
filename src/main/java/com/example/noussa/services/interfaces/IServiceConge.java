package com.example.noussa.services.interfaces;

import com.example.noussa.models.Conge;

import java.util.List;

public interface IServiceConge {
    public String saveConge(Conge absence);
    public String updateConge(Long id,Conge absence);
    public void deleteConge(Long id);
    public Conge getConge(Long id);
    List<Conge> retrieveAll();

}
