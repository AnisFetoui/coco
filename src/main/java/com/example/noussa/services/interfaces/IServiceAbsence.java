package com.example.noussa.services.interfaces;

import com.example.noussa.models.Absence;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface IServiceAbsence {
    public void addAbsence(Absence absence, Long id);
    public ResponseEntity<?> uploadFile(MultipartFile file,Long id);
    public ResponseEntity<?> downloadFile( Long id);
    public void updateAbsence(Long absence, Absence updatedAbsence);
    public void deleteAbsence(Long id);
    public Set<Absence> getAbsencesByEmp(Long id);
    public List<Absence> retrieveAbsencesForToday();
    public List<Absence> retrieveAll();
    public Absence getAbsence(Long id);

}
