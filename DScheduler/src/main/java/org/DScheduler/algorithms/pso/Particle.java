package org.DScheduler.algorithms.pso;

import org.cloudbus.cloudsim.cloudlets.Cloudlet;
import org.cloudbus.cloudsim.vms.Vm;

import java.util.List;
import java.util.Random;

public class Particle {
    private List<Cloudlet> cloudlets;
    private List<Vm> vms;
    private int[] position; // A vector of task-to-VM assignments
    private int[] velocity;
    private int[] personalBestPosition;
    private double personalBestFitness;
    private static final Random random = new Random();

    public Particle(List<Cloudlet> cloudlets, List<Vm> vms) {
        this.cloudlets = cloudlets;
        this.vms = vms;
        this.position = new int[cloudlets.size()];
        this.velocity = new int[cloudlets.size()];
        this.personalBestPosition = new int[cloudlets.size()];
        this.personalBestFitness = Double.MAX_VALUE;

        // Random initialization of position and velocity
        for (int i = 0; i < cloudlets.size(); i++) {
            position[i] = random.nextInt(vms.size());
            velocity[i] = random.nextInt(vms.size());
        }
    }

    public void updateVelocity(Particle globalBestParticle) {
        double inertiaWeight = 0.5;
        double cognitiveCoefficient = 1.5;
        double socialCoefficient = 1.5;

        for (int i = 0; i < position.length; i++) {
            // Update velocity
            velocity[i] = (int) (inertiaWeight * velocity[i] +
                    cognitiveCoefficient * random.nextDouble() * (personalBestPosition[i] - position[i]) +
                    socialCoefficient * random.nextDouble() * (globalBestParticle.position[i] - position[i]));

            // Apply velocity limits
            if (velocity[i] > vms.size() - 1) velocity[i] = vms.size() - 1;
            if (velocity[i] < 0) velocity[i] = 0;
        }
    }

    public void updatePosition() {
        // Update the position based on velocity
        for (int i = 0; i < position.length; i++) {
            position[i] = Math.min(Math.max(position[i] + velocity[i], 0), vms.size() - 1);
        }
    }

    public double evaluateFitness() {
        // Fitness function: calculate makespan or completion time
        double makespan = 0;
        // Here you would implement logic to calculate the makespan of the schedule
        // This can include execution time of cloudlets on VMs
        // A simple example is a weighted sum of VM utilization or completion times
        return makespan;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPersonalBest() {
        double fitness = evaluateFitness();
        if (fitness < personalBestFitness) {
            personalBestFitness = fitness;
            System.arraycopy(position, 0, personalBestPosition, 0, position.length);
        }
    }

    public int[] getPersonalBestPosition() {
        return personalBestPosition;
    }

    public double getPersonalBestFitness() {
        return personalBestFitness;
    }
}