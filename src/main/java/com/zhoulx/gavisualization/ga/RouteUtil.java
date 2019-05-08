package com.zhoulx.gavisualization.ga;

import com.zhoulx.gavisualization.service.DataHolder;
import com.zhoulx.gavisualization.service.RouteLine;
import com.zhoulx.gavisualization.service.Schema;

import java.util.ArrayList;
import java.util.List;

public class RouteUtil {

    public static Schema getSchema(Route route) {
        List<RouteLine> lines = new ArrayList<>();
        City[] cities = route.getRoute();
        int length = cities.length;


        // 从 cities 中找到起点
        int start = 0;
        for (int i = 0; i < length; i++) {
            if (cities[i].getName().equals(DataHolder.START)) {
                start = i;
                break;
            }
        }


        for (int i = start; i < start + length - 1; i++) {
            City city = cities[i % length];
            City next = cities[(i + 1) % length];
            lines.add(new RouteLine(city.getX(), city.getY(), next.getX(), next.getY(), i - start));
        }
        lines.add(new RouteLine(cities[start - 1].getX(), cities[start - 1].getY(),
                cities[start].getX(), cities[start].getY(), length - 1));

        double distance = route.getDistance();

        return new Schema(lines, distance);
    }
}
