import java.awt.*;
import java.awt.event.KeyEvent;

public class Hero extends DynamicSprite {
    private final int timeBetweenFramesInMs=150;
    private final int timeBeforeSleeping=1500;
    private final int timeBeforeAsleep=1000;
    private long lastMesuredTime;

    private GameRender environment;
    private int keyPressed;

    public void setKeyPressed(int keyPressed) {
        this.keyPressed = keyPressed;
        if (this.keyPressed == 0) {
            lastMesuredTime=System.currentTimeMillis();
        }
    }

    private final int width = 96;
    private final int height = 104;

    private HeroBehaviour heroBehaviour = HeroBehaviour.SLEEPING_SOUTH;

    public Hero(double x, double y, Image image, GameRender environment) {
        super(x, y, image);
        this.hitBox.width = width/2;
        this.hitBox.height = height/2;
        this.environment=environment;
    }

    @Override
    public void draw(Graphics g){
        int index;
        if(heroBehaviour.shouldRoll()){
            index=(int) ((System.currentTimeMillis()/timeBetweenFramesInMs)% heroBehaviour.getNumberOfFrameInCurrentAttitude());}
        else{
            index = heroBehaviour.getNumberOfFrameInCurrentAttitude()-1;
        }
        g.drawImage(image,(int)x,(int)y,(int) x+width/2,(int) y+height/2,
                        index * width, heroBehaviour.getAttitude() * height,
                        (index+1)*width, (heroBehaviour.getAttitude()+1)*height,null);
    }

    public void setHeroBehaviour(HeroBehaviour heroBehaviour) {
        this.heroBehaviour = heroBehaviour;
    }

    public void updateFSM(){
        if (keyPressed != 0){
            switch (keyPressed){
                case KeyEvent.VK_UP:
                    this.setHeroBehaviour(HeroBehaviour.WALKING_NORTH);
                    break;
                case KeyEvent.VK_DOWN:
                    this.setHeroBehaviour(HeroBehaviour.WALKING_SOUTH);
                    break;
                case KeyEvent.VK_LEFT:
                    this.setHeroBehaviour(HeroBehaviour.WALKING_WEST);
                    break;
                case KeyEvent.VK_RIGHT:
                    this.setHeroBehaviour(HeroBehaviour.WALKING_EAST);
                    break;
                }
            }
        else{
            switch (heroBehaviour){
                case SLEEPY_SOUTH -> {
                    if (System.currentTimeMillis()-lastMesuredTime>1500) heroBehaviour=HeroBehaviour.SLEEPING_SOUTH;
                }
                case WALKING_SOUTH -> {
                    heroBehaviour=HeroBehaviour.SLEEPY_SOUTH;
                }
                case SLEEPY_WEST -> {
                    if (System.currentTimeMillis()-lastMesuredTime>1500) heroBehaviour=HeroBehaviour.SLEEPING_WEST;
                }
                case WALKING_WEST -> {
                    heroBehaviour=HeroBehaviour.SLEEPY_WEST;
                }
                case SLEEPY_NORTH -> {
                    if (System.currentTimeMillis()-lastMesuredTime>1500) heroBehaviour=HeroBehaviour.SLEEPING_NORTH;
                }
                case WALKING_NORTH -> {
                    heroBehaviour=HeroBehaviour.SLEEPY_NORTH;
                }
                case SLEEPY_EAST -> {
                    if (System.currentTimeMillis()-lastMesuredTime>1500) heroBehaviour=HeroBehaviour.SLEEPING_EAST;
                }
                case WALKING_EAST -> {
                    heroBehaviour=HeroBehaviour.SLEEPY_EAST;
                }
            }
        }
    }

    public void updatePosition(){
        switch(heroBehaviour){
            case WALKING_EAST -> moveIfPossible(10,0,environment.getListOfSprites());
            case WALKING_WEST -> moveIfPossible(-10,0,environment.getListOfSprites());
            case WALKING_NORTH -> moveIfPossible(0,-10,environment.getListOfSprites());
            case WALKING_SOUTH -> moveIfPossible(0,10,environment.getListOfSprites());
        }

    }
}
