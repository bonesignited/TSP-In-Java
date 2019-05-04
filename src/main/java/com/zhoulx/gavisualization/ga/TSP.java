package com.zhoulx.gavisualization.ga;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TSP {
    public static City[] cities;

    private static void getCapitals() throws IOException {
        String filepath = "src/res/data.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
        String line; int lineCount = 0;
        ArrayList<City> temp = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            temp.add(new City(data[0], Double.valueOf(data[1]), Double.valueOf(data[2])));
            lineCount++;
        }
        cities = new City[lineCount];
        temp.toArray(cities);
    }

    public static void main(String[] args) throws IOException {

        // 读取城市数据
        getCapitals();

        // 初始化算法
        GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.001, 0.9, 3, 5);

        // 初始化种群
        Population population = ga.initPopulation(cities.length);

        // 评估种群
        ga.evalPopulation(population, cities);

        Route startRoute = new Route(population.getFittest(0), cities);
        System.out.println("Initial distance: " + startRoute.getDistance() + startRoute.toString());

        int generation = 1;

        while (!ga.isTerminationConditionMet(generation, Constant.MAX_GENERATIONS)) {
            // 打印种群中适应度最高的个体
            Route route = new Route(population.getFittest(0), cities);
//            System.out.println("G" + generation + " Best distance: " + route.getDistance());
            System.out.println(RouteUtil.getRouteCoordinate(route).toString());

            // 交叉
            population = ga.crossoverPopulation(population);

            // 变异
            population = ga.mutatePopulation(population);

            // 评估种群
            ga.evalPopulation(population, cities);

            generation++;
        }

        // 打印结果
        System.out.println("Stopped after " + Constant.MAX_GENERATIONS + " generations.");
        Route route = new Route(population.getFittest(0), cities);
        System.out.println("Best distance: " + route.getDistance());
    }
}
