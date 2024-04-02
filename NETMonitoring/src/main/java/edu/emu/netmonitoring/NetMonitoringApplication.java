package edu.emu.netmonitoring;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class NetMonitoringApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(NetMonitoringApplication.class.getResource("/edu/emu/netmonitoring/fxml/welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        //String cssPath = this.getClass().getResource("/edu/emu/netmonitoring/css/welcome.css").toExternalForm();
        String cssPath = Objects.requireNonNull(this.getClass().getResource("/edu/emu/netmonitoring/css/welcome.css")).toExternalForm();
        scene.getStylesheets().add(cssPath);

        stage.setTitle("NET Monitoring");
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

//        FXMLLoader dashboardFXML = new FXMLLoader(NetMonitoringApplication.class.getResource("/edu/emu/netmonitoring/fxml/dashboard.fxml"));
//        Parent dashboardRoot = dashboardFXML.load();
//        Scene dashboardScene  = new Scene(new BorderPane(dashboardRoot));
//
//        Stage dashboardStage = new Stage();
//
//        Duration delay = Duration.seconds(3);
//        Timeline timeline = new Timeline(new KeyFrame(delay, event -> {
//            stage.close();
//
//            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//            dashboardStage.setX((screenBounds.getWidth() - 1820) / 2);
//            dashboardStage.setY((screenBounds.getHeight() - 782) / 2);
//
//            dashboardStage.setScene(dashboardScene);
//            dashboardStage.initStyle(StageStyle.TRANSPARENT);
//            dashboardStage.show();
//        }));
//        timeline.play();

    }

    public static void main(String[] args) {
        launch();
    }
}