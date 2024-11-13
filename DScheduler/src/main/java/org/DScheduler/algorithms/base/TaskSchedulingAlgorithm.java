package org.DScheduler.algorithms.base;

import org.cloudbus.cloudsim.cloudlets.Cloudlet;
import org.cloudbus.cloudsim.vms.Vm;

import java.util.List;

public interface TaskSchedulingAlgorithm {
    void schedule();  // For basic or setup logic

    void schedule(List<Cloudlet> cloudletList, List<Vm> vmList);  // For scheduling tasks with resources
}
