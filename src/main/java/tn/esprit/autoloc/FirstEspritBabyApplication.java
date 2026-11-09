package tn.esprit.autoloc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IVehiculeRepository;

import io.swagger.v3.oas.models.OpenAPI;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title= "Autoloc", description = "description"))
public class FirstEspritBabyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstEspritBabyApplication.class, args);
	}

	@Bean
	CommandLineRunner initVehicules(IVehiculeRepository vehiculeRepository) {
		return args -> {
			if (vehiculeRepository.count() == 0) {
				Vehicule v1 = new Vehicule(
						null,
						"123-TN-4567",
						"Peugeot",
						"208",
						CategorieVehicule.CITADINE,
						new BigDecimal("90.00"),
						StatutVehicule.DISPONIBLE, null, null, null, null);

				Vehicule v2 = new Vehicule(
						null,
						"234-TN-5678",
						"Volkswagen",
						"Golf 8",
						CategorieVehicule.BERLINE,
						new BigDecimal("150.00"),
						StatutVehicule.LOUE, null, null, null, null);

				Vehicule v3 = new Vehicule(
						null,
						"345-TN-6789",
						"Toyota",
						"RAV4",
						CategorieVehicule.SUV,
						new BigDecimal("220.00"),
						StatutVehicule.DISPONIBLE, null, null, null, null);

				vehiculeRepository.saveAll(List.of(v1, v2, v3));
				log.info(">>> 3 véhicules de démonstration insérés avec succès !");
			}
		};
	}
}
