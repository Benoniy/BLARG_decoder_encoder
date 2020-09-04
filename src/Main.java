import Sprites.Sprite;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    public static int SCREEN_WIDTH = 1010;
    public static int SCREEN_HEIGHT = 1010;

    public static Instance bk;

    public static Pane bkPane, objPane;
    public static Scene mainScene;
    public static Group layers;

    public static ArrayList<Sprite> sprites = new ArrayList<>();

    @Override
    public void start(Stage mainStage) {
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        layers = new Group();

        bkPane = new Pane();
        objPane = new Pane();
        layers.getChildren().add(bkPane);
        layers.getChildren().add(objPane);

        mainScene = new Scene(layers, SCREEN_WIDTH, SCREEN_HEIGHT);

        mainStage.setScene(mainScene);
        mainStage.show();

        TXT_DECODER.decode("C:\\Users\\Ben\\Desktop\\TESTui.txt");

        loop();
    }

    public void loop(){
        AnimationTimer gameLoop = new AnimationTimer() {

            @Override
            public void handle(long now) {

                bk.render();
                for (Sprite s : sprites){
                    s.render();
                }
            }

        };
        gameLoop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
