package com.dimed.backend.facade;

import com.dimed.backend.model.LinhaOnibus;
import com.dimed.backend.service.LinhaOnibusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@AllArgsConstructor
public class LinhaOnibusFacade {

    private final LinhaOnibusService linhaOnibusService;

    public List<LinhaOnibus> findAll(){
        return linhaOnibusService.findAll();
    }

    public LinhaOnibus findById(Long id){
        return linhaOnibusService.findById(id);
    }

    public List<LinhaOnibus> findByName(String name){
        return linhaOnibusService.findByName(name);
    }

    public LinhaOnibus save(LinhaOnibus linha) {
        return linhaOnibusService.save(linha);
    }
}
