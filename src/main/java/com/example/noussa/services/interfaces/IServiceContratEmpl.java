package com.example.noussa.services.interfaces;

import com.example.noussa.models.ContratEmployee;

import java.util.Date;
import java.util.List;

public interface IServiceContratEmpl {
    public void addContratEmployee(ContratEmployee contrat);
    public ContratEmployee updateContratEmployee(ContratEmployee absence);
    public void deleteContratEmployee(Long id);
    public ContratEmployee getContratEmployee(Long id);
    List<ContratEmployee> retrieveAll();
    public Integer countByIsArchiveIsFalseAndDate_debutBetween(Date startDate, Date endDate);

}
