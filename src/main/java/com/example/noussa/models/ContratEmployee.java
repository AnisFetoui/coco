package com.example.noussa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContratEmployee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detail_contrat_e;
    private Long rib;
    private Long numeroSecuriteSociale;
    @Temporal(TemporalType.DATE)
    private Date date_debut;
    @Temporal(TemporalType.DATE)
    private Date date_fin;
    @Enumerated(EnumType.STRING)
    private ContratEmployeeType typeCE;
    private int duree_hebdomadaire;
    private Boolean isArchive;

    @ManyToOne(cascade = CascadeType.ALL)
    Employee empl;

}
