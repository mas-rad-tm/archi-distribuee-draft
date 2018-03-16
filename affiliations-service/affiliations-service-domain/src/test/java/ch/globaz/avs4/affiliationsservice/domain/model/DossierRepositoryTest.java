package ch.globaz.avs4.affiliationsservice.domain.model;

import ch.globaz.avs4.affiliationsservice.domain.model.Dossier;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

public class DossierRepositoryTest {

	@Test
	public void assertIfBuilderworkCorrectly () {

		Dossier dossier = Dossier.builder(1L,LocalDate.now());
		assertThat(dossier).isNotNull();
		assertThat(dossier.identifiant()).isNotNull();
		assertThat(dossier.dateEnregistrement()).isNotNull();
	}

}