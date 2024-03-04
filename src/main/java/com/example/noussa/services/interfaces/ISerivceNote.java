package com.example.noussa.services.interfaces;

import com.example.noussa.models.Note;

import java.util.List;

public interface ISerivceNote {
    public void addNote(Note note);
    public Note updateNote(Note note);
    public void deleteNote(Long id);
    public Note getNote(Long id);
    List<Note> retrieveAll();
}