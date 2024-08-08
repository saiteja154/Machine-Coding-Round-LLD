package SnakeAndLadder;

import java.util.Random;

public class Dice {
    public int diceRoll(){
        Random random=new Random();

        return random.nextInt(6)+1;
    }
}
