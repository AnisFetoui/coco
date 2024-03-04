package com.example.noussa.services.service;

import com.example.noussa.models.Conge;
import com.example.noussa.models.Employee;
import com.example.noussa.repos.CongeRepo;
import com.example.noussa.services.interfaces.IServiceConge;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceConge implements IServiceConge {

    CongeRepo congeRepo;

    public boolean isCongeRequestValid(Conge conge) {
        List<Conge> teamConges = congeRepo.findTeamConges(conge.getEmployee().getTeam().getTeam_id(), conge.getDate_debut(), conge.getDate_fin());

        if (conge.getDate_debut().after(conge.getDate_fin())) {
            return false;
        }

        for (Conge teamConge : teamConges) {
            if (conge.getDate_fin().after(teamConge.getDate_debut()) && conge.getDate_debut().before(teamConge.getDate_fin())) {
                return false;
            }
        }
        return true;
    }


    public String saveConge(Conge conge){
        if (isCongeRequestValid(conge)) {
            Employee employee = conge.getEmployee();
            conge.setEmployee(employee);
            congeRepo.save(conge);
            return "conge submitted with success";
        }else{
            return "something wrong";
        }
    }
    @Override
    public String updateConge(Long id,Conge updatedConge) {
        Conge conge = congeRepo.findById(id).get();
        if (isCongeRequestValid(updatedConge)) {
            conge.setCommentaire(updatedConge.getCommentaire());
            conge.setJustification(updatedConge.getJustification());
            conge.setDate_debut(updatedConge.getDate_debut());
            conge.setDate_fin(updatedConge.getDate_fin());
            conge.setStatutC(updatedConge.getStatutC());
            conge.setTypeC(updatedConge.getTypeC());
            congeRepo.save(updatedConge);
            return "conge submitted with success";
        }else{
            return "something wrong";
        }
    }

    @Override
    public void deleteConge(Long id) {
        congeRepo.deleteById(id);
    }

    @Override
    public Conge getConge(Long id) {

        return congeRepo.findById(id).get();
    }

    @Override
    public List<Conge> retrieveAll() {
        return congeRepo.findAll();
    }
}
