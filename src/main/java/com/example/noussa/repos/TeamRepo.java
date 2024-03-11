package com.example.noussa.repos;

import com.example.noussa.models.Note;
import com.example.noussa.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepo extends JpaRepository<Team,Long> {


}
