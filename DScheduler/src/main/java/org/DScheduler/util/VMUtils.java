package org.DScheduler.util;

import org.cloudbus.cloudsim.vms.Vm;

import java.util.List;

public class VMUtils {

    // Calculate the total MIPS for all VMs (using getMips)
    public static double calculateTotalVMUsage(List<Vm> vms) {
        return vms.stream().mapToDouble(Vm::getMips).sum();  // Summing up the MIPS of all VMs
    }

    // More VM-related utilities can be added here
}
