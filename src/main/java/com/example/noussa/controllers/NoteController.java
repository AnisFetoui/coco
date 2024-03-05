package com.example.noussa.controllers;


import com.example.noussa.models.Note;
import com.example.noussa.services.interfaces.ISerivceNote;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Note")
@CrossOrigin(origins = "http://localhost:4200")

public class NoteController {
    ISerivceNote iSerivceNote;

    @PostMapping("/addNote")
    public void addNote(@RequestBody Note p){
        iSerivceNote.addNote(p);
    }
    @PutMapping("/updateNote/{id}")
    public Note updateNote(@PathVariable("id") Long id,@RequestBody Note p){
        return iSerivceNote.updateNote(id,p);
    }
    @DeleteMapping("/deleteNote/{p}")
    public void deleteNote(@PathVariable("p") Long p) {
        iSerivceNote.deleteNote(p);
    }
    @GetMapping("/getNote/{id}")
    public Note getNote(@PathVariable("id") Long id){
        return iSerivceNote.getNote(id);
    }
    @GetMapping("/retrieveAll")
    public List<Note> retrieveAll(){
        return iSerivceNote.retrieveAll();
    }

}
