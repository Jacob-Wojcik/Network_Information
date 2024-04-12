package edu.emu.netmonitoring.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.net.InetAddress;
import java.util.Enumeration;

public class HomePanelController {

    @FXML
    private Label networkStatusLabel;

    @FXML
    private Label dateTimeLabel;

    @FXML
    private Label ipAddressLabel;

    @FXML
    private Label networkTypeLabel;

    public void initialize() {
        // Initialize your controller, if needed
        startNetworkMonitoring();
        startDateTimeUpdate();
        displayNetworkInfo();
    }

    private void startNetworkMonitoring() {
        Thread monitorThread = new Thread(() -> {
            while (true) {
                boolean isConnected = checkInternetConnection();
                Platform.runLater(() -> updateNetworkStatus(isConnected));

                // Sleep for a while before checking again
                try {
                    Thread.sleep(5000); // Check every 5 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        monitorThread.setDaemon(true);
        monitorThread.start();
    }

    private void startDateTimeUpdate() {
        Thread dateTimeThread = new Thread(() -> {
            while (true) {
                Platform.runLater(this::updateDateTime);
                try {
                    Thread.sleep(1000); // Update every second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        dateTimeThread.setDaemon(true);
        dateTimeThread.start();
    }

    private boolean checkInternetConnection() {
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            return address.isReachable(5000); // Timeout: 5 seconds
        } catch (Exception e) {
            return false;
        }
    }

    private void updateNetworkStatus(boolean isConnected) {
        if (isConnected) {
            networkStatusLabel.setText("Connected");
            networkStatusLabel.setStyle("-fx-text-fill: white; -fx-background-color: #4CAF50; -fx-alignment: center; -fx-text-alignment: center;"); // Green with centered text
        } else {
            networkStatusLabel.setText("Disconnected");
            networkStatusLabel.setStyle("-fx-text-fill: white; -fx-background-color: #ff6347; -fx-alignment: center; -fx-text-alignment: center;"); // Red with centered text
        }
        networkStatusLabel.setTextAlignment(TextAlignment.CENTER);
    }

    private void updateDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = dateFormat.format(new Date());
        dateTimeLabel.setText(dateTime);
    }

    private void displayNetworkInfo() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                if (!networkInterface.isLoopback() && networkInterface.isUp()) {
                    Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddress = inetAddresses.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            ipAddressLabel.setText("IP Address: " + inetAddress.getHostAddress());
                            networkTypeLabel.setText("Network Type: " + networkInterface.getDisplayName());
                            return;
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        ipAddressLabel.setText("IP Address: N/A");
        networkTypeLabel.setText("Network Type: N/A");
    }

}


