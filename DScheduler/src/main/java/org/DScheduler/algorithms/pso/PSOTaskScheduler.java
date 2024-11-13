package org.DScheduler.algorithms.pso;

import org.DScheduler.algorithms.base.TaskSchedulingAlgorithm;
import org.DScheduler.config.AlgorithmConfig;
import org.cloudbus.cloudsim.cloudlets.Cloudlet;
import org.cloudbus.cloudsim.vms.Vm;

import java.util.ArrayList;
import java.util.List;

public class PSOTaskScheduler implements TaskSchedulingAlgorithm {
    private List<Particle> particles;
    private Particle globalBestParticle;
    private int numberOfParticles;
    private int maxIterations = 100;
    private List<Cloudlet> cloudletList;
    private List<Vm> vmList;

    public PSOTaskScheduler() {
        this.numberOfParticles = AlgorithmConfig.getPsoParticles();
        System.out.println("Executing PSO scheduling algorithm");
    }

    @Override
    public void schedule() {
        // Initialize particles
        particles = new ArrayList<>();
        for (int i = 0; i < numberOfParticles; i++) {
            particles.add(new Particle(cloudletList, vmList));
        }

        // Set the initial global best particle
        globalBestParticle = particles.get(0);

        // Main PSO loop
        for (int iteration = 0; iteration < maxIterations; iteration++) {
            for (Particle particle : particles) {
                particle.setPersonalBest(); // Update personal best
                if (particle.getPersonalBestFitness() < globalBestParticle.getPersonalBestFitness()) {
                    globalBestParticle = particle; // Update global best
                }
            }

            // Update particles
            for (Particle particle : particles) {
                particle.updateVelocity(globalBestParticle); // Update velocity
                particle.updatePosition(); // Update position
            }
        }
    }

    @Override
    public void schedule(List<Cloudlet> cloudletList, List<Vm> vmList) {
        this.cloudletList = cloudletList;
        this.vmList = vmList;

        // Run the PSO scheduling algorithm
        schedule();
    }
}
