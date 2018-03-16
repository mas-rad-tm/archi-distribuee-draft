package ch.globaz.avs4.zuulapigateway.application.service;

import ch.globaz.avs4.zuulapigateway.application.dto.DossierDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("affiliations-service")
public interface DossierService {

    @RequestMapping(method = RequestMethod.POST, value = "/dossiers")
    ResponseEntity<DossierDto> sauveDossier(@RequestBody DossierDto dto);
}


