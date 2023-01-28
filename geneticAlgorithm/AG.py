import numpy as np
import random


class AlgorytmGenetyczny():

    def __init__(self, n, m=1000, T=100, capacity=10, v=None, c=None, crossoverProp=0.9,
                 mutationProbability=0.001,doubleCrossOver = False, rank_select = False, fitFunc = False):
        self.n_ = n
        self.m_ = m
        self.T_ = T
        self.v_ = v
        self.c_ = c
        self.capacity_ = capacity
        self.crossoverProp_ = crossoverProp
        self.mutationProbability_ = mutationProbability
        self.doubleCrossOver_ = doubleCrossOver # True -> wybieramy doubleCrossOver zamiast pojedynczego
        self.rank_select_ = rank_select # True -> wybieramy rank_select zamiast roulette
        self.fitFunc_ = fitFunc # True -> wybieramy funckje przystosowania z ciezka kara za przekroczenie capacity

    def algo_gen(self):
        population = self.generate_population()
        for i in range(self.T_):
            selected = self.rank_select(population)
            newGeneration = self.doublePointCrossOver(selected)
            newGenerationAfterMutation = self.mutation(newGeneration)
        population = sorted(newGenerationAfterMutation, key=lambda x: self.fitness_func_hard(x), reverse=True)
        return population[0]

    def algo_genTest(self):
        population = self.generate_population()
        for i in range(self.T_):
            if self.rank_select_:
                selected = self.rank_select(population)
            else:
                selected = self.roulette_selection(population)
            if self.doubleCrossOver_:
                newGeneration = self.doublePointCrossOver(selected)
            else:
                newGeneration = self.onePointCrossOver(selected)
            newGenerationAfterMutation = self.mutation(newGeneration)
        if self.fitFunc_:
            population = sorted(newGenerationAfterMutation, key=lambda x: self.fitness_func_hard(x), reverse=True)
        else:
            population = sorted(newGenerationAfterMutation, key=lambda x: self.fitness_func_easy(x), reverse=True)
        return population[0]

    def generate_population(self):
        population = []
        for i in range(self.m_):
            chromosom = []
            for j in range(self.n_):
                chromosom.append(random.randint(0, 1))
            population.append(chromosom)
        return population

    def fitness_func_hard(self, chromosom):
        weight = 0
        value = 0
        for i in range(len(chromosom)):
            if chromosom[i] == 1:
                weight += self.c_[i]
                value += self.v_[i]
        if weight > self.capacity_:
            return 1 / value
        else:
            return value

    def fitness_func_easy(self,chromosom):
        weight = 0
        value = 0
        for i in range(len(chromosom)):
            if chromosom[i] == 1:
                weight += self.c_[i]
                value += self.v_[i]
        if weight - self.capacity_ * 0.05 > self.capacity_:
            return 1 / value
        else:
            return value

    def roulette_selection(self, population):
        # suma wszystkich wartosci z fitness function dla calej populacji
        suma = 0
        # jest to tablica ruletek liczymy sobie jakie
        # prawdopodobienstwo ma dany chromosom w stosunku do calej ilosci populacji
        # drugi indeks odpowiada indeksowi w starej populacji, żeby po "wylosowaniu danego chromosomu, bedziemy mogli odniesc sie po indeksie"
        arrayOfScores = np.zeros((len(population), 2))

        # wybrałem sobie, że losujemy ruletką 1/5 x wielkość całej populacji. jest to tablica na nową populację
        newPopulation = np.zeros((len(population[:int(0.2 * len(population))]), self.n_))

        for i in range(len(population)):
            suma += (self.fitness_func_hard(population[i]))

        for j in range(len(population)):
            arrayOfScores[j][0] = (self.fitness_func_hard(population[j])) / suma
            arrayOfScores[j][1] = j
        prob = 0.0
        for j in range(len(population[:int(0.2 * len(population))])):
            r = random.uniform(0, 1)
            for i in range(len(arrayOfScores)):
                prob += arrayOfScores[i][0]
                if r < prob:
                    newPopulation[j] = (population[int(arrayOfScores[j][1])])
        return newPopulation

    def onePointCrossOver(self, population):
        new_population = []
        for i in range(0, len(population) - 1):
            parent1 = population[i]
            parent2 = population[i + 1]
            probability = random.random()

            if probability <= self.crossoverProp_:
                crossover_point = random.randint(0, self.n_)
                for z in range(crossover_point, len(parent1)):
                    parent1[z], parent2[z] = parent2[z], parent1[z]  # zamiana koncowek

                new_population.append(parent1)
                new_population.append(parent2)
            else:
                new_population.append(parent1)
                new_population.append(parent2)
        return new_population

    def mutation(self, population):
        for i in range(len(population)):
            for j in range(len(population[i])):
                if random.uniform(0, 1) < self.mutationProbability_:
                    population[i][j] = 1 if population[i][j] == 0 else 0
        return population

    def rank_select(self, population):
        return sorted(population, key=lambda x: self.fitness_func_hard(x) / (len(population) * (len(population) + 1)) / 2,
                      reverse=True)

    def doublePointCrossOver(self, population):
        new_population = []
        for i in range(0, len(population) - 1):
            parent1 = population[i]
            parent2 = population[i + 1]
            child1 = []
            child2 = []
            probability = random.random()
            if probability <= self.crossoverProp_:
                crossover_point1 = random.randint(0, int(0.5 * self.n_))
                crossover_point2 = random.randint(int(0.5 * self.n_), self.n_)
                for c in range(len(population[i])):
                    if c >= crossover_point1 and i <= crossover_point2:
                        child1.append(parent2[c])
                        child2.append(parent1[c])
                    else:
                        child1.append(parent1[c])
                        child2.append(parent2[c])
                new_population.append(child1)
                new_population.append(child2)
            else:
                new_population.append(parent1)
                new_population.append(parent2)
        return new_population

#Do zabawy Multithreadingiem
    def algo_gen_thread(self, output_queue):
        population = self.generate_population()
        for i in range(self.T_):
            selected = self.rank_select(population)
            newGeneration = self.doublePointCrossOver(selected)
            newGenerationAfterMutation = self.mutation(newGeneration)
        population = sorted(newGenerationAfterMutation, key=lambda x: self.fitness_func_hard(x), reverse=True)
        output_queue.put(population[0])

    def algo_gen_test_thread(self, output_queue):
        population = self.generate_population()
        for i in range(self.T_):
            if self.rank_select_:
                selected = self.rank_select(population)
            else:
                selected = self.roulette_selection(population)
            if self.doubleCrossOver_:
                newGeneration = self.doublePointCrossOver(selected)
            else:
                newGeneration = self.onePointCrossOver(selected)
            newGenerationAfterMutation = self.mutation(newGeneration)
        if self.fitFunc_:
            population = sorted(newGenerationAfterMutation, key=lambda x: self.fitness_func_hard(x), reverse=True)
        else:
            population = sorted(newGenerationAfterMutation, key=lambda x: self.fitness_func_easy(x), reverse=True)
        output_queue.put(population[0])