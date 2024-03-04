package com.example.noussa.services.service;

import com.example.noussa.models.Note;
import com.example.noussa.repos.NoteRepo;
import com.example.noussa.services.interfaces.ISerivceNote;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceNote implements ISerivceNote {

    NoteRepo noteRepo;
    @Override
    public void addNote(Note note) {
        noteRepo.save(note);
    }

    @Override
    public Note updateNote(Note note) {
       return noteRepo.save(note);
    }

    @Override
    public void deleteNote(Long id) {
        noteRepo.deleteById(id);

    }

    @Override
    public Note getNote(Long id) {
        return noteRepo.findById(id).get();
    }

    @Override
    public List<Note> retrieveAll() {
        return noteRepo.findAll();
    }
}
