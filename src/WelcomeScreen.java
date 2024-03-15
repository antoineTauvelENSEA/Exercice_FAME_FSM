import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class WelcomeScreen extends JPanel {
    Image welcome;
    final int blinkPeriod=1000;
    public WelcomeScreen() throws Exception{
        welcome = ImageIO.read(new File("./image/welcomeScreen.jpg"));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(welcome,0,0,null);
        if (System.currentTimeMillis()%blinkPeriod<blinkPeriod/2){
            g.setColor(Color.YELLOW);
            g.setFont(new Font("TimesRoman",Font.BOLD,24));
            g.drawString("Press any key to start !",120,255);
        }
    }
}
