package org.DScheduler.util;

import org.cloudbus.cloudsim.cloudlets.Cloudlet;

import java.util.List;

public class TaskUtils {

    public static void sortCloudletsByPriority(List<Cloudlet> cloudlets) {
        cloudlets.sort((c1, c2) -> (int) (c1.getLength() - c2.getLength())); // Sorting by length as an example
    }

    public static double calculateTotalCompletionTime(List<Cloudlet> cloudlets) {
        return cloudlets.stream().mapToDouble(Cloudlet::getFinishTime).sum();
    }

    // Add more utility methods as needed
}
