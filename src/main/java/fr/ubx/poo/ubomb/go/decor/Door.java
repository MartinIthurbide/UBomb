package fr.ubx.poo.ubomb.go.decor;

import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Player;

import java.io.IOException;

public class Door  extends Decor {

    public static final int CLOSED = 0;
    public static final int OPENED = 1;

    public static final int BACK = 0;
    public static final int NEXT = 1;


    private int state;
    private int sens;

    public Door(Game game, Position position) {
        super(game, position);
    }

    public Door(Game game, Position position, int etat, int sens) {
        super(game, position);
        this.state = etat;
        this.sens = sens;
    }

    public int getState() {
        return state;
    }

    public void open() {
        if(getState() == CLOSED) {
            state = OPENED;
            //explode();
        }
    }

    public void takenBy(Player player) {

            if (sens == BACK) { // BACK LEVEL
                game.currentLevel--;
                }
            else { // NEXT LEVEL
                game.currentLevel++;
            }
            // todo : load map suivante
        System.out.println(game.currentLevel);
    }

    public boolean isWalkable(Player player) {
        if (getState() == OPENED)
            return true;
        return false;
    }


}
