import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameEngine implements ActionListener{

    private ArrayList<Sprite> spriteArrayList = new ArrayList<>();
    private Hero hero;

    public GameEngine(ArrayList<Sprite> spriteArrayList, Hero hero) {
        this.spriteArrayList = spriteArrayList;
        this.hero = hero;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            for (Sprite sprite : spriteArrayList){
                if (sprite instanceof Enemy){
                    ((Enemy) sprite).sees(hero, spriteArrayList);
                    ((Enemy) sprite).updateFSM();
                    ((Enemy) sprite).updatePosition(hero,spriteArrayList);
                }
            }
            hero.updateFSM();
            hero.updatePosition();

    }
}
