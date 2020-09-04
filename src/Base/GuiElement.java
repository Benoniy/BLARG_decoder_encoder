package Base;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public abstract class GuiElement {
    public Pane layer;
    public String title, type, data;
    public double width, height, x, y;
    public double ingameX, ingameY, ingameW, ingameH;
    public Color color;

    public GuiElement(Pane layer, String title, String type, String data, double ingameX, double ingameY, double ingameW, double ingameH){
        this.layer = layer;
        this.title = title;
        this.type = type;
        this.data = data;
        this.ingameX = ingameX;
        this.ingameY = ingameY;
        this.ingameW = ingameW;
        this.ingameH = ingameH;
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

    public abstract void setColor(Color color);

    public abstract void render();
}
