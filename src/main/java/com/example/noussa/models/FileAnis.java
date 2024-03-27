package com.example.noussa.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FileAnis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_file ;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] justification;
    @Column(name = "file_name")
    private String filename;

    @Column(name = "content_type")
    private String contentType;
//
//    @OneToOne(mappedBy = "fileAnis" ,fetch = FetchType.EAGER)
//    @JsonIgnore
//
//    Absence absence;
}
