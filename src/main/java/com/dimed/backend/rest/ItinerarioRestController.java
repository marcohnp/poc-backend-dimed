package com.dimed.backend.rest;

import com.dimed.backend.model.Coordendas;
import com.dimed.backend.model.Itinerario;
import com.dimed.backend.model.LinhaOnibus;
import com.dimed.backend.service.ItinerarioService;
import com.dimed.backend.service.LinhaOnibusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/itinerario")
public class ItinerarioRestController {

    @Autowired
    private ItinerarioService itinerarioService;


    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Itinerario> getItinerario(@RequestParam String id) throws IOException {
        Itinerario itinerario = new Itinerario();
        itinerario = itinerarioService.findItinerario(id);
        if (itinerario == null) {
            return new ResponseEntity<Itinerario>(HttpStatus.NOT_FOUND);
        }

        itinerarioService.save(itinerario);

        return new ResponseEntity<Itinerario>(itinerario, HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Collection<Itinerario>> findAll() {
        Collection<Itinerario> list = new ArrayList<>();
        list = itinerarioService.findAll();
        if (list == null) {
            return new ResponseEntity<Collection<Itinerario>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Collection<Itinerario>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Itinerario> findById(@PathVariable Long id) {
        Itinerario itinerarioById = new Itinerario();
        itinerarioById = itinerarioService.findById(id);

        return new ResponseEntity<Itinerario>(itinerarioById, HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Void> createItinerario(@RequestBody Itinerario itinerario) {
        Collection<Itinerario> list = itinerarioService.findAll();
        boolean exist = false;
        for (Itinerario l : list) {
            if (l.getIdlinha().equals(itinerario.getIdlinha())) {
                exist = true;
            }
        }

        if (exist == false) {
            if(!itinerario.getNome().isEmpty() && !itinerario.getCodigo().isEmpty()){
                itinerarioService.save(itinerario);
                return new ResponseEntity<Void>(HttpStatus.CREATED);
            }
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            if(!itinerario.getNome().isEmpty() && !itinerario.getCodigo().isEmpty()){
                itinerarioService.save(itinerario);
                return new ResponseEntity<Void>(HttpStatus.OK);
            }
            return new ResponseEntity<Void>(HttpStatus.OK);

        }
    }

}
