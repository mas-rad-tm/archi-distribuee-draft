package ch.globaz.avs4.affiliationsservice.domain.repository;

import ch.globaz.avs4.affiliationsservice.domain.model.Dossier;

import java.util.List;
import java.util.Optional;

public interface DossierRepository {

	Dossier store (Dossier dossier);


	List<Dossier> getAll();


    Optional<Dossier> getById(Long dossierId);
}
