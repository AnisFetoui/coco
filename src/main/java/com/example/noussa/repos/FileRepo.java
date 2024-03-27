package com.example.noussa.repos;

import com.example.noussa.models.Absence;
import com.example.noussa.models.FileAnis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FileRepo extends JpaRepository<FileAnis,Long> {

    //FileAnis findByAbsence_Id_absence(Long idAbsence);

//    List<Absence> findByStudent(Employee student);
//
//    List<Absence> findByLesson(Employee lesson);
//
//    List<Absence> findByValidee(boolean validee);

//    int countByStudent(Employee student);
//    int countByEmp(Long id);

     /*
    	@Query("Select "
			+ "DISTINCT p from Patient p "
			+ "join p.medecins med "
			+ "join med.clinique cli "
			+ "where cli=:clinique")
    public List<Patient> getAllPatientByClinique(@Param("clinique") Clinique clinique);

	@Query("SELECT count(*) FROM Patient p join p.medecins m WHERE m.poste=:poste")
	public Long getNombrePatientsExaminesParMedecinActionaire(@Param("poste")Poste poste) ;

     @Query("SELECT c FROM Chaine c " +
"            	"join c.programmes p "  +
"               "join p.utilisateurs u \ " +
"               "WHERE SIZE(p.utilisateurs) > 0 " +
"               "GROUP BY c \ " +
"               "ORDER BY COUNT(p) DESC")
			public void ordonnerChaines();

     */
    //    List<Reservation> findByAnneeUniversitaireBetween(Date dateDebut , Date dateFin);
    //    long countByTypeCAndBlocIdBloc(TypeChambre Typec, long idBloc);
    /* @Query("SELECT p.chaine, count(p) AS cp FROM Programme p " +
            "INNER JOIN p.utilisateurs u " +
            "WHERE u.usrId IS NOT NULL " +
            "GROUP BY p.chaine ORDER BY cp DESC")
    List<Object[]> listerchaines();*/
    /* @Query("SELECT distinct (p) FROM Programme p " +
            "INNER JOIN p.utilisateurs u " +
            "WHERE u.profession = :p ")
    List<Programme> listerProgrammesInteressants(@Param("p") Profession p);*/
}
