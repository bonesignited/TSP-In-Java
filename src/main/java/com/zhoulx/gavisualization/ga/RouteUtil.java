package com.zhoulx.gavisualization.ga;

import java.util.ArrayList;
import java.util.List;

public class RouteUtil {

    public static List<RouteLine> getRouteLines(Route route) {
        List<RouteLine> lines = new ArrayList<>();
        City[] cities = route.getRoute();
        int length = cities.length;
        for (int i = 0; i < length - 1; i++) {
            City city = cities[i];
            City next = cities[i + 1];
            lines.add(new RouteLine(city.getX(), city.getY(), next.getX(), next.getY(), i + 1));
        }
        lines.add(new RouteLine(cities[length - 1].getX(), cities[length - 1].getY(),
                cities[0].getX(), cities[0].getY(), length));

        return lines;
    }
}
