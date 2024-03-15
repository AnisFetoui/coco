package com.example.noussa.services.service;

import com.example.noussa.models.Conge;
import com.example.noussa.models.CongeStatut;
import com.example.noussa.models.Departement;
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
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceConge implements IServiceConge {

    CongeRepo congeRepo;
    EmployeeRepo employeeRepo;

    public boolean isCongeRequestValid(Conge conge, Long employeeId) {
        Employee employee = employeeRepo.findById(employeeId).get();

        long differenceInMilliseconds = conge.getDate_fin().getTime() - conge.getDate_debut().getTime();
        long differenceInDays = TimeUnit.MILLISECONDS.toDays(differenceInMilliseconds);

        if (employee.getNbrJourConge() < 0 || employee.getNbrJourConge()<differenceInDays) {
            return false;
        }


        List<Conge> overlappingConges = congeRepo.findCongeInSamePeriodAndSameTeam(
                employee.getTeem().getTeam_id(),
                employee.getPosteEmployee(),
                conge.getDate_debut(),
                conge.getDate_fin());

        return overlappingConges.isEmpty();
    }

    public ResponseEntity<Long> saveConge(Conge conge,Long id){
        if (isCongeRequestValid(conge,id)) {
            Employee employee = employeeRepo.findById(id).get();
            conge.setEmployee(employee);
            long differenceInMilliseconds = conge.getDate_fin().getTime() - conge.getDate_debut().getTime();
            long differenceInDays = TimeUnit.MILLISECONDS.toDays(differenceInMilliseconds);
            employee.setNbrJourConge((int) (employee.getNbrJourConge() - differenceInDays));
            employeeRepo.save(employee);
            congeRepo.save(conge);
            return ResponseEntity.ok( conge.getId_conge());
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(conge.getId_conge()+100);
        }
    }
    public boolean isCongeRequestValidUpdate(Conge conge, Long employeeId, Long congeIdToUpdate) {
        Employee employee = employeeRepo.findById(employeeId).get();

        long differenceInMilliseconds = conge.getDate_fin().getTime() - conge.getDate_debut().getTime();
        long differenceInDays = TimeUnit.MILLISECONDS.toDays(differenceInMilliseconds);

        if (employee.getNbrJourConge() < 0 || employee.getNbrJourConge()<differenceInDays) {
            return false;
        }

        List<Conge> overlappingConges = congeRepo.findCongeInSamePeriodAndSameTeam(
                employee.getTeem().getTeam_id(),
                employee.getPosteEmployee(),
                conge.getDate_debut(),
                conge.getDate_fin());

        if (congeIdToUpdate != null) {
            overlappingConges = overlappingConges.stream()
                    .filter(c -> !c.getId_conge().equals(congeIdToUpdate))
                    .collect(Collectors.toList());
        }

        return overlappingConges.isEmpty();
    }

    @Override
    public ResponseEntity<Long> updateConge(Long id,Conge updatedConge) {
        Conge conge = congeRepo.findById(id).get();
        Employee employee = conge.getEmployee();
        if (isCongeRequestValidUpdate(updatedConge,employee.getId_employe(),id)) {
            conge.setCommentaire(updatedConge.getCommentaire());
            conge.setJustification(updatedConge.getJustification());
            conge.setDate_debut(updatedConge.getDate_debut());
            conge.setDate_fin(updatedConge.getDate_fin());
            conge.setStatutC(updatedConge.getStatutC());
            conge.setTypeC(updatedConge.getTypeC());
            congeRepo.save(conge);
            long differenceInMilliseconds = conge.getDate_fin().getTime() - conge.getDate_debut().getTime();
            long differenceInDays = TimeUnit.MILLISECONDS.toDays(differenceInMilliseconds);
            employee.setNbrJourConge((int) (employee.getNbrJourConge() - differenceInDays));
            employeeRepo.save(employee);
            return ResponseEntity.ok( conge.getId_conge());
        }else{
            log.error("Validation failed for Conge update. ID: {}", conge.getId_conge());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(conge.getId_conge()+100);
        }
    }
    @Override
    public void deleteConge(Long id) {
        Conge conge = congeRepo.findById(id).get();
        Employee employee = conge.getEmployee();
        long differenceInMilliseconds = conge.getDate_fin().getTime() - conge.getDate_debut().getTime();
        long differenceInDays = TimeUnit.MILLISECONDS.toDays(differenceInMilliseconds);
        employee.setNbrJourConge((int) (employee.getNbrJourConge()+differenceInDays));
        employeeRepo.save(employee);
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

    public List<Conge> searchCongesByStartingLetters(String StartingLetter) {
        return congeRepo.findByCommentaireStartingWithOrJustificationStartingWith(StartingLetter, StartingLetter);
    }

}
