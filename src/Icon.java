import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Icon extends sprite {
    public Icon(Pane layer, String title, double ingameX, double ingameY, double ingameW, double ingameH) {
        super(layer, title, "Icon", new Rectangle(1, 1), ingameX, ingameY, ingameW, ingameH);
        this.layer.getChildren().remove(shape);
        this.width = this.ingameW * 10;
        this.height = this.ingameH * 10;
        this.shape = new Rectangle(this.width,this.height);
        this.shape.setFill(Color.GREEN);
        this.layer.getChildren().add(shape);

        System.out.println("yes");

        this.render();

    }

    @Override
    public void render(){
        super.render();

    }
}
