package com.zhoulx.gavisualization.ga;

import java.util.Arrays;

public class Route {
    private City[] route;
    private double distance = 0;

    public Route(Individual individual, City[] cities) {

        // 获得染色体
        int[] chromosome = individual.getChromosome();

        // 创建路线
        this.route = new City[cities.length];
        for (int geneIndex = 0; geneIndex < chromosome.length; geneIndex++) {
            this.route[geneIndex] = cities[chromosome[geneIndex]];
        }
    }

    public double getDistance() {
        if (this.distance > 0) {
            return this.distance;
        }

        // 计算总距离
        double totalDistance = 0;
        for (int cityIndex = 0; cityIndex + 1 < this.route.length; cityIndex++) {
            totalDistance += this.route[cityIndex].distanceFrom(this.route[cityIndex + 1]);
        }
        totalDistance += this.route[this.route.length - 1].distanceFrom(this.route[0]);
        this.distance = totalDistance;

        return totalDistance;
    }


    public City[] getRoute() {
        return route;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Route{route=");
        for (int i = 0; i < route.length; i++) {
            sb.append(route[i].getName());
            sb.append(" -> ");
        }
        sb.append(route[0].getName());
        return sb.toString();
    }
}
