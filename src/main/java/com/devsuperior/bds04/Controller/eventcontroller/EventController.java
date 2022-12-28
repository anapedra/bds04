package com.devsuperior.bds04.Controller.eventcontroller;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.services.eventservice.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/events")
public class EventController {

    @Autowired
    private EventService service;

    @GetMapping
    public ResponseEntity<Page<EventDTO>> findAll(Pageable pageable) {
        Page<EventDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> findById(@PathVariable Long id) {
        EventDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }
    @PostMapping
    public ResponseEntity<EventDTO> insert(@Valid @RequestBody EventDTO dto){
        dto=service.save(dto);
        URI uri= ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> update(@PathVariable Long id,@RequestBody @Valid EventDTO dto){
        EventDTO newDto=service.upDate(id,dto);
        return ResponseEntity.ok().body(newDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delet(id);
        return ResponseEntity.noContent().build();
    }
}