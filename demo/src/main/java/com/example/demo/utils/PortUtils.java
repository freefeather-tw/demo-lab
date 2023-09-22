package com.example.demo.utils;


import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class PortUtils {

    public static String findAvailablePort(int minPort, int maxPort) {

        int portRange = maxPort - minPort;
        int candidatePort;
        int searchCounter = 0;
        do {
            if (searchCounter > portRange) {
                throw new RuntimeException("Could not find an available port above " + minPort
                        + " after " + searchCounter + " attempts");
            }
            candidatePort = findRandomPort(minPort, maxPort);
            searchCounter++;
        }
        while (!available(candidatePort));

        return String.valueOf(candidatePort);
    }

    public static int findRandomPort(int minPort, int maxPort) {
        return minPort >= 0 && maxPort > minPort ? minPort + RandomUtils.nextInt(maxPort - minPort) : -1;
    }

    public static boolean available(int port) {
        if (port <= 0) {
            return false;
        }
        try {
            new java.net.ServerSocket(port).close();
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
}
