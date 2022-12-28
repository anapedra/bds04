package com.devsuperior.bds04.services.eventservice;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repository.cityrepository.CityRepository;
import com.devsuperior.bds04.repository.eventrepository.EventRepository;
import com.devsuperior.bds04.services.exceptionservice.DataBaseException;
import com.devsuperior.bds04.services.exceptionservice.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;


    @Transactional(readOnly = true)
    public Page<EventDTO> findAllPaged(Pageable pageable){
        Page<Event> list=repository.findAll(pageable);
        return list.map(x -> new EventDTO(x));
    }
    @Transactional(readOnly = true)
    public EventDTO findById(Long id){
        Optional<Event> obj=repository.findById(id);
        Event entity=obj.orElseThrow(
                ()-> new ResourceNotFoundException("Id "+id+" not found"));
        return new EventDTO(entity);
    }
    @Transactional
    public EventDTO save(EventDTO dto) {
        var event=new Event();
        copyDtoToEntity(dto,event);
        City city=new City();
        city.setId(event.getCity().getId());
        event=repository.save(event);
        return new EventDTO(event);
    }
    @Transactional
    public EventDTO upDate(Long id, EventDTO dto){
        try {
            var event= repository.getOne(id);
            copyDtoToEntity(dto,event);
            event=repository.save(event);
            return new EventDTO(event);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id " + id + " not found :(");
        }

    }

    public void delet(Long id){

        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Id "+id+" not found!");
        }
        catch (DataIntegrityViolationException e){
            throw new DataBaseException("Integrity violation");
        }

    }

    private void copyDtoToEntity(EventDTO eventDTO, Event event){
        event.setName(eventDTO.getName());
        event.setDate(eventDTO.getDate());
        event.setUrl(eventDTO.getUrl());
    }

}


