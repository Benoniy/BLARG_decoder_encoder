package Text;

import Base.GuiElement;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.awt.*;


public class Text extends GuiElement {
    public javafx.scene.text.Text shape;
    private String anchor;
    private Font font;
    private double scale = 7.4;
    private double size;

    public Text(Pane layer, String title, String data, String anchor, double ingameX, double ingameY, double size, Color color) {
        super(layer, title, "text", data, ingameX, ingameY, size, size);
        this.anchor = anchor;
        this.color = Color.GREEN;
        this.size = size;
        shape = new javafx.scene.text.Text(data);
        this.font = Font.font ("Verdana", scale * this.size);
        shape.setFont(font);
        shape.setFill(color);

        javafx.scene.text.Text t2 = new javafx.scene.text.Text(data);
        new Scene(new Group(t2));
        t2.setFont(font);
        t2.applyCss();
        this.width = t2.getLayoutBounds().getWidth();
        this.height = t2.getLayoutBounds().getHeight();

        layer.getChildren().add(shape);

        this.x = ingameX * 10;
        this.y = -ingameY * 10;
        this.x += 505;
        this.y += 505;


        this.y -= height * 0.5;

        System.out.println(data);
        System.out.println(ingameX);
        System.out.println(x);
        System.out.println("\n");
    }


    @Override
    public void setColor(Color color) {

    }

    @Override
    public void render() {
        this.checkBounds();

        this.x = ingameX * 10;
        this.y = -ingameY * 10;
        this.x += 505;
        this.y += 505;

        if (anchor.equals("center")){
            this.x -= width * 0.5;
        }

        this.y -= height * 0.5;

        shape.relocate(x, y);
    }
}
