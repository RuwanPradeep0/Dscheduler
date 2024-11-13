package org.DScheduler.config;

public class SimulationConfig {
    private static final int NUMBER_OF_VMS = 5;
    private static final int NUMBER_OF_CLOUDLETS = 20;
    private static final int HOST_MIPS = 1000;
    private static final int HOST_RAM = 16000;
    private static final int HOST_STORAGE = 1000000;
    private static final int HOST_BW = 10000;

    public static int getNumberOfVms() { return NUMBER_OF_VMS; }
    public static int getNumberOfCloudlets() { return NUMBER_OF_CLOUDLETS; }
    public static int getHostMips() { return HOST_MIPS; }
    public static int getHostRam() { return HOST_RAM; }
    public static int getHostStorage() { return HOST_STORAGE; }
    public static int getHostBw() { return HOST_BW; }
}