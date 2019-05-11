package com.zhoulx.gavisualization.ga;



public class Individual {
	private int[] chromosome;
	private double fitness = -1;


	public Individual(int[] chromosome) {
		this.chromosome = chromosome;
	}

	/**
	 * 通过染色体长度创建个体
	 * @param chromosomeLength 染色体长度
	 */
	public Individual(int chromosomeLength) {
		int[] individual = new int[chromosomeLength];

		for (int gene = 0; gene < chromosomeLength; gene++) {
			individual[gene] = gene;
		}

		this.chromosome = individual;
	}

	public int[] getChromosome() {
		return this.chromosome;
	}


	public int getChromosomeLength() {
		return this.chromosome.length;
	}


	public void setGene(int offset, int gene) {
		this.chromosome[offset] = gene;
	}


	public int getGene(int offset) {
		return this.chromosome[offset];
	}


	public void setFitness(double fitness) {
		this.fitness = fitness;
	}


	public double getFitness() {
		return this.fitness;
	}


	public String toString() {
		StringBuilder output = new StringBuilder();
		for (int gene : this.chromosome) {
			output.append(gene);
		}
		return output.toString();
	}


	public boolean containsGene(int gene) {
		for (int i : chromosome) {
			if (i == gene) {
				return true;
			}
		}
		return false;
	}
}
