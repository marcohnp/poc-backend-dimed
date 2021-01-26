package com.dimed.backend.rest;

import com.dimed.backend.model.Itinerario;
import com.dimed.backend.model.LinhaOnibus;
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
@RequestMapping("/linhaonibus")
public class LinhaOnibusRestController {

    @Autowired
    private LinhaOnibusService linhaOnibusService;


    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Collection<LinhaOnibus>> getAllLinhasOnibus() {
        Collection<LinhaOnibus> list = new ArrayList<>();
        list = linhaOnibusService.findAll();
        if (list == null) {
            return new ResponseEntity<Collection<LinhaOnibus>>(HttpStatus.NOT_FOUND);
        }

        for (LinhaOnibus l: list) {
            linhaOnibusService.save(l);
        }

        return new ResponseEntity<Collection<LinhaOnibus>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<LinhaOnibus> findById(@PathVariable Long id) {
        LinhaOnibus linhaById = new LinhaOnibus();
        linhaById = linhaOnibusService.findById(id);

        return new ResponseEntity<LinhaOnibus>(linhaById, HttpStatus.OK);
    }

    @RequestMapping(value = "/linha", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Collection<LinhaOnibus>> getListLinhaOnibusByName(@RequestParam String nome) throws IOException {
        Collection<LinhaOnibus> list = new ArrayList<>();
        list = linhaOnibusService.findByName(nome);
        if (list == null) {
            return new ResponseEntity<Collection<LinhaOnibus>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Collection<LinhaOnibus>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Void> saveLinhaOnibus(@RequestBody LinhaOnibus linhaOnibus) {
        Collection<LinhaOnibus> list = linhaOnibusService.findAll();
        boolean exist = false;
        for (LinhaOnibus l : list) {
            if (l.getId().equals(linhaOnibus.getId())) {
                exist = true;
            }
        }

        if (exist == false) {
            if(!linhaOnibus.getNome().isEmpty() && !linhaOnibus.getCodigo().isEmpty()){
                linhaOnibusService.save(linhaOnibus);
                return new ResponseEntity<Void>(HttpStatus.CREATED);
            }
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            if(!linhaOnibus.getNome().isEmpty() && !linhaOnibus.getCodigo().isEmpty()){
                linhaOnibusService.save(linhaOnibus);
                return new ResponseEntity<Void>(HttpStatus.OK);
            }
            return new ResponseEntity<Void>(HttpStatus.OK);

        }
    }

    @RequestMapping(value = "/coord", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Collection<LinhaOnibus>> getListLinhaOnibusByCoord(@RequestParam double lat, @RequestParam double lng ) throws IOException, InterruptedException {
        Collection<LinhaOnibus> list = new ArrayList<>();

        linhaOnibusService.findByCoord(lat, lng);

        return new ResponseEntity<Collection<LinhaOnibus>>(list, HttpStatus.OK);
    }
}
