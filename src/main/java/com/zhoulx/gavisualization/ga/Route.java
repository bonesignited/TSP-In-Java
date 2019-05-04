package com.zhoulx.gavisualization.ga;

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
}
