import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class GUI extends JFrame implements KeyListener {
    GameRender gameRender = new GameRender();
    private Hero hero;
    private DynamicSprite enemy;


    public GUI() throws Exception {
        super("Welcome in Cergy");
        super.setSize(400,600);
        super.add(gameRender);
        super.setVisible(true);

        this.addKeyListener(this);

        Image tree = ImageIO.read(new File("./image/tree.png"));
        Image grass = ImageIO.read(new File("./image/grass.png"));
        Image rock = ImageIO.read(new File("./image/rock.png"));
        Image enemySpriteSheet = ImageIO.read(new File("./image/enemySpriteSheet.png"));

        Image spriteSheetLink = ImageIO.read(new File("./image/LinkSpriteSheet.png"));
        for (int i=0;i<8*12;i++){
            gameRender.addOneSprite(new Sprite((i%8)*grass.getWidth(null),
                    i/12*grass.getHeight(null),grass));
        }

        gameRender.addOneSprite(new SolidSprite(164,240,tree));
        gameRender.addOneSprite(new SolidSprite(150,120,rock));
        gameRender.addOneSprite(new Enemy(70,190,enemySpriteSheet));

        hero = new Hero(200,330,spriteSheetLink);
        gameRender.addOneSprite(hero);
        Timer timerGameEngine = new Timer(100, new GameEngine(gameRender.getListOfSprites(), hero));
        timerGameEngine.start();
    }

    public static void main (String[] args) throws Exception {
        GUI gui= new GUI();
        Timer animationTimer = new Timer(150, e-> {
            gui.repaint();
        });
        animationTimer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                hero.setHeroBehaviour(HeroBehaviour.WALKING_NORTH);
                hero.moveIfPossible(0,-10,gameRender.getListOfSprites());
                break;
            case KeyEvent.VK_DOWN:
                hero.setHeroBehaviour(HeroBehaviour.WALKING_SOUTH);
                hero.moveIfPossible(0,10,gameRender.getListOfSprites());
                break;
            case KeyEvent.VK_LEFT:
                hero.setHeroBehaviour(HeroBehaviour.WALKING_WEST);
                hero.moveIfPossible(-10,0,gameRender.getListOfSprites());
                break;
            case KeyEvent.VK_RIGHT:
                hero.setHeroBehaviour(HeroBehaviour.WALKING_EAST);
                hero.moveIfPossible(10,0,gameRender.getListOfSprites());
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
      //  hero.setHeroBehaviour(HeroBehaviour.SLEEPY_SOUTH);
    }
}
