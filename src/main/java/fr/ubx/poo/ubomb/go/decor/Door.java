package fr.ubx.poo.ubomb.go.decor;

import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Player;

public abstract class Door  extends Decor {

    public Door(Game game, Position position) {
        super(game, position);
    }

    public Door(Position position) {
        super(position);
    }

    public void takenBy(Player player, int level) {

        player.takeDoor(level);
    }

    public abstract boolean doorState();

}
