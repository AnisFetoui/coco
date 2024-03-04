package com.example.noussa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Departement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_departement;
    private String libelle ;
    private int maxSaturation;
    private int nbreEmpl;
    @OneToMany(mappedBy="departement",fetch = FetchType.EAGER)
    Set<Employee> employees;

}
