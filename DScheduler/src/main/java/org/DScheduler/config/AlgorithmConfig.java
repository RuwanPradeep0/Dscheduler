package org.DScheduler.config;

public class AlgorithmConfig {
    public enum SchedulingAlgorithm {
        PSO,
        GA,
        PCA,
        DEEP_LEARNING
    }

    // PSO-specific parameters
    private static final int PSO_PARTICLES = 30;
    private static final double PSO_COGNITIVE = 2.0;
    private static final double PSO_SOCIAL = 2.0;


    // GA-specific parameters (For future implementation)
    private static final int GA_POPULATION_SIZE = 50;
    private static final double GA_MUTATION_RATE = 0.05;


    // PCA-specific parameters (For future implementation)
    private static final int PCA_COMPONENTS = 10;

    public static int getPsoParticles() {
        return PSO_PARTICLES;
    }

    public static double getPsoCognitive() {
        return PSO_COGNITIVE;
    }

    public static double getPsoSocial() {
        return PSO_SOCIAL;
    }

    // Add getters for other algorithms' parameters
    public static int getGaPopulationSize() {
        return GA_POPULATION_SIZE;
    }

    public static double getGaMutationRate() {
        return GA_MUTATION_RATE;
    }

    public static int getPcaComponents() {
        return PCA_COMPONENTS;
    }

    // Other configuration methods as needed for different algorithms
}
