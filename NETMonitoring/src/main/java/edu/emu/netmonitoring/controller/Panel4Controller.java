// Panel4Controller.java
package edu.emu.netmonitoring.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Panel4Controller {

    @FXML
    private TextField hostTextField;

    @FXML
    private Label tracerouteStatusLabel;

    @FXML
    private TextArea tracerouteResultArea;

    @FXML
    public void traceroute() {
        String host = hostTextField.getText().trim();
        if (host.isEmpty()) {
            tracerouteStatusLabel.setText("Please enter a host name or IP address.");
            return;
        }

        try {
            // Specify the full path to the tracert executable
            Process tracertProcess = Runtime.getRuntime().exec("C:\\Windows\\System32\\tracert.exe " + host);
            BufferedReader reader = new BufferedReader(new InputStreamReader(tracertProcess.getInputStream()));

            String line;
            StringBuilder tracerouteResult = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                tracerouteResult.append(line).append("\n");
            }

            int exitCode = tracertProcess.waitFor();
            if (exitCode == 0) {
                tracerouteStatusLabel.setText("Traceroute to " + host + " completed.");
            } else {
                tracerouteStatusLabel.setText("Traceroute to " + host + " failed.");
            }

            tracerouteResultArea.setText(tracerouteResult.toString());
        } catch (IOException | InterruptedException e) {
            tracerouteStatusLabel.setText("Error: " + e.getMessage());
        }
    }
}








































/*
package edu.emu.netmonitoring.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class Panel4Controller {

    @FXML
    private TextField hostTextField;

    @FXML
    private Label tracerouteStatusLabel;

    @FXML
    private TextArea tracerouteResultArea;

    @FXML
    public void traceroute() {
        String host = hostTextField.getText().trim();
        if (host.isEmpty()) {
            tracerouteStatusLabel.setText("Please enter a host name or IP address.");
            return;
        }

        try {
            List<String> hops = traceroute(host);
            StringBuilder result = new StringBuilder();
            for (String hop : hops) {
                result.append(hop).append("\n");
            }
            tracerouteResultArea.setText(result.toString());
            tracerouteStatusLabel.setText("Traceroute to " + host + " completed.");
        } catch (IOException e) {
            tracerouteStatusLabel.setText("Error: " + e.getMessage());
        }
    }

    private List<String> traceroute(String hostname) throws IOException {
        List<String> hops = new ArrayList<>();
        InetAddress address;
        try {
            address = InetAddress.getByName(hostname);
        } catch (IOException e) {
            throw new IOException("Unknown host: " + hostname);
        }

        for (int ttl = 1; ttl <= 30; ttl++) {
            long startTime = System.currentTimeMillis();
            try {
                InetAddress hopAddress = InetAddress.getByName(tracerouteHop(address, ttl));
                long endTime = System.currentTimeMillis();
                long timeTaken = endTime - startTime;
                hops.add(hopAddress.getHostAddress() + " (" + timeTaken + " ms)");
                if (hopAddress.equals(address)) {
                    break; // Reached the destination
                }
            } catch (IOException e) {
                hops.add("*");
            }
        }

        return hops;
    }

    private String tracerouteHop(InetAddress address, int ttl) throws IOException {
        // Implement sending and receiving ICMP packets with specified TTL here
        // This is a simplified example and does not include actual packet sending/receiving
        return address.getHostAddress(); // Replace with actual traceroute logic
    }
}
*/
