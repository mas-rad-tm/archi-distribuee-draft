package ch.globaz.avs4.affiliationsservice.application.service.impl;

import ch.globaz.avs4.affiliationsservice.application.service.DossierService;
import ch.globaz.avs4.affiliationsservice.domain.model.Dossier;
import ch.globaz.avs4.affiliationsservice.domain.repository.DossierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DossierServiceImpl implements DossierService {

	@Autowired
	DossierRepository repository;

	@Override
	public Dossier sauve(Dossier dossier) {

		return repository.store(dossier);
	}

	@Override
	public List<Dossier> getAll() {
		return repository.getAll();
	}

	@Override
	public Optional getById(Long id) {

		Optional<Dossier> pp = repository.getById(id);

		return pp;
	}
}
