package Sprites;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Sprite extends Base.GuiElement {



    // Works bottom left to top right from -50 to 50 inclusive for each value a total of 101x101
    // Max ingame width is 101 min is 1
    Shape shape;

    public Sprite(Pane layer, String title, String data, Shape shape, double ingameX, double ingameY, double ingameW, double ingameH){
        super(layer, title, "sprite", data, ingameX, ingameY, ingameW, ingameH);
        this.shape = shape;

        width = ingameW * 10;
        height = ingameH * 10;

        layer.getChildren().add(shape);
    }



    public void render(){
        this.checkBounds();

        y = 505 - height*0.5;
        x = 505 - width*0.5;

        double tempx = ingameX * 10;
        double tempy = ingameY * 10;

        x += tempx;
        y -= tempy;

        shape.relocate(x, y);
    }

    @Override
    public void setColor(Color color){
        this.color = color;
        shape.setFill(color);
    }
}
