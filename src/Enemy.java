import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Enemy extends DynamicSprite{
    private boolean sees;
    private final double startingX;
    private final double startingY;
    private long timeStamp=0;

    public Enemy(double x, double y, Image image) {
        super(x, y, image);
        this.startingX=x;
        this.startingY=y;

        this.hitBox.height=43;
        this.hitBox.width=40;
    }

    public boolean sees (DynamicSprite sprite, ArrayList<Sprite> environment){
        Line2D.Double lineOfSight = new Line2D.Double((double) this.x+this.hitBox.width/2,(double) this.y+this.hitBox.height/2,
                            (double) sprite.x+sprite.hitBox.width/2,(double) sprite.y+sprite.hitBox.height/2);

        for (Sprite element :environment){
            if (element instanceof SolidSprite && (element != this) && (element != sprite)) {
                if (lineOfSight.intersects(((SolidSprite) element).hitBox)){
                    sees=false;
                    return false;
                }
            }
        }
        sees=true;
        return true;
    }

    public void draw(Graphics g) {
        g.drawImage(image,(int) x,(int) y,(int) x+40,(int) y+ 43,0,0,40,43,null);
        g.setColor(Color.BLUE);
        g.setFont(new Font("TimesRoman",Font.BOLD,24));
        g.drawString(sees?"Gotcha !":"Where are you ?", 0,25);
    }

}