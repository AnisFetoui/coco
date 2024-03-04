package com.example.noussa.services.interfaces;

import com.example.noussa.models.PosteEmployee;
import com.example.noussa.models.SalaireEmployee;

import java.util.List;

public interface IServiceSalaire {
    public void addSalaire(SalaireEmployee absence);
    public SalaireEmployee updateSalaire(SalaireEmployee absence);
    public void deleteSalaire(Long id);
    public SalaireEmployee getSalaire(Long id);
    List<SalaireEmployee> retrieveAll();
    public List<SalaireEmployee> findBySalaireBaseGreaterThan(Float minSalaire);

    public Float calculateAverageSalaryByPoste(PosteEmployee posteEmployee);

}
