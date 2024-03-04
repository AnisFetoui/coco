package com.example.noussa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PerformanceEmployee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_performance ;
    private float note;
    private String commentaire ;

    @OneToOne(mappedBy = "performanceEmployee")
    Employee employee;
    @OneToMany(mappedBy = "performanceEmpl" ,fetch = FetchType.EAGER)
    List<Note> notes;
}
