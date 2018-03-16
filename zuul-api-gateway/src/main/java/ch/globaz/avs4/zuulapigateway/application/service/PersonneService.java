package ch.globaz.avs4.zuulapigateway.application.service;

import ch.globaz.avs4.zuulapigateway.application.dto.PersonnesPhysiqueDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("personnes-service")
public interface PersonneService {

    @RequestMapping(method = RequestMethod.GET, value = "/personnes/{personnesId}")
    ResponseEntity<PersonnesPhysiqueDto> getPersonnePhysiqueById(@PathVariable("personnesId") Long tiersId);

    @RequestMapping(method = RequestMethod.GET, value = "/personnes")
    ResponseEntity<List<PersonnesPhysiqueDto>> getPersonnesPhysique();
}
