import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SolidSprite extends Sprite{
    protected Rectangle hitBox;
    public SolidSprite(double x, double y, Image image) {
        super(x, y, image);
        hitBox = new Rectangle((int) x,(int) y,
                image.getWidth(null),image.getHeight(null));
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
}
