package com.zhoulx.gavisualization;

import com.zhoulx.gavisualization.ga.City;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        City[] existedCities = {
                new City("北京", 1, 2),
                new City("南京", 3, 4),
                new City("济南", 5, 6),
                new City("乌鲁木齐", 7, 8),
                new City("南昌", 9, 10),
                new City("武汉", 11, 12),
                new City("杭州", 13, 14)
        };

        List<String> cities = Arrays.asList("北京", "济南", "南昌");

        List<City> all = Arrays.asList(existedCities);

        List<City> all2 = all.stream().filter(city -> city.getName().equals("北京")).collect(Collectors.toList());
    }
}
