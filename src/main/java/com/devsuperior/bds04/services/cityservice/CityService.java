package com.devsuperior.bds04.services.cityservice;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repository.cityrepository.CityRepository;
import com.devsuperior.bds04.services.exceptionservice.DataBaseException;
import com.devsuperior.bds04.services.exceptionservice.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityService {
   @Autowired
   private CityRepository repository;

    @Transactional(readOnly = true)
    public List<CityDTO> findAll() {
        List<City> list = repository.findAll();
        return list.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public CityDTO findById(Long id){
        Optional<City> obj=repository.findById(id);
        City entity=obj.orElseThrow(
                ()-> new ResourceNotFoundException("Id "+id+" not found"));
        return new CityDTO(entity);
    }
    @Transactional
    public CityDTO save(CityDTO cityDTO) {
        var city=new City();
        city.setName(cityDTO.getName());
        city=repository.save(city);
        return new CityDTO(city);
    }
    @Transactional
    public CityDTO upDate(Long id, CityDTO dto){
        try {
            var city = repository.getOne(id);
            city.setName(dto.getName());
            city=repository.save(city);
            return new CityDTO(city);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id " + id + " not found :(");
        }

    }

    public void delete(Long id){

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



}


