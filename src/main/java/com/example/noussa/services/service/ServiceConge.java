package com.example.noussa.services.service;

import com.example.noussa.models.Conge;
import com.example.noussa.models.CongeStatut;
import com.example.noussa.models.Employee;
import com.example.noussa.repos.CongeRepo;
import com.example.noussa.repos.EmployeeRepo;
import com.example.noussa.services.interfaces.IServiceConge;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceConge implements IServiceConge {

    CongeRepo congeRepo;
    EmployeeRepo employeeRepo;

    public boolean isCongeRequestValid(Conge conge,Long id) {
        List<Conge> anis = congeRepo.findAll();
        Employee employee = employeeRepo.findById(id).get();
        if(anis.size()>0){
        List<Conge> teamConges = congeRepo.findTeamConges(employee.getTeem().getTeam_id(), conge.getDate_debut(), conge.getDate_fin());

        if (conge.getDate_debut().after(conge.getDate_fin())) {
            return false;
        }

        for (Conge teamConge : teamConges) {
            if (conge.getDate_fin().after(teamConge.getDate_debut()) && conge.getDate_debut().before(teamConge.getDate_fin())) {
                return false;
            }
        }
            return true;
        }else{
            return true;
        }


    }


    public ResponseEntity<String> saveConge(Conge conge,Long id){
        if (isCongeRequestValid(conge,id)) {
            Employee employee = employeeRepo.findById(id).get();
            conge.setEmployee(employee);
            conge.setStatutC(CongeStatut.PENDING);
            congeRepo.save(conge);
            return ResponseEntity.ok("Conge submitted with success");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something wrong");
        }
    }
    @Override
    public ResponseEntity<String> updateConge(Long id,Conge updatedConge) {
        Conge conge = congeRepo.findById(id).get();
        if (isCongeRequestValid(updatedConge,conge.getEmployee().getId_employe())) {
            conge.setCommentaire(updatedConge.getCommentaire());
            conge.setJustification(updatedConge.getJustification());
            conge.setDate_debut(updatedConge.getDate_debut());
            conge.setDate_fin(updatedConge.getDate_fin());
            conge.setStatutC(updatedConge.getStatutC());
            conge.setTypeC(updatedConge.getTypeC());
            congeRepo.save(updatedConge);
            return ResponseEntity.ok("Conge updated with success");
        }else{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something wrong");
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
