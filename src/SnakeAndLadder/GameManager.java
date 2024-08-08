package SnakeAndLadder;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private static GameManager instance;
    private List<SnakeAndLadderGame> games;

    public GameManager(){
        games=new ArrayList<>();
    }

    public static synchronized GameManager getInstance(){
        if(instance==null){
            instance=new GameManager();
        }

        return instance;
    }

    // handiling multiple games at a time using Treads.
    public void startNewGame(List<String> playerNames){
        SnakeAndLadderGame game=new SnakeAndLadderGame(playerNames);
        games.add(game);
        new Thread(()-> game.play()).start();

    }
}
