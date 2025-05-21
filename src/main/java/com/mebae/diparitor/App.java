package com.mebae.diparitor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

  private static Scene scene;

  static void setRoot(String fxml) throws IOException {
    scene.setRoot(loadFXML(fxml));
  }

  private static Parent loadFXML(String fxml) throws IOException {
    var fxmlLoader = new FXMLLoader(App.class.getResource("/" + fxml + ".fxml"));
    return fxmlLoader.load();
  }

  public static void main(String[] args) {
/*    // Very Simple (2 powers 8 participants): OK
    var variant = new Variant("Simple", "Source", Set.of(new Power("France", 10), new Power("Turquie", 22)), true);
    var players = Set.of(new Player(new PlayerInfo("A", ""), 3), new Player(new PlayerInfo("B", ""), 2),
            new Player(new PlayerInfo("C", ""), 2), new Player(new PlayerInfo("D", ""), 1));
    // Normal (7 powers, 21 participants): StackOverflow
//    var variant = Variant.STANDARD_VARIANT;
//    var players = Set.of(new Player(new PlayerInfo("A", ""), 2), new Player(new PlayerInfo("B", ""), 2),
//            new Player(new PlayerInfo("C", ""), 2), new Player(new PlayerInfo("D", ""), 2),
//            new Player(new PlayerInfo("E", ""), 2), new Player(new PlayerInfo("F", ""), 2),
//            new Player(new PlayerInfo("G", ""), 2), new Player(new PlayerInfo("H", ""), 2),
//            new Player(new PlayerInfo("I", ""), 2), new Player(new PlayerInfo("J", ""), 2),
//            new Player(new PlayerInfo("K", ""), 1));

    var bestTournament = computeBestTournament(variant, players);
    System.out.println(bestTournament);*/

    launch();
  }

  @Override
  public void start(Stage stage) throws IOException {
    scene = new Scene(loadFXML("primary"), 640, 480);
    stage.setScene(scene);
    stage.show();
  }
}
