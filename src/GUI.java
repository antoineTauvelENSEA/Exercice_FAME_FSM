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
        super.setSize(8*65,8*65);
        super.add(gameRender);
        super.setVisible(true);

        this.addKeyListener(this);

        Image tree = ImageIO.read(new File("./image/tree.png"));
        Image grass = ImageIO.read(new File("./image/grass.png"));
        Image rock = ImageIO.read(new File("./image/rock.png"));
        Image enemySpriteSheet = ImageIO.read(new File("./image/enemySpriteSheet.png"));

        Image spriteSheetLink = ImageIO.read(new File("./image/LinkSpriteSheet.png"));
        for (int i=0;i<8*8;i++){
            gameRender.addOneSprite(new Sprite((i%8)*grass.getWidth(null),
                    i/8*grass.getHeight(null),grass));
        }

        gameRender.addOneSprite(new SolidSprite(164,240,tree));
        gameRender.addOneSprite(new SolidSprite(150,120,rock));
        gameRender.addOneSprite(new Enemy(70,190,enemySpriteSheet));
        gameRender.addOneSprite(new Enemy(270,190,enemySpriteSheet));
        gameRender.addOneSprite(new Enemy(70,350,enemySpriteSheet));

        hero = new Hero(200,330,spriteSheetLink,gameRender);
        gameRender.addOneSprite(hero);
        Timer timerGameEngine = new Timer(75, new GameEngine(gameRender.getListOfSprites(), hero));
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
        hero.setKeyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        hero.setKeyPressed(0);
    }
}
