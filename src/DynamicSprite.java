import java.awt.*;
import java.util.ArrayList;

public class DynamicSprite extends SolidSprite {

    public DynamicSprite(double x, double y, Image image) {
        super(x, y, image);
    }

    void moveIfPossible(double dx, double dy, ArrayList<Sprite> listOfObstacle){
        this.hitBox.x = (int) (this.hitBox.x+dx);
        this.hitBox.y = (int) (this.hitBox.y+dy);
        boolean moveIsPossible = true;

        for (Sprite s : listOfObstacle){
            if (s instanceof SolidSprite && (s!=this)){
                if(((SolidSprite) s).hitBox.intersects(this.hitBox)){
                    moveIsPossible = false;
                    break;
                }
            }
        }

        if(moveIsPossible){
            this.x=this.x+dx;
            this.y=this.y+dy;
        }
        else{
            this.hitBox.x = (int)(this.hitBox.x-dx);
            this.hitBox.y = (int)(this.hitBox.y-dy);
        }
    }

    @Override
    public void draw(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect((int)x,(int)y,this.hitBox.width, this.hitBox.height);
    }
}
