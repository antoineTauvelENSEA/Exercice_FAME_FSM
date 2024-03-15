import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GameOverScreen extends JPanel {
    Image gameOver;
    final int blinkPeriod=1000;
    public GameOverScreen() throws Exception{
        gameOver = ImageIO.read(new File("./image/gameOverScreen.jpg"));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(gameOver,0,0,null);
        if (System.currentTimeMillis()%blinkPeriod<blinkPeriod/2){
            g.setColor(Color.RED);
            g.setFont(new Font("TimesRoman",Font.BOLD,24));
            g.drawString("LOOOOSER !",200,255);
        }
    }
}
