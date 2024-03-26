package edu.emu.netmonitoring.controller;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;

public class NetworkLister {

    public static void listNetworkInterfaces() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface iface : Collections.list(interfaces)) {
                System.out.println("Interface Name: " + iface.getName());
                System.out.println("Interface Display Name: " + iface.getDisplayName());
                System.out.println("Is up? " + iface.isUp());
                System.out.println("Is loopback? " + iface.isLoopback());
                System.out.println("Is virtual? " + iface.isVirtual());
                System.out.println("Hardware Address: " + Arrays.toString(iface.getHardwareAddress()));
                System.out.println("MTU: " + iface.getMTU());

                Enumeration<InetAddress> inetAddresses = iface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress addr = inetAddresses.nextElement();
                    System.out.println("IP Address: " + addr.getHostAddress());
                    scanHost(String.valueOf(addr));
                }

                System.out.println("-------------------------------------------------------");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void scanHost(String hostAddress) {
        System.out.println("Scanning host: " + hostAddress);
        for (int port = 1; port <= 1000; port++) {
            System.out.println("Attempting to connect to port " + port + " on " + hostAddress);
            long startTime = System.currentTimeMillis();
            try (Socket socket = new Socket()) {
                socket.connect(new java.net.InetSocketAddress(hostAddress, port), 5000); // Set timeout to 5000ms
                long endTime = System.currentTimeMillis();
                System.out.println("Port " + port + " is open on " + hostAddress + ". Connection attempt took " + (endTime - startTime) + " ms");
            } catch (Exception ignored) {
                ignored.printStackTrace();
                long endTime = System.currentTimeMillis();
                // Uncomment the following line if you want to see the timing for failed connection attempts as well
                System.out.println("Port " + port + " is closed on " + hostAddress + ". Connection attempt took " + (endTime - startTime) + " ms");
            }
        }
    }

    public static void main(String[] args) {
        listNetworkInterfaces();
    }
}
