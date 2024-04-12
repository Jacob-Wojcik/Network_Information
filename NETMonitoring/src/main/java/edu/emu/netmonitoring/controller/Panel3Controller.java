// Panel3Controller.java
package edu.emu.netmonitoring.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;
import java.io.IOException;
import java.net.InetAddress;

public class Panel3Controller {

    @FXML
    private Label latencyLabel;

    private String ipAddress = "8.8.8.8"; // Google's DNS server
    private int timeout = 1000; // Timeout in milliseconds

    @FXML
    public void initialize() {
        // Set up a Timeline to refresh latency every 2 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> refreshLatency()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void refreshLatency() {
        try {
            long startTime = System.nanoTime();
            InetAddress address = InetAddress.getByName(ipAddress);
            boolean reachable = address.isReachable(timeout);
            long endTime = System.nanoTime();

            if (reachable) {
                // Calculate latency in milliseconds
                double latencyMS = (endTime - startTime) / 1e6;
                latencyLabel.setText("Latency: " + latencyMS + " ms");
            } else {
                latencyLabel.setText("Latency: Unreachable");
            }
        } catch (IOException e) {
            e.printStackTrace();
            latencyLabel.setText("Latency: Error");
        }
    }
}
