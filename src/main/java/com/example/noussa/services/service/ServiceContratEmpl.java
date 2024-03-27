package com.example.noussa.services.service;

import com.example.noussa.models.Conge;
import com.example.noussa.models.ContratEmployee;
import com.example.noussa.models.ContratEmployeeType;
import com.example.noussa.models.Employee;
import com.example.noussa.repos.ContratEmplRepo;
import com.example.noussa.repos.EmployeeRepo;
import com.example.noussa.services.interfaces.IServiceContratEmpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceContratEmpl implements IServiceContratEmpl {
    ContratEmplRepo contratEmplRepo;
    EmployeeRepo employeeRepo;

    @Override
    public ResponseEntity<?> addContratEmployee(ContratEmployee contrat, Long id) {
        Employee emp = employeeRepo.findById(id).get();
        Set<ContratEmployee> anis = emp.getContratEmployees();
        ContratEmployee OldContrat = null;
        if(anis.size()>0){
            if (ContratEmployeeIsValid(contrat, emp)){
                contrat.setEmpl(emp);
                contratEmplRepo.save(contrat);
                return ResponseEntity.ok( contrat.getId_contrat_e());

            }else{
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Invalid Contrat request. Please check your inputs.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);            }
        }
        if(contrat.getDate_debut().isAfter(contrat.getDate_fin())){
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid Contrat request. Please check your inputs.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
            contrat.setEmpl(emp);
        contratEmplRepo.save(contrat);
        return ResponseEntity.ok( contrat.getId_contrat_e());

    }
    public boolean ContratEmployeeIsValid(ContratEmployee contrat, Employee em){
        Set<ContratEmployee> oldContracts = em.getContratEmployees();
        int nb =0;
        for(ContratEmployee ce : oldContracts){
            if(ce.getTypeCE().equals(ContratEmployeeType.CIVP)){
                nb+=1;
            }
        }

       if(contrat.getDate_debut().isAfter(contrat.getDate_fin()) ||
               nb>=1 && contrat.getTypeCE().equals(ContratEmployeeType.CIVP)
       ){
           return false;
       }
       return true;
    }

    @Override
    public ResponseEntity<?> updateContratEmployee(ContratEmployee Updatedcontrat, Long id) {
        ContratEmployee contratEmployee = contratEmplRepo.findById(id).get();
        Employee emp = contratEmployee.getEmpl();
        if (ContratEmployeeIsValid(Updatedcontrat, emp)){
            contratEmployee.setRib(Updatedcontrat.getRib());
            contratEmployee.setDate_debut(Updatedcontrat.getDate_debut());
            contratEmployee.setDate_fin(Updatedcontrat.getDate_fin());
            contratEmployee.setDuree_hebdomadaire(Updatedcontrat.getDuree_hebdomadaire());
            contratEmployee.setNumeroSecuriteSociale(Updatedcontrat.getNumeroSecuriteSociale());            contratEmployee.setDuree_hebdomadaire(Updatedcontrat.getDuree_hebdomadaire());
            contratEmployee.setTypeCE(Updatedcontrat.getTypeCE());
            contratEmployee.setIsArchive(Updatedcontrat.getIsArchive());
            contratEmplRepo.save(contratEmployee);
            return ResponseEntity.ok( contratEmployee.getId_contrat_e());
        }else{
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid Contrat request. Please check your inputs.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);        }

    }

    @Override
    public void deleteContratEmployee(Long id) {
        contratEmplRepo.deleteById(id);
    }

    @Override
    public ContratEmployee getContratEmployee(Long id) {
        return contratEmplRepo.findById(id).get();
    }

    @Override
    public List<ContratEmployee> retrieveAll() {
        return contratEmplRepo.findAll();
    }

    @Scheduled(cron = "0 0 1 ? * *")
//    @Scheduled(fixedDelay = 10000)

    public void retrieveAndUpdateStatusContrat() {
        List<ContratEmployee> contrat = retrieveAll();
        LocalDate localDate = LocalDate.now();
        for (int d = 0; d < contrat.size(); d++) {
            ContratEmployee S = contrat.get(d);
            long aa =   ChronoUnit.DAYS.between( localDate, S.getDate_fin());
            if (aa <= 0) {
                log.info("Contrat expiré. Contract ID: {}. Timestamp: {}", S.getId_contrat_e(), LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));

                S.setIsArchive(true);
                contratEmplRepo.save(S);
            }
        }
    }
    @Override
    public Integer countByIsArchiveIsFalseAndDate_debutBetween(Date startDate, Date endDate) {
        return contratEmplRepo.countByIsArchiveIsFalseAndDateDebutBetween(startDate,endDate);
    }
}
