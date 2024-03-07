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
import java.util.concurrent.TimeUnit;

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
        for (Conge teamConge : teamConges) {
            log.info("Conge: {}", conge);
            log.info("Team Conge: {}", teamConge);
            log.info("Employee NbrJourConge: {}", employee.getNbrJourConge());

            if (conge.getDate_debut().after(conge.getDate_fin()) ||
                    conge.getDate_fin().after(teamConge.getDate_debut()) && conge.getDate_debut().before(teamConge.getDate_fin()) ||
            employee.getNbrJourConge()<0) {
                return false;
            }
        }
            return true;
        }else{
            return true;
        }
    }


    public ResponseEntity<Long> saveConge(Conge conge,Long id){
        if (isCongeRequestValid(conge,id)) {
            Employee employee = employeeRepo.findById(id).get();
            conge.setEmployee(employee);
            long differenceInMilliseconds = conge.getDate_fin().getTime() - conge.getDate_debut().getTime();
            long differenceInDays = TimeUnit.MILLISECONDS.toDays(differenceInMilliseconds);
            employee.setNbrJourConge((int) (employee.getNbrJourConge() - differenceInDays));
//            conge.setStatutC(CongeStatut.PENDING);
            congeRepo.save(conge);
            return ResponseEntity.ok( conge.getId_conge());
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(conge.getId_conge()+100);
        }
    }
    @Override
    public ResponseEntity<Long> updateConge(Long id,Conge updatedConge) {
        Conge conge = congeRepo.findById(id).get();
        if (isCongeRequestValid(updatedConge,conge.getEmployee().getId_employe())) {
            conge.setCommentaire(updatedConge.getCommentaire());
            conge.setJustification(updatedConge.getJustification());
            conge.setDate_debut(updatedConge.getDate_debut());
            conge.setDate_fin(updatedConge.getDate_fin());
            conge.setStatutC(updatedConge.getStatutC());
            conge.setTypeC(updatedConge.getTypeC());
            congeRepo.save(conge);
            return ResponseEntity.ok( conge.getId_conge());
        }else{
            log.error("Validation failed for Conge update. ID: {}", conge.getId_conge());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(conge.getId_conge()+100);
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
