import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameEngine implements ActionListener{
    private GameState gameState = GameState.WELCOME;
    private ArrayList<Sprite> spriteArrayList = new ArrayList<>();
    private Hero hero;
    private GUI root; // Shame on me...

    public GameEngine(ArrayList<Sprite> spriteArrayList, Hero hero, GUI root) {
        this.spriteArrayList = spriteArrayList;
        this.hero = hero;
        this.root = root;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            if (gameState == GameState.PLAYING){
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
            fsmEvolve(0);
    }

    public GameState getGameState() {
        return gameState;
    }

    public void fsmEvolve(int e){
        switch (gameState) {
            case WELCOME:
                if (e!=0){
                    gameState = GameState.PLAYING;
                    root.switchToPlaying();
                }
                break;
            case PLAYING:
                for (Sprite enemy :spriteArrayList){
                    if (enemy instanceof Enemy){
                        if (((Enemy) enemy).hasCatchTheHero()){
                            gameState = GameState.GAME_OVER;
                            root.switchToGameOver();
                        }
                    }
                }
                break;
        }
    }
}
