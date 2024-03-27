package com.example.noussa.services.interfaces;

import com.example.noussa.models.ContratEmployee;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface IServiceContratEmpl {
    public ResponseEntity<?> addContratEmployee(ContratEmployee contrat, Long id);
    public ResponseEntity<?> updateContratEmployee(ContratEmployee Updatedcontrat, Long id);
    public void deleteContratEmployee(Long id);
    public ContratEmployee getContratEmployee(Long id);
    List<ContratEmployee> retrieveAll();
    public Integer countByIsArchiveIsFalseAndDate_debutBetween(Date startDate, Date endDate);

}
