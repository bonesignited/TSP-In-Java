package com.zhoulx.gavisualization.ga;

import java.util.ArrayList;
import java.util.List;

public class RouteUtil {

    public static List<Coordinate> getRouteCoordinate(Route route) {
        List<Coordinate> coordinates = new ArrayList<>();
        for (City city : route.getRoute()) {
            coordinates.add(new Coordinate(city.getX(), city.getY()));
        }

        return coordinates;
    }
}
