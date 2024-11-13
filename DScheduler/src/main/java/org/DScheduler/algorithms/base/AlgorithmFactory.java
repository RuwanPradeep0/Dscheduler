package org.DScheduler.algorithms.base;

import org.DScheduler.algorithms.ga.GATaskScheduler;
import org.DScheduler.algorithms.pca.PCATaskScheduler;
import org.DScheduler.algorithms.pso.PSOTaskScheduler;
import org.DScheduler.config.AlgorithmConfig;

public class AlgorithmFactory {
    public static TaskSchedulingAlgorithm createAlgorithm(AlgorithmConfig.SchedulingAlgorithm type) {
        return switch (type) {
            case PSO -> new PSOTaskScheduler();
            case PCA -> new PCATaskScheduler();
            default -> new GATaskScheduler();
        };
    }
}
