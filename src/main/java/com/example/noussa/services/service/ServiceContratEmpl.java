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
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceContratEmpl implements IServiceContratEmpl {
    ContratEmplRepo contratEmplRepo;
    EmployeeRepo employeeRepo;

    @Override
    public ResponseEntity<Long> addContratEmployee(ContratEmployee contrat, Long id) {
        Employee emp = employeeRepo.findById(id).get();
        Set<ContratEmployee> anis = emp.getContratEmployees();
        if(anis.size()>0){
            ContratEmployee OldContrat = null;
            for (ContratEmployee ce : anis) {
                if (ce.getIsArchive() == false) {
                    OldContrat = ce;
                }
            }
            if (ContratEmployeeIsValid(contrat, emp)){
                contrat.setEmpl(emp);
                contratEmplRepo.save(contrat);
                return ResponseEntity.ok( contrat.getId_contrat_e());

            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(100L);
            }

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

       if(contrat.getDate_debut().after(contrat.getDate_fin()) ||
               nb>=1 && contrat.getTypeCE().equals(ContratEmployeeType.CIVP)
       ){
           return false;
       }
       return true;
    }

    @Override
    public ContratEmployee updateContratEmployee(ContratEmployee contrat) {
        return contratEmplRepo.save(contrat);
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
    public void retrieveAndUpdateStatusContrat() {
        List<ContratEmployee> contrat = retrieveAll();
        LocalDate localDate = LocalDate.now();
        for (int d = 0; d < contrat.size(); d++) {
            ContratEmployee S = contrat.get(d);
            long diff = S.getDate_fin().getTime() - localDate.getDayOfMonth();
            long diffs = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            if (diffs <= 0) {
                log.info("Contrat expiré " + LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));

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
