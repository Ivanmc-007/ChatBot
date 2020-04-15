package com.ivan.application.service;

import com.ivan.application.entity.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CityService {
//    private final static String INFO_MOSCOW = "Не забудьте посетить Красную Площадь. Ну а в ЦУМ можно и не заходить";
//    private final static String INFO_MINSK = "В историческом центре города сохранились величественные храмы и памятники истории.";
//    private final static String INFO_KIEV = "Владимирская горка — фрагмент городского ландшафта и парк, который на нём расположен.";
//
//    private static Map<Long, City> cities = new HashMap<Long, City>() {
//        {
//            City city1 = new City(1L, "Москва", INFO_MOSCOW);
//            City city2 = new City(2L, "Минск", INFO_MINSK);
//            City city3 = new City(3L, "Киев", INFO_KIEV);
//            this.put(city1.getId(), city1);
//            this.put(city2.getId(), city2);
//            this.put(city3.getId(), city3);
//        }
//    };
//
//    private static Long count = 3L;

    private final static Logger LOG = LoggerFactory.getLogger(CityService.class);

    @Autowired
    private CityRepository cityRepository;

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    public void delete(City city) {
        if(Objects.nonNull(city))
            cityRepository.delete(city);
        else
            LOG.info("LOGGER: Object of " + City.class.getName() +" is null in method delete() in " + getClass().getName());
    }

    public City save(City city) {
        return cityRepository.save(city);
    }

    public Optional<City> findByName(String name) {
        return cityRepository.findByName(name);
    }
}
