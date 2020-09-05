package Sprites;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Icon extends Sprite {
    public Icon(Pane layer, String title, String sprite, double ingameX, double ingameY, double ingameW, double ingameH) {
        super(layer, title, sprite, new Rectangle(1, 1), ingameX, ingameY, ingameW, ingameH);
        this.layer.getChildren().remove(shape);
        this.ingameH = ingameW;
        this.width = this.ingameW * 10;
        this.height = this.ingameH * 10;
        this.shape = new Rectangle(this.width,this.height);
        this.shape.setFill(Color.GREEN);
        this.layer.getChildren().add(shape);
        this.render();
    }
}
