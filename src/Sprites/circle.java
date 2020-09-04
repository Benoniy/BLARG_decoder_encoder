package Sprites;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class circle extends sprite {

    public circle(Pane layer, String title, double ingameX, double ingameY, double ingameW, double ingameH, Color color) {
        super(layer, title, "Sprites.circle", new Circle(0), ingameX, ingameY, ingameW, ingameH);
        this.color = color;
        this.width = width / 2;
        this.layer.getChildren().remove(shape);
        this.shape = new Circle(this.width);
        this.shape.setFill(this.color);
        this.layer.getChildren().add(shape);
        this.render();
    }

    @Override
    public void render(){
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

        y = 505 - height * 0.5;
        x = 505 - width;



        double tempx = ingameX * 10;
        double tempy = ingameY * 10;


        x += tempx;
        y -= tempy;


        shape.relocate(x, y);
    }
}
