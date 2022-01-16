package com.demo.util.controller;

import com.demo.util.model.Utility;
import com.demo.util.service.UtilityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class UtilityResourceController {


    @Autowired
    RestTemplate restTemplate;
    Logger log = LoggerFactory.getLogger( UtilityResourceController.class );

    @Autowired
    private UtilityService utilityService;

    @GetMapping(value = "/utilities", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUtilitys( HttpServletRequest req ) {
        log.info( "Utility Service running in port " + req.getLocalPort( ) );
        return new ResponseEntity<List<Utility>>( utilityService.getUtilities( ), HttpStatus.FOUND );
    }


    @PostMapping(value = "/utilities")
    public ResponseEntity<String> saveUtility( @RequestBody Utility utility ) {

        utilityService.save( utility );
        return new ResponseEntity<String>( HttpStatus.CREATED );

    }

    @PutMapping(value = "/utilities/{utilityId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Utility> updateUtility( @RequestBody Utility utility, @PathVariable("utilityId") int utilityId ) {

        Utility s = utilityService.update( utilityId, utility ).get( );
        HttpHeaders headers = new HttpHeaders( );
        headers.setContentType( MediaType.APPLICATION_JSON );
        return new ResponseEntity<Utility>( s, headers, HttpStatus.ACCEPTED );

    }

    @DeleteMapping(value = "/utilities/{utilityId}")
    public ResponseEntity<Utility> deleteUtility( @PathVariable("utilityId") int utilityId ) {

        utilityService.delete( utilityId );

        return new ResponseEntity<Utility>( HttpStatus.NO_CONTENT );

    }


    @GetMapping(value = "/utilities/{utilityId}")
    public ResponseEntity<Utility> getUtilityById( @PathVariable("utilityId") int utilityId, HttpServletRequest req ) {

        log.info( "inside get utility by id " + utilityId );

        log.info( "Utility Service running in port " + req.getLocalPort( ) );

        Utility utility = utilityService.getUtilityById( utilityId ).get( );

        if (utility == null) {
            return new ResponseEntity<Utility>( HttpStatus.NOT_FOUND );
        }
        return new ResponseEntity<Utility>( utility, HttpStatus.OK );
    }
}
