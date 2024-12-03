package com.mebae.diparitor;

import com.mebae.diparitor.model.Player;
import com.mebae.diparitor.model.Power;
import com.mebae.diparitor.model.Tournament;
import com.mebae.diparitor.model.Variant;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        // launch();
        // TODO déplacer en tests unitaires

/*
        var variant = Variant.STANDARD_VARIANT;
        var tournament = new Tournament(variant, new Registrations(Map.ofEntries(
            Map.entry(new Player("A", null), 3),
            Map.entry(new Player("B", null), 3),
            Map.entry(new Player("C", null), 3),
            Map.entry(new Player("D", null), 3),
            Map.entry(new Player("E", null), 3),
            Map.entry(new Player("F", null), 3),
            Map.entry(new Player("G", null), 3),
            Map.entry(new Player("H", null), 3),
            Map.entry(new Player("I", null), 3),
            Map.entry(new Player("J", null), 3),
            Map.entry(new Player("K", null), 3),
            Map.entry(new Player("L", null), 3),
            Map.entry(new Player("M", null), 3),
            Map.entry(new Player("N", null), 3),
            Map.entry(new Player("O", null), 3),
            Map.entry(new Player("P", null), 3),
            Map.entry(new Player("Q", null), 3),
            Map.entry(new Player("R", null), 3),
            Map.entry(new Player("S", null), 3),
            Map.entry(new Player("T", null), 3),
            Map.entry(new Player("U", null), 3),
            Map.entry(new Player("V", null), 3),
            Map.entry(new Player("W", null), 3),
            Map.entry(new Player("X", null), 3),
            Map.entry(new Player("Y", null), 3),
            Map.entry(new Player("Z", null), 2)
        ))); */

        var variant = new Variant("Simple", Set.of(new Power("France", 0), new Power("Turquie", 0), new Power(
          "Autriche", 0)));
        var tournament = new Tournament(variant, Map.ofEntries(
            Map.entry(new Player("A", "Luile"), 1),
            Map.entry(new Player("B", "Stiol"), 1),
            Map.entry(new Player("C", "Sharp"), 1)
        ));

        tournament.generateGames();
    }
}
