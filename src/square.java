import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class square extends sprite{


    public square(Pane layer, String title, double ingameX, double ingameY, double ingameW, double ingameH, Color color) {
        super(layer, title, "square", new Rectangle(1,1), ingameX, ingameY, ingameW, ingameH);
        this.color = color;
        this.layer.getChildren().remove(shape);
        this.shape = new Rectangle(this.width,this.height);
        this.shape.setFill(this.color);
        this.layer.getChildren().add(shape);
        this.render();
    }
}
