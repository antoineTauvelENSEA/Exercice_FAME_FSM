import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameRender extends JPanel {
    private ArrayList<Sprite> listOfSprites = new ArrayList<>();
    public GameRender(){
        super();
    }
    public void addOneSprite(Sprite s){
        listOfSprites.add(s);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Sprite s : listOfSprites){
            s.draw(g);
        }
    }

    public ArrayList<Sprite> getListOfSprites() {
        return listOfSprites;
    }
}
