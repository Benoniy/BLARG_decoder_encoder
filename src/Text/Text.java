package Text;

import Base.GuiElement;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Text extends GuiElement {
    public Text(Pane layer, String title, String type, String data, double ingameX, double ingameY, double ingameW, double ingameH) {
        super(layer, title, type, data, ingameX, ingameY, ingameW, ingameH);
    }

    @Override
    public void setColor(Color color) {

    }
}
