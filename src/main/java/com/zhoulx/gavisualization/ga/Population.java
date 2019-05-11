package com.zhoulx.gavisualization.ga;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;


public class Population {
    private Individual[] population;
    private double populationFitness = -1;


    public Population(int populationSize) {
        this.population = new Individual[populationSize];
    }


    public Population(int populationSize, int chromosomeLength) {
        // 每个种群都是一个个体数组
        this.population = new Individual[populationSize];

        // 初始化每个个体
        for (int individualCount = 0; individualCount < populationSize; individualCount++) {
            // 以给定染色体长度初始化个体
            Individual individual = new Individual(chromosomeLength);
            this.population[individualCount] = individual;
        }
    }


    public Individual[] getIndividuals() {
        return this.population;
    }


    public Individual getFittest(int offset) {
        Arrays.sort(this.population, new Comparator<Individual>() {
            @Override
            public int compare(Individual o1, Individual o2) {
                if (o1.getFitness() > o2.getFitness()) {
                    return -1;
                } else if (o1.getFitness() < o2.getFitness()) {
                    return 1;
                }
                return 0;
            }
        });

        return this.population[offset];
    }


    public void setPopulationFitness(double fitness) {
        this.populationFitness = fitness;
    }


    public double getPopulationFitness() {
        return this.populationFitness;
    }


    public int size() {
        return this.population.length;
    }


    public Individual setIndividual(int offset, Individual individual) {
        return population[offset] = individual;
    }


    public Individual getIndividual(int offset) {
        return population[offset];
    }


    public void shuffle() {
        Random rnd = new Random();
        for (int i = population.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            Individual a = population[index];
            population[index] = population[i];
            population[i] = a;
        }
    }
}