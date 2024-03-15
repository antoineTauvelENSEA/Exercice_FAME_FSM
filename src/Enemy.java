import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Enemy extends DynamicSprite{
    private boolean sees;
    private final double startingX;
    private final double startingY;
    private long timeStamp=System.currentTimeMillis();
    private EnemyBehaviour enemyBehaviour = EnemyBehaviour.IDLE;
    private boolean catchTheHero = false;

    public boolean hasCatchTheHero() {
        return catchTheHero;
    }

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
    }

    public void updateFSM(){
        if (sees){
            enemyBehaviour = EnemyBehaviour.CHARGING;
        }
        else{
            switch(enemyBehaviour){
                case IDLE : enemyBehaviour= EnemyBehaviour.IDLE;
                    break;
                case CHARGING  :
                    timeStamp = System.currentTimeMillis();
                    enemyBehaviour=EnemyBehaviour.SUSPISCIOUS;
                    break;
                case SUSPISCIOUS:
                    if (System.currentTimeMillis()-timeStamp>2000){
                        enemyBehaviour = EnemyBehaviour.IDLE;
                    }
                    break;
            }
        }
    }

    public void updatePosition(Hero hero, ArrayList<Sprite> environment){
        if (enemyBehaviour != EnemyBehaviour.SUSPISCIOUS){
            double targetPositionX = enemyBehaviour==EnemyBehaviour.CHARGING?hero.x:startingX;
            double targetPositionY = enemyBehaviour==EnemyBehaviour.CHARGING?hero.y:startingY;
            moveIfPossible(((targetPositionX-x)>0)?5:-5,(targetPositionY-y)>0?5:-5,environment);
        }
        Rectangle largeHitBox = (Rectangle) hitBox.clone();
        largeHitBox.width= largeHitBox.width+10;
        largeHitBox.height= largeHitBox.height+10;
        largeHitBox.x= largeHitBox.x-5;
        largeHitBox.y= largeHitBox.y-5;
        catchTheHero=hero.hitBox.intersects(largeHitBox);
    }

}