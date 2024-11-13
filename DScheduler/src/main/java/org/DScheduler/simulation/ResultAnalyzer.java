package org.DScheduler.simulation;

import org.cloudbus.cloudsim.cloudlets.Cloudlet;

import java.util.List;

public class ResultAnalyzer {
    public void printResults(List<Cloudlet> finishedCloudlets) {
        System.out.println("========== Simulation Results ==========");
        System.out.println("Cloudlet ID | Status | Data center ID | VM ID | Time | Start Time | Finish Time");

        for (Cloudlet cloudlet : finishedCloudlets) {
            System.out.printf("%-10d | %-6s | %-14d | %-5d | %-4.1f | %-10.1f | %-10.1f\n",
                    cloudlet.getId(),
                    cloudlet.getStatus(),
                    cloudlet.getVm().getHost().getDatacenter().getId(),
                    cloudlet.getVm().getId(),
                    cloudlet.getActualCpuTime(),
                    cloudlet.getExecStartTime(),
                    cloudlet.getFinishTime()
            );
        }
    }
}