package com.example.noussa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Absence implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_absence ;
    private String motif ;
    private LocalDate date;
    private boolean validee;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    Employee emp;
    @OneToMany(mappedBy = "absence")
    List<FileAnis> fileAnis;

}
