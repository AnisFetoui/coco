package com.example.noussa.services.service;

import com.example.noussa.models.*;
import com.example.noussa.repos.EmployeeRepo;
import com.example.noussa.repos.SalaireEmplRepo;
import com.example.noussa.services.interfaces.IServiceSalaire;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceSalaire implements IServiceSalaire {

    private SalaireEmplRepo salaireEmployeeService;
    EmployeeRepo employeeRepo;

    @Override
    public void addSalaire(SalaireEmployee salaire) {
        if (salaire.getSalaire_base() < 0) {
            throw new IllegalArgumentException("Le salaire de base ne peut pas être négatif.");
        }

        Float totalSalaire = calculateTotalSalaire(salaire);
        salaire.setTotal_salaire(totalSalaire);

        List<SalaireEmployee> salaireEmployees = salaireEmployeeService.findAll();
        if(salaireEmployees!=null){
            for (SalaireEmployee se : salaireEmployees){
                se.setIsArchive(true);
            }
        }
        salaire.setIsArchive(false);
        salaireEmployeeService.save(salaire);
    }

    private Float calculateTotalSalaire(SalaireEmployee salaire) {
        return salaire.getSalaire_base() + salaire.getPrime() + salaire.getMontant_heures_supplementaires();
    }

    @Override
    public SalaireEmployee updateSalaire(SalaireEmployee salaire) {
        Float totalSalaire = calculateTotalSalaire(salaire);
        salaire.setTotal_salaire(totalSalaire);
        salaire.setIsArchive(false);

        return salaireEmployeeService.save(salaire);

    }

    @Override
    public void deleteSalaire(Long id) {
        salaireEmployeeService.deleteById(id);

    }

    @Override
    public SalaireEmployee getSalaire(Long id) {
        return salaireEmployeeService.findById(id).get();

    }

    @Override
    public List<SalaireEmployee> retrieveAll() {
       return salaireEmployeeService.findAll();
    }

    public List<SalaireEmployee> findBySalaireBaseGreaterThan(Float minSalaire) {
        return salaireEmployeeService.findByIsArchiveAndSalaireBaseGreaterThan(false,minSalaire);
    }

    //juste st7a9itha fel fct calculateAverageSalaryByPoste
    public List<Employee> findAllByPosteEmployee(PosteEmployee posteEmployee) {
        List<Employee> anis = new ArrayList<>();
        List<Employee> noussa = employeeRepo.findAll();
        for(Employee em : noussa){
            if(em.getPosteEmployee().equals(posteEmployee)){
                anis.add(em);
            }
        }
        return anis;
    }
    public Float calculateAverageSalaryByPoste(PosteEmployee posteEmployee) {
        List<Employee> employees = findAllByPosteEmployee(posteEmployee);
        float nb=0;
        for(Employee em : employees) {
            Set<SalaireEmployee> salaireEmployees = em.getSalaireEmployees();
            for (SalaireEmployee se : salaireEmployees) {
                if (se.getIsArchive() == false) {
                    nb += se.getTotal_salaire();
                }
            }

        }
        return nb / employees.size();
    }






}
