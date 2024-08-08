package SnakeAndLadder;

import java.util.ArrayList;
import java.util.List;

public class SnakeAndLadderGame {
    private Board board;
    private Dice dice;
    private List<Player> players;

    private int currentPlayerIndex;

    public SnakeAndLadderGame(List<String> playerNames) {
        board=new Board();
        dice=new Dice();
        currentPlayerIndex=0;
        players = new ArrayList<>();
        for(String playerName: playerNames){
            players.add(new Player(playerName));
        }

    }

    public void play(){

        while (!isGameOver()){
            Player currentplayer=players.get(currentPlayerIndex);
            int diceRoll= dice.diceRoll();
            int newPosition=currentplayer.getPosition()+diceRoll;

            if(newPosition<= board.getBoard_Size()){
                //currentPlayer.setPosition(board.getNewPositionAfterSnakeOrLadder(newPosition));
                currentplayer.setPosition(board.getNewPositionAfterSnakeOrLadder(newPosition));
                System.out.println(currentplayer.getName() + " rolled a " + diceRoll +
                        " and moved to position " + currentplayer.getPosition());

            }

            if(currentplayer.getPosition()== board.getBoard_Size()){
                System.out.println(currentplayer.getName() + " wins!");
                break;
            }
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }

    }


    private boolean isGameOver(){
        for(Player player:players){
            if(player.getPosition()==board.getBoard_Size()){
                return true;
            }
        }
        return false;
    }
}
