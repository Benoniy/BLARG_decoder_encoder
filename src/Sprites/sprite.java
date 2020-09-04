package Sprites;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class sprite {

    Pane layer;
    String title;
    String type;
    String sprite;
    double width, height, x, y;

    // Works bottom left to top right from -50 to 50 inclusive for each value a total of 101x101
    // Max ingame width is 101 min is 1
    double ingameX, ingameY, ingameW, ingameH;
    Shape shape;
    Color color;

    public sprite(Pane layer, String title, String sprite, Shape shape, double ingameX, double ingameY, double ingameW, double ingameH){
        this.layer = layer;
        this.shape = shape;
        this.ingameX = ingameX;
        this.ingameY = ingameY;
        this.ingameW = ingameW;
        this.ingameH = ingameH;
        this.title = title;
        this.sprite = sprite;
        this.type = "sprite";

        width = ingameW * 10;
        height = ingameH * 10;

        layer.getChildren().add(shape);
    }

    public void checkBounds(){
        if (ingameX > 50){
            ingameX = 50;
        }
        else if (ingameX < -50){
            ingameX = -50;
        }

        if (ingameY > 50){
            ingameY = 50;
        }
        else if (ingameY < -50){
            ingameY = -50;
        }
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

    public void setColor(Color color){
        this.color = color;
        shape.setFill(color);
    }
}