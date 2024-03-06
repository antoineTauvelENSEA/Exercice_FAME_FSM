import java.awt.*;

public class Hero extends DynamicSprite {
    private final int timeBetweenFramesInMs=150;
    private final int timeBeforeSleeping=1500;
    private final int timeBeforeAsleep=1000;
    private long lastMesuredTime;
    private int numberOfFramesInCurrentAttitude=10;

    private final int width = 96;
    private final int height = 104;

    private HeroBehaviour heroBehaviour = HeroBehaviour.SLEEPING_SOUTH;

    public Hero(double x, double y, Image image) {
        super(x, y, image);
        this.hitBox.width = width/2;
        this.hitBox.height = height/2;
    }

    @Override
    public void draw(Graphics g){
        int index=(int) ((System.currentTimeMillis()/timeBetweenFramesInMs)% heroBehaviour.getNumberOfFrameInCurrentAttitude());
        g.drawImage(image,(int)x,(int)y,(int) x+width/2,(int) y+height/2,
                        index * width, heroBehaviour.getAttitude() * height,
                        (index+1)*width, (heroBehaviour.getAttitude()+1)*height,null);
    }

    public void setHeroBehaviour(HeroBehaviour heroBehaviour) {
        this.heroBehaviour = heroBehaviour;
    }
}
