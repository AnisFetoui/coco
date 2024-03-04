package com.example.noussa.repos;

import com.example.noussa.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepo extends JpaRepository<Note,Long> {


}
