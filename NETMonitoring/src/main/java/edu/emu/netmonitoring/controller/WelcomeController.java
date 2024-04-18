package edu.emu.netmonitoring.controller;

import edu.emu.netmonitoring.NetMonitoringApplication;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class WelcomeController {

    @FXML
    protected void onLaunchButtonClick(ActionEvent actionEvent) throws IOException {
        // Load the loading screen
        FXMLLoader loader = new FXMLLoader(NetMonitoringApplication.class.getResource("/edu/emu/netmonitoring/fxml/loadingScreen.fxml"));
        Parent loadingRoot = loader.load();
        Scene loadingScene = new Scene(loadingRoot);

        Stage loadingStage = new Stage();
        loadingStage.initStyle(StageStyle.TRANSPARENT);
        loadingStage.setScene(loadingScene);
        loadingStage.show();

        // Create a timeline for the delay
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(5), // Delay duration
                event -> {
                    try {
                        // Load the dashboard screen after the delay
                        FXMLLoader dashboardFXML = new FXMLLoader(NetMonitoringApplication.class.getResource("/edu/emu/netmonitoring/fxml/dashboard.fxml"));
                        Parent dashboardRoot = dashboardFXML.load();
                        Scene dashboardScene = new Scene(new BorderPane(dashboardRoot));

                        Stage dashboardStage = new Stage();
                        dashboardStage.setResizable(false);
                        dashboardStage.initStyle(StageStyle.TRANSPARENT);
                        dashboardStage.setScene(dashboardScene);
                        dashboardStage.show();

                        // Close the loading screen
                        loadingStage.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }));
        timeline.play();

        // Close the welcome screen
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onExitButtonClick(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
