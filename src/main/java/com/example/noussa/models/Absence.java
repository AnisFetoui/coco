package com.example.noussa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Absence implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_absence ;
    private String motif ;
    private String justification;
    private LocalDate date;
    private boolean validee;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    Employee emp;

}
