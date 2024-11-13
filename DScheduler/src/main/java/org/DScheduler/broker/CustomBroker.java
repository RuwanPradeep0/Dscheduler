package org.DScheduler.broker;

import org.DScheduler.algorithms.base.TaskSchedulingAlgorithm;
import org.cloudbus.cloudsim.brokers.DatacenterBroker;
import org.cloudbus.cloudsim.brokers.DatacenterBrokerSimple;
import org.cloudbus.cloudsim.cloudlets.Cloudlet;
import org.cloudbus.cloudsim.core.CloudSim;

import java.util.List;

public class CustomBroker extends DatacenterBrokerSimple {
    private TaskSchedulingAlgorithm schedulingAlgorithm;

    public CustomBroker(CloudSim simulation) {
        super(simulation);
    }

    public void setSchedulingAlgorithm(TaskSchedulingAlgorithm algorithm) {
        this.schedulingAlgorithm = algorithm;
    }

    @Override
    public DatacenterBroker submitCloudletList(List<? extends Cloudlet> list) {
        if (schedulingAlgorithm != null && !getVmExecList().isEmpty()) {
            schedulingAlgorithm.schedule((List<Cloudlet>) list, getVmExecList());
        }
        super.submitCloudletList(list);
        return null;
    }
}
