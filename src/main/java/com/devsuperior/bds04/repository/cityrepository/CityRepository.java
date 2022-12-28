package com.devsuperior.bds04.repository.cityrepository;

import com.devsuperior.bds04.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {
}