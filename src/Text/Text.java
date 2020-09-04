package Text;

import Base.GuiElement;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Text extends GuiElement {
    public javafx.scene.text.Text shape;
    private String anchor;

    public Text(Pane layer, String title, String data, String anchor, double ingameX, double ingameY) {
        super(layer, title, "text", data, ingameX, ingameY, 1, 1);
        shape = new javafx.scene.text.Text(data);
        if (anchor.equals("left")){
        }
        else if (anchor.equals("right")){
        }
        else {
        }
    }

    @Override
    public void setColor(Color color) {

    }

    @Override
    public void render() {

    }
}
