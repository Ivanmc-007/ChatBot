package com.ivan.application.controller;

import com.ivan.application.entity.City;
import com.ivan.application.exception.NotFoundException;
import com.ivan.application.service.CityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public List<City> getCities() {
        return cityService.findAll();
    }

    @GetMapping("/{id}")
    public City findById(@PathVariable Long id) {
        return getCityFromDB(id);
    }

    private City getCityFromDB(Long id) {
        return cityService
                .findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public City createNew(@RequestBody City city) {
        return cityService.save(city);
    }

    @PutMapping("/{id}")
    public City updateCity(@PathVariable(name = "id") Long id, @RequestBody City city) {
        City cityFromDB = getCityFromDB(id);
        cityFromDB.setName(city.getName());
        cityFromDB.setInformation(city.getInformation());
        return cityFromDB;
    }

    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable(name = "id") Long id) {
        cityService.delete(getCityFromDB(id));
    }

}
