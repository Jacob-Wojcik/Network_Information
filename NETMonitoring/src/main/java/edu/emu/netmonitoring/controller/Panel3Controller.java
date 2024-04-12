package edu.emu.netmonitoring.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Panel3Controller {

    @FXML
    private Label networkStatusLabel;

    @FXML
    private Label dateTimeLabel;

    @FXML
    private Label ipAddressLabel;

    @FXML
    private Label networkTypeLabel;

    @FXML
    private Button toggleButton;

    public void initialize() {
        startNetworkMonitoring();
        startDateTimeUpdate();
        displayNetworkInfo();
    }

    private void startNetworkMonitoring() {
        // Implement network monitoring logic
    }

    private void startDateTimeUpdate() {
        // Implement date and time update logic
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

    @FXML
    private void toggleVisibility() {
        // Implement toggle visibility logic
    }
}
