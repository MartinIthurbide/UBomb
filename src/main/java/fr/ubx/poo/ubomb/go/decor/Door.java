package fr.ubx.poo.ubomb.go.decor;

import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Player;

public class Door  extends Decor {

    public static final int CLOSED = 0;
    public static final int OPENED = 1;

    private int state;

    public Door(Game game, Position position) {
        super(game, position);
    }

    public Door(Position position, int etat) {
        super(position);
        state = etat;
    }

    public int getState() {
        return state;
    }

    public void takenBy(Player player, int level) {
        if(state == OPENED)
            player.takeDoor(level);
        else {
            state = OPENED; // SI ET SEULEMENT SI CA A OUVERT LA PORTE
        }
    }


}
