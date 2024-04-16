package edu.emu.netmonitoring.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Panel2Controller {

    @FXML
    private TableView<NetworkInterfaceInfo> tableView;
    @FXML
    private TableColumn<NetworkInterfaceInfo, String> cInterfaceName, cDisplayName, cIPAddress, cStatus, cIPType;

    // There are javafx components in this file that are linked with an fxml file
    // initialize is called to build / populate a table utilizing that layout
    public void initialize() {
        this.cInterfaceName.setCellValueFactory(new PropertyValueFactory<>("interfaceName"));
        this.cDisplayName.setCellValueFactory(new PropertyValueFactory<>("displayName"));
        this.cIPAddress.setCellValueFactory(new PropertyValueFactory<>("ipAddress"));
        this.cStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        this.cIPType.setCellValueFactory(new PropertyValueFactory<>("IPType"));

        // populate the table with the appropriate information
        tableView.setItems(getNetworkInterfaces());
    }

    // the core of the class. This is the Network Interface Information
    public static class NetworkInterfaceInfo {

        private String cInterfaceName;
        private String cDisplayName;
        private String cIPAddress;
        private String cStatus;

        public NetworkInterfaceInfo(String cInterfaceName, String cDisplayName, String cIPAddress, String cStatus) {
            this.cInterfaceName = cInterfaceName;
            this.cDisplayName = cDisplayName;
            this.cIPAddress = cIPAddress;
            this.cStatus = cStatus;
        }

        public String getInterfaceName() {
            return cInterfaceName;
        }

        public String getDisplayName() {
            return cDisplayName;
        }

        public String getIpAddress() {
            return cIPAddress;
        }

        public String getStatus() {
            return cStatus;
        }

        // created a hack of how to tell if a network was ipv4 or ipv6
        public String getIPType() {

            if (getIpAddress().contains(":")) {

                return "IPv6";
            } else if (getIpAddress().contains(".")) {
                return "IPv4";
            } else {
                return "Unknown";
            }


        }


    }

    // really the whole thing. Collection of the Information about all the network interfaces
    private ObservableList<NetworkInterfaceInfo> getNetworkInterfaces() {
        // a place to store all the info
        ObservableList<NetworkInterfaceInfo> networkInterfaces = FXCollections.observableArrayList();

        try {
            // try to run through all network interfaces on a local machine
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            // loop through them... while theres another network interface
            while (interfaces.hasMoreElements()) {
                // snag all of the addresses assigned to it
                NetworkInterface individualInterface = interfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = individualInterface.getInetAddresses();
                // while there are addresses to go through
                while (inetAddresses.hasMoreElements()) {
                    // add a new instance of that class to the list
                    // snag its info
                    InetAddress theAddress = inetAddresses.nextElement();
                    networkInterfaces.add(new NetworkInterfaceInfo(individualInterface.getName(), individualInterface.getDisplayName(), theAddress.getHostAddress(), individualInterface.isUp() ? "Up" : "Down"));
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        // return all the info
        return networkInterfaces;
    }

}