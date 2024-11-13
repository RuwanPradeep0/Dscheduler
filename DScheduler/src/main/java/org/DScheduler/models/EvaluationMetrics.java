package org.DScheduler.models;

import org.cloudbus.cloudsim.cloudlets.Cloudlet;
import org.cloudbus.cloudsim.vms.Vm;

import java.util.List;

public class EvaluationMetrics {

    // Calculate Makespan: the maximum finish time of all cloudlets
    public static double calculateMakespan(List<Cloudlet> cloudlets) {
        double maxFinishTime = 0;
        for (Cloudlet cloudlet : cloudlets) {
            if (cloudlet.getFinishTime() > maxFinishTime) {
                maxFinishTime = cloudlet.getFinishTime();
            }
        }
        return maxFinishTime;
    }

    // Calculate Average VM Utilization (based on CPU usage of cloudlets)
    public static double calculateAverageVMUtilization(List<Vm> vms, List<Cloudlet> cloudlets) {
        double totalUtilization = 0;
        for (Vm vm : vms) {
            totalUtilization += calculateCpuUtilization(vm, cloudlets);
        }
        return totalUtilization / vms.size();
    }

    // Helper method to calculate CPU utilization of a VM based on assigned cloudlets
    private static double calculateCpuUtilization(Vm vm, List<Cloudlet> cloudlets) {
        double totalCpuTime = 0;
        double totalMips = vm.getMips();  // Get MIPS value for the VM

        // Calculate total CPU time spent by the cloudlets assigned to this VM
        for (Cloudlet cloudlet : cloudlets) {
            if (cloudlet.getVm().getId() == vm.getId()) {
                totalCpuTime += cloudlet.getActualCpuTime();  // Use cloudlet's CPU time
            }
        }

        // Assuming totalMips is the capacity, calculate utilization as the ratio
        return totalCpuTime / (totalMips * 1000);  // Normalize by multiplying with MIPS and 1000
    }
}
