package com.zhoulx.gavisualization.service;


import com.zhoulx.gavisualization.ga.*;
import org.omg.PortableServer.AdapterActivator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TSPService {

    public List<City> getAllCities() {
        return Arrays.asList(DataHolder.allCities);
    }

    public Schema process(List<String> cities, String start) {
        List<City> ret = new ArrayList<>();
        for (String city : cities) {
            for (int i = 0; i < DataHolder.allCities.length; i++) {
                City existed = DataHolder.allCities[i];
                if (city.equals(existed.getName())) {
                    ret.add(new City(existed.getName(), existed.getX(), existed.getY()));
                }
            }
        }

        DataHolder.START = start;


        City[] cityArray = new City[cities.size()];
        ret.toArray(cityArray);

        Route route =  TSP.run(cityArray);

        return RouteUtil.getSchema(route);
    }
}
