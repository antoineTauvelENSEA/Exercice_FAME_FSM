import java.awt.*;

public class Hero extends DynamicSprite {
    private final int timeBetweenFramesInMs=150;
    private final int timeBeforeSleeping=1500;
    private final int timeBeforeAsleep=1000;
    private long lastMesuredTime;
    private final int numberOfAttitudes=8;
    private int numberOfFramesInCurrentAttitude=10;
    private int attitude = 4;

    private final int width = 96;
    private final int height = 104;


    private boolean isWalking=false;
    private boolean isSleeping=false;
    private boolean isAsleep=false;

    public Hero(double x, double y, Image image) {
        super(x, y, image);
        this.hitBox.width = width/2;
        this.hitBox.height = height/2;
    }

    @Override
    public void draw(Graphics g){
        int localAttitude=attitude;
        int index=0;
        if (!isWalking && !isAsleep && ! isSleeping){
            if (System.currentTimeMillis()-lastMesuredTime>timeBeforeAsleep){
                isAsleep=true;
                lastMesuredTime = System.currentTimeMillis();
            }
        }
        if (isWalking)
        {
            index = (int) ((System.currentTimeMillis()/timeBetweenFramesInMs)%numberOfFramesInCurrentAttitude);
            localAttitude = attitude + 4;
            isAsleep = false;
            isSleeping = false;
        }
        if (isAsleep){
            index = (attitude != 2)?1:0;
            if (System.currentTimeMillis()-lastMesuredTime>timeBeforeSleeping){
                isAsleep=false;
                isSleeping=true;
            }
        }
        if (isSleeping){
            index = (attitude != 2)?2:0;
        }
        g.drawImage(image,(int)x,(int)y,(int) x+width/2,(int) y+height/2,
                        index * width, localAttitude * height,
                        (index+1)*width, (localAttitude+1)*height,
                null);
    }

    public void setWalking(boolean walking) {
        isWalking = walking;
        if (walking){
            isAsleep=false;
            isSleeping=false;
        }
        else{
            lastMesuredTime = System.currentTimeMillis();
        }
    }

    public void setAttitude(int attitude) {
        this.attitude = attitude;
    }
}
