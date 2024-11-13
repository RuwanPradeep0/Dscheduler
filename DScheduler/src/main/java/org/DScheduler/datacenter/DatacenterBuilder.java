package org.DScheduler.datacenter;

import org.DScheduler.config.SimulationConfig;
import org.cloudbus.cloudsim.allocationpolicies.VmAllocationPolicySimple;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.datacenters.Datacenter;
import org.cloudbus.cloudsim.datacenters.DatacenterSimple;
import org.cloudbus.cloudsim.hosts.Host;
import org.cloudbus.cloudsim.hosts.HostSimple;
import org.cloudbus.cloudsim.resources.Pe;
import org.cloudbus.cloudsim.resources.PeSimple;

import java.util.ArrayList;
import java.util.List;



public class DatacenterBuilder {
    public static Datacenter createDatacenter(CloudSim simulation) {
        List<Host> hostList = new ArrayList<>();

        // Create Hosts based on the number of required hosts
        for (int i = 0; i < 2; i++) { // For example, create 2 hosts
            List<Pe> peList = new ArrayList<>();
            for (int j = 0; j < 4; j++) { // Each host has 4 processing elements (cores)
                peList.add(new PeSimple(SimulationConfig.getHostMips()));
            }

            Host host = new HostSimple(SimulationConfig.getHostRam(),
                    SimulationConfig.getHostBw(),
                    SimulationConfig.getHostStorage(),
                    peList);

            hostList.add(host);
        }

        return new DatacenterSimple(simulation, hostList, new VmAllocationPolicySimple());
    }
}
