package com.example.noussa.services.service;

import com.example.noussa.models.ContratEmployee;
import com.example.noussa.repos.ContratEmplRepo;
import com.example.noussa.services.interfaces.IServiceContratEmpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceContratEmpl implements IServiceContratEmpl {
    ContratEmplRepo contratEmplRepo;

    @Override
    public void addContratEmployee(ContratEmployee contrat) {
        contratEmplRepo.save(contrat);
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
