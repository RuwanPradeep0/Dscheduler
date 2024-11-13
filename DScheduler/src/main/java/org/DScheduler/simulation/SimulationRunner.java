package org.DScheduler.simulation;

import org.DScheduler.algorithms.pso.PSOTaskScheduler;
import org.DScheduler.broker.CustomBroker;
import org.DScheduler.config.SimulationConfig;
import org.DScheduler.datacenter.DatacenterBuilder;
import org.DScheduler.models.EvaluationMetrics;
import org.cloudbus.cloudsim.cloudlets.Cloudlet;
import org.cloudbus.cloudsim.cloudlets.CloudletSimple;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.datacenters.Datacenter;
import org.cloudbus.cloudsim.utilizationmodels.UtilizationModelDynamic;
import org.cloudbus.cloudsim.vms.Vm;
import org.cloudbus.cloudsim.vms.VmSimple;

import java.util.ArrayList;
import java.util.List;

public class SimulationRunner {
    public static void main(String[] args) {
        CloudSim simulation = new CloudSim();

        // Create Datacenter
        Datacenter datacenter = DatacenterBuilder.createDatacenter(simulation);

        // Create Broker
        CustomBroker broker = new CustomBroker(simulation);
        broker.setSchedulingAlgorithm(new PSOTaskScheduler());

        // Create VMs
        List<Vm> vmList = createVms();
        broker.submitVmList(vmList);

        // Create Cloudlets
        List<Cloudlet> cloudletList = createCloudlets();
        broker.submitCloudletList(cloudletList);

        // Start the simulation
        simulation.start();

        // Evaluate and print results
        double makespan = EvaluationMetrics.calculateMakespan(cloudletList);
        double vmUtilization = EvaluationMetrics.calculateAverageVMUtilization(vmList, cloudletList);  // Pass cloudletList here

        System.out.println("Makespan: " + makespan);
        System.out.println("Average VM Utilization: " + vmUtilization);

        // Print detailed results
        new ResultAnalyzer().printResults(broker.getCloudletFinishedList());
    }

    private static List<Vm> createVms() {
        List<Vm> vmList = new ArrayList<>();

        for (int i = 0; i < SimulationConfig.getNumberOfVms(); i++) {
            Vm vm = new VmSimple(i, 500, 1) // Reduce MIPS if host resources are limited
                    .setRam(2048) // Set VM RAM lower if necessary to avoid resource conflicts
                    .setBw(SimulationConfig.getHostBw() / 5)
                    .setSize(10000); // VM disk size

            vmList.add(vm);
        }
        return vmList;
    }


    private static List<Cloudlet> createCloudlets() {
        List<Cloudlet> cloudletList = new ArrayList<>();

        // Loop through the number of cloudlets specified in the SimulationConfig
        for (int i = 0; i < SimulationConfig.getNumberOfCloudlets(); i++) {
            // Create a Cloudlet with specific properties (id, length, number of PEs, etc.)
            Cloudlet cloudlet = new CloudletSimple(i, 10000, 1) // id, length, number of processing elements
                    .setUtilizationModel(new UtilizationModelDynamic(0.5)); // Example utilization model

            // Add the cloudlet to the list
            cloudletList.add(cloudlet);
        }
        return cloudletList;
    }
}
