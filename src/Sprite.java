import java.awt.*;

public class Sprite {
    protected double x;
    protected double y;
    protected Image image;

    public Sprite(double x, double y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public void draw(Graphics g) {
        g.drawImage(image,(int) x,(int) y, null);
    }
}
