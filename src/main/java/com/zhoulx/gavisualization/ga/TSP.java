package com.zhoulx.gavisualization.ga;

import static com.zhoulx.gavisualization.ga.Constant.*;

public class TSP {

    public static Route run(City[] inputCities) {

        // 初始化算法
        GeneticAlgorithm ga = new GeneticAlgorithm(POPULATION_SIZE, MUTATION_RATE, CROSSOVER_RATE, ELITISM_COUNT, TOURNAMENT_SIZE);

        // 初始化种群
        Population population = ga.initPopulation(inputCities.length);

        // 评估种群
        ga.evalPopulation(population, inputCities);

        Route startRoute = new Route(population.getFittest(0), inputCities);
        System.out.println("Initial distance: " + startRoute.getDistance() + startRoute.toString());

        int generation = 1;

        while (!ga.isTerminationConditionMet(generation, Constant.MAX_GENERATIONS)) {
            // 打印种群中适应度最高的个体
            Route route = new Route(population.getFittest(0), inputCities);
            System.out.println("G" + generation + " Best distance: " + route.getDistance());

            // 交叉
            population = ga.crossoverPopulation(population);

            // 变异
            population = ga.mutatePopulation(population);

            // 评估种群
            ga.evalPopulation(population, inputCities);

            generation++;
        }

        // 打印结果
        System.out.println("Stopped after " + Constant.MAX_GENERATIONS + " generations.");
        Route route = new Route(population.getFittest(0), inputCities);
        System.out.println("Best distance: " + route.getDistance());
        return route;
    }


    public static void main(String[] args) throws Exception{

    }
}
