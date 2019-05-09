package com.zhoulx.gavisualization.ga;

import java.util.Arrays;

public class GeneticAlgorithm {

    private int populationSize;
    private double mutationRate;
    private double crossoverRate;
    private int elitismCount;


    public GeneticAlgorithm(int populationSize, double mutationRate, double crossoverRate, int elitismCount) {

        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.elitismCount = elitismCount;
    }


    public Population initPopulation(int chromosomeLength) {
        return new Population(this.populationSize, chromosomeLength);
    }


    public double calcFitness(Individual individual, City[] cities) {
        // 计算适应度，倒数运算使其在0、1之间
        Route route = new Route(individual, cities);
        double fitness = 1 / route.getDistance();

        // 设置适应度
        individual.setFitness(fitness);
        return fitness;
    }


    public void evalPopulation(Population population, City[] cities) {
        double populationFitness = 0;

        for (Individual individual : population.getIndividuals()) {
            populationFitness += this.calcFitness(individual, cities);
        }

        double avgFitness = populationFitness / population.size();
        population.setPopulationFitness(avgFitness);
    }


    public boolean isTerminationConditionMet(int generationsCount, int maxGenerations) {
        return (generationsCount > maxGenerations);
    }


    public Individual selectParentWheel(Population population) {
        Individual[] individuals = population.getIndividuals();

        double populationFitness = population.getPopulationFitness();
        double rouletteWheelPosition = Math.random() * populationFitness;

        double spinWheel = 0;
        for (Individual individual : individuals) {
            spinWheel += individual.getFitness();
            if (spinWheel >= rouletteWheelPosition) {
                return individual;
            }
        }
        return individuals[population.size() - 1];
    }


    public Population crossoverPopulation(Population population) {
        // 创建新一代种群
        Population newPopulation = new Population(population.size());

        // 根据适应度遍历当代种群
        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
            // Get parent1
            Individual parent1 = population.getFittest(populationIndex);

            // 对该个体进行交叉
            if (this.crossoverRate > Math.random() && populationIndex >= this.elitismCount) {
                Individual parent2 = this.selectParentWheel(population);

                int[] offspringChromosome = new int[parent1.getChromosomeLength()];
                Arrays.fill(offspringChromosome, -1);
                Individual offspring = new Individual(offspringChromosome);

                // 获得父亲的子集
                int substrPos1 = (int) (Math.random() * parent1.getChromosomeLength());
                int substrPos2 = (int) (Math.random() * parent1.getChromosomeLength());

                final int startSubstr = Math.min(substrPos1, substrPos2);
                final int endSubstr = Math.max(substrPos1, substrPos2);

                // 将子集加入到后代中
                for (int i = startSubstr; i < endSubstr; i++) {
                    offspring.setGene(i, parent1.getGene(i));
                }

                // 遍历父亲2
                for (int i = 0; i < parent2.getChromosomeLength(); i++) {
                    int parent2Gene = i + endSubstr;
                    if (parent2Gene >= parent2.getChromosomeLength()) {
                        parent2Gene -= parent2.getChromosomeLength();
                    }

                    // 如果后代染色体中没有该城市，那么就加入到后代中
                    if (!offspring.containsGene(parent2.getGene(parent2Gene))) {
                        // 找到后代的空位置
                        for (int j = 0; j < offspring.getChromosomeLength(); j++) {
                            if (offspring.getGene(j) == -1) {
                                offspring.setGene(j, parent2.getGene(parent2Gene));
                                break;
                            }
                        }
                    }
                }

                newPopulation.setIndividual(populationIndex, offspring);
            } else {
                newPopulation.setIndividual(populationIndex, parent1);
            }
        }
        return newPopulation;
    }


    public Population mutatePopulation(Population population) {
        // 创建新种群
        Population newPopulation = new Population(this.populationSize);

        // 根据适应度遍历当代种群
        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
            Individual individual = population.getFittest(populationIndex);

            // 如果该个体为精英个体，那么跳过变异过程
            if (populationIndex >= this.elitismCount) {
                // 遍历个体基因
                for (int geneIndex = 0; geneIndex < individual.getChromosomeLength(); geneIndex++) {
                    // 判断该个体是否需要变异
                    if (this.mutationRate > Math.random()) {
                        // 随机产生新基因的位置
                        int newGenePos = (int) (Math.random() * individual.getChromosomeLength());
                        // 交换基因
                        int gene1 = individual.getGene(newGenePos);
                        int gene2 = individual.getGene(geneIndex);
                        individual.setGene(geneIndex, gene1);
                        individual.setGene(newGenePos, gene2);

                    }
                }
            }

            // 将变异后的个体加入新种群
            newPopulation.setIndividual(populationIndex, individual);
        }

        return newPopulation;
    }
}
