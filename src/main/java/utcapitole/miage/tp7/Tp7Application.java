package utcapitole.miage.tp7;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import utcapitole.miage.tp7.model.entity.Activite;
import utcapitole.miage.tp7.model.entity.Statut;
import utcapitole.miage.tp7.model.entity.Thematique;
import utcapitole.miage.tp7.repository.ActiviteRepo;
import utcapitole.miage.tp7.repository.StatutRepo;
import utcapitole.miage.tp7.repository.ThematiqueRepo;

/**
 * Classe principale de l'application
 * @author Esteban BIRET-TOSCANO
 * @version 1.0.0.0.0.0.0.69.5
 */
@SpringBootApplication
public class Tp7Application {

	/**
	 * Point d'entrée de l'application
	 * @param args 
	 */
	public static void main(String[] args) {
		SpringApplication.run(Tp7Application.class, args);
	}

	/**
	 * Initialisation de la base de données avec 3 statuts
	 * @param repository
	 */
	@Bean
	@SuppressWarnings("unused")
	CommandLineRunner initStatuts(StatutRepo repository) {
		return args -> {
			String[] nomsActivites = {
				"Étudiant",
				"Universitaire",
				"Entreprise"
			};
			for (String nom : nomsActivites) {
				if (repository.findByNomStatut(nom) == null) {
					repository.save(new Statut(nom));
				}
			}
		};
	}

	/**
	 * Initialisation de la base de données avec 3 activités
	 * @param repository
	 */
	@Bean
	@SuppressWarnings("unused")
	CommandLineRunner initActivites(ActiviteRepo repository) {
		return args -> {
			List<Activite> activites = List.of(
				new Activite("Visite guidée de la ville", 20.0, 10.0, 50),
				new Activite("Repas de gala", 50.0, 30.0, 100),
				new Activite("Repas rencontre", 35.0, 20.0, 75)
			);

			for (Activite activite : activites) {
				if (repository.findByNomActivite(activite.getNomActivite()) == null) {
					repository.save(activite);
				}
			}
		};
	}

	/**
	 * Initialisation de la base de données avec 9 thématiques
	 * @param repository
	 */
	@Bean
	@SuppressWarnings("unused")
	CommandLineRunner initThematiques(ThematiqueRepo thematiqueRepo) {
		return args -> {
			String[] nomsThematiques = {
				"Comptabilité-Contrôle",
				"Ressources Humaines",
				"Marketing",
				"Finance",
				"Gestion des SI",
				"Gestion de projets informatiques",
				"Business Intelligence",
				"Informatique Décisionnelle",
				"Veille Stratégique"
			};

			for (String nom : nomsThematiques) {
				if (thematiqueRepo.findByNomThematique(nom) == null) {
					thematiqueRepo.save(new Thematique(nom));
				}
			}
		};
	}

}