package com.example.noussa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PerformanceEmployee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_performance ;
    private float moyenne;
    private String commentaire ;


    @OneToOne(mappedBy = "performanceEmployee" ,fetch = FetchType.EAGER)
    @JsonIgnore
    Employee empp;
}
