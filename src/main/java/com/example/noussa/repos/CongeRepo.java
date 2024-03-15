package com.example.noussa.repos;

import com.example.noussa.models.Conge;
import com.example.noussa.models.Departement;
import com.example.noussa.models.PosteEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CongeRepo extends JpaRepository<Conge,Long> {
    @Query("SELECT c FROM Conge c WHERE " +
            "c.employee.teem.team_id = :teamId AND " +
            "c.employee.PosteEmployee = :poste AND " +
            "((c.date_debut BETWEEN :start AND :end) OR " +
            "(c.date_fin BETWEEN :start AND :end) OR " +
            "(:start BETWEEN c.date_debut AND c.date_fin) OR " +
            "(:end BETWEEN c.date_debut AND c.date_fin))")
    List<Conge> findCongeInSamePeriodAndSameTeam(
            @Param("teamId") Long teamId,
            @Param("poste") PosteEmployee poste,
            @Param("start") Date start,
            @Param("end") Date end);

    List<Conge> findByCommentaireStartingWithOrJustificationStartingWith(String commentaireStartingLetter, String justificationStartingLetter);
}


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

