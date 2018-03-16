package ch.globaz.avs4.affiliationsservice.application.service;

import ch.globaz.avs4.affiliationsservice.domain.model.Dossier;

import java.util.List;
import java.util.Optional;

public interface DossierService {

	Dossier sauve(Dossier dossier);

	List<Dossier> getAll();

    Optional getById(Long id);
}
