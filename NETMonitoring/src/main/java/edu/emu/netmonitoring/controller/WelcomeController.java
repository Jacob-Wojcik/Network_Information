package edu.emu.netmonitoring.controller;

import edu.emu.netmonitoring.NetMonitoringApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class WelcomeController {

    @FXML
    protected void onLaunchButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader dashboardFXML = new FXMLLoader(NetMonitoringApplication.class.getResource("/edu/emu/netmonitoring/fxml/dashboard.fxml"));
        Parent dashboardRoot = dashboardFXML.load();
        Scene dashboardScene  = new Scene(new BorderPane(dashboardRoot));

        Stage dashboardStage = new Stage();
        dashboardStage.setResizable(false);
        dashboardStage.initStyle(StageStyle.TRANSPARENT);
        dashboardStage.setScene(dashboardScene);
        dashboardStage.show();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onExitButtonClick(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

}