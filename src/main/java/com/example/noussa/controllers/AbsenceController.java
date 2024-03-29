package com.example.noussa.controllers;

import com.example.noussa.models.Absence;
import com.example.noussa.models.FileAnis;
import com.example.noussa.services.interfaces.IServiceAbsence;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/absence")
public class AbsenceController {
    IServiceAbsence iServiceAbsence;
    @PostMapping("/addAbsence/{id}")
    public ResponseEntity<Long> addAbsence(@RequestBody Absence p, @PathVariable ("id") Long id){
        return iServiceAbsence.addAbsence(p,id);
    }
//    @PostMapping("/upload/{id}")
//    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable ("id") Long id){
//      return  iServiceAbsence.uploadFile(file,id);
//    }
//    @GetMapping("/files")
//    public ResponseEntity<List<FileAnis>> getFile() {
//        return iServiceAbsence.getFile();
//    }
//    @GetMapping("/OneFile/{id}")
//    public ResponseEntity<?> getFile( @PathVariable ("id") Long id) {
//        return iServiceAbsence.findjustifByAbsence(id);
//    }
//    @GetMapping("/download/{id}")
//    public ResponseEntity<?> downloadFile(@PathVariable Long id) {
//        return iServiceAbsence.downloadFile(id);
//    }
    @PutMapping("/updateAbsence/{id}")
    public void updateAbsence(@PathVariable ("id") Long id, @RequestBody Absence updatedAbsence) {
        iServiceAbsence.updateAbsence(id, updatedAbsence);
    }

    @DeleteMapping("/deleteAbsence/{p}")
    public void delete(@PathVariable("p") Long p) {
        iServiceAbsence.deleteAbsence(p);
    }

    @GetMapping("/getAbsences/{id}")
    public Set<Absence> getAbsencesByEmp(@PathVariable("id") Long id){
        return iServiceAbsence.getAbsencesByEmp(id);
    }

    @GetMapping("/getAbsencesToday")
    public List<Absence> retrieveAbsencesForToday(){
        return iServiceAbsence.retrieveAbsencesForToday();
    }
    @GetMapping("/retrieveAll")
    public List<Absence> retrieveAll(){
        return iServiceAbsence.retrieveAll();
    }

    @GetMapping("/getAbsence/{id}")
    public Absence getAbsence(@PathVariable("id") Long id){
        return iServiceAbsence.getAbsence(id);
    }
}
