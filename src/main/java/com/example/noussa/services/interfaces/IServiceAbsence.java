package com.example.noussa.services.interfaces;

import com.example.noussa.models.Absence;
import com.example.noussa.models.FileAnis;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface IServiceAbsence {
    public ResponseEntity<Long> addAbsence(Absence absence, Long id);
//    public ResponseEntity<?> uploadFile(MultipartFile file,Long id);
//    public ResponseEntity<?> downloadFile( Long id);
//    public ResponseEntity<List<FileAnis>> getFile();
//    public ResponseEntity<?> findjustifByAbsence(Long id );
    public void updateAbsence(Long absence, Absence updatedAbsence);
    public void deleteAbsence(Long id);
    public Set<Absence> getAbsencesByEmp(Long id);
    public List<Absence> retrieveAbsencesForToday();
    public List<Absence> retrieveAll();
    public Absence getAbsence(Long id);

}
