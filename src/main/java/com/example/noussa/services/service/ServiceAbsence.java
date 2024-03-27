package com.example.noussa.services.service;

import com.example.noussa.models.Absence;
import com.example.noussa.models.Employee;
import com.example.noussa.models.FileAnis;
import com.example.noussa.repos.AbsenceRepo;
import com.example.noussa.repos.EmployeeRepo;
import com.example.noussa.repos.FileRepo;
import com.example.noussa.services.interfaces.IServiceAbsence;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceAbsence implements IServiceAbsence {

    AbsenceRepo absenceRepo;
    EmployeeRepo employeeRepo;
    FileRepo fileRepo;

//    @Override
//    public void addAbsence(Absence absence, Long id) {
//        Employee emp = employeeRepo.findById(id).get();
//        absence.setEmp(emp);
//        absenceRepo.save(absence);
//
//    }

    @Override
    public ResponseEntity<Long> addAbsence(Absence absence, Long id) {
        Employee emp = employeeRepo.findById(id).get();
        absence.setEmp(emp);
        absenceRepo.save(absence);
        return ResponseEntity.ok( absence.getId_absence());

    }
//    @Override
//    public ResponseEntity<?> uploadFile( MultipartFile file,Long id) {
//        Absence absence = absenceRepo.findById(id).get();
//
//        try {
//            FileAnis fileEntity = FileAnis.builder().justification(file.getBytes()).contentType(file.getContentType())
//                    .filename(file.getName()).build();
//            absence.setFileAnis(fileEntity);
//            fileRepo.save(fileEntity);
//            absenceRepo.save(absence);
//            String message = "File uploaded successfully!";
//            return ResponseEntity.status(HttpStatus.CREATED).body(message);
//        } catch (IOException e) {
//            return ResponseEntity.status(500).build();
//        }
//    }
//    public ResponseEntity<List<FileAnis>> getFile() {
//        List<FileAnis> files = fileRepo.findAll();
//        return ResponseEntity.ok(files);
//    }
//    public ResponseEntity<?> findjustifByAbsence(Long id ) {
//        Absence absence = absenceRepo.findById(id).get();
//        FileAnis fileEntity = absence.getFileAnis();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType(fileEntity.getContentType()));
//        headers.setContentDisposition(ContentDisposition.attachment().filename(fileEntity.getFilename()).build());
//        ByteArrayResource resource = new ByteArrayResource(fileEntity.getJustification());
//        return ResponseEntity.ok().headers(headers).body(resource);
////        return ResponseEntity.ok(absence.getFileAnis()).getBody();
//    }

//    public ResponseEntity<?> downloadFile( Long id) {
//        FileAnis fileEntity = fileRepo.findById(id).orElse(null);
//        if (fileEntity != null) {
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.parseMediaType(fileEntity.getContentType()));
//            headers.setContentDisposition(ContentDisposition.attachment().filename(fileEntity.getFilename()).build());
//            ByteArrayResource resource = new ByteArrayResource(fileEntity.getJustification());
//            return ResponseEntity.ok().headers(headers).body(resource);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }



    @Override
    public void updateAbsence(Long id, Absence updatedAbsence) {

        Absence existingAbsence = absenceRepo.findById(id).get();

        existingAbsence.setMotif(updatedAbsence.getMotif());
//        existingAbsence.setJustification(updatedAbsence.getJustification());
        existingAbsence.setDate(updatedAbsence.getDate());
        existingAbsence.setValidee(updatedAbsence.isValidee());

        absenceRepo.save(existingAbsence);
    }

    @Override
    public void deleteAbsence(Long id) {
    absenceRepo.deleteById(id);
    }

    @Override
    public Set<Absence> getAbsencesByEmp(Long id) {
        Employee emp = employeeRepo.findById(id).get();
        return emp.getAbsences();
    }
    @Override
    public Absence getAbsence(Long id) {
        return absenceRepo.findById(id).get();
    }


    public List<Absence> retrieveAbsencesForToday() {
        LocalDate currentDate = LocalDate.now();  // Current local date
        return absenceRepo.findByDate(currentDate);
    }
    public List<Absence> retrieveAll() {
        return absenceRepo.findAll();
    }


//    public String verifNbreAbsencByempl(Long id){
//        Employee employee = employeeRepo.findById(id).get();
////        User user = userRepo.findById(employee.getUserId()).get();
//        Set<Absence> absences = employee.getAbsences();
//        if(absences.size()>=5){
//            return "this employe "+user.getNom()+"has many absence";
//        } else if (absences.size()<5 && absences.size()>1) {
//            return "this employe "+user.getNom()+"has few absence";
//
//        }else {
//            return "this employe "+user.getNom()+"has no absence";
//        }
//    }
}
