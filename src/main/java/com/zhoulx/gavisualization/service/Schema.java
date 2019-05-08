package com.zhoulx.gavisualization.service;

import com.zhoulx.gavisualization.ga.Route;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

public class Schema {
    private List<RouteLine> routeLines;

    private double distance;

    public Schema(List<RouteLine> routeLines, double distance) {
        this.routeLines = routeLines;
        this.distance = distance;
    }

    public List<RouteLine> getRouteLines() {
        return routeLines;
    }

    public double getDistance() {
        return distance;
    }
}
