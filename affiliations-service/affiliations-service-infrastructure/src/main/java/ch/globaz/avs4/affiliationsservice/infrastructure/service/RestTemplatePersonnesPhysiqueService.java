package ch.globaz.avs4.affiliationsservice.infrastructure.service;

import ch.globaz.avs4.affiliationsservice.infrastructure.dto.PersonnesPhysiqueDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Component
public class RestTemplatePersonnesPhysiqueService {

	@Autowired
	private RestTemplate restTemplate;

	public List<PersonnesPhysiqueDto> getPersonnesPhysique(){
		ResponseEntity<List<PersonnesPhysiqueDto>> exchange = this.restTemplate.exchange(
				"http://personnes-service/personnes-service/personnes",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<PersonnesPhysiqueDto>>() {
				});

		return exchange.getBody();
	}
}
