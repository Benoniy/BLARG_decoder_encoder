import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Instance extends sprite {
    static int screen = 1;
    static Color bkColor = Color.BLACK;
    static Rectangle shape = new Rectangle(1010,1010);

    public Instance(Pane pane, String title){
        super(pane, title, "bk", shape, 0, 0, 101, 101);
        shape.setFill(bkColor);
    }

    public void setScreen(int screen){
        this.screen = screen;
    }

}

