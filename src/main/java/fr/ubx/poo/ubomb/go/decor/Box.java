package fr.ubx.poo.ubomb.go.decor;

import fr.ubx.poo.ubomb.game.*;
import fr.ubx.poo.ubomb.go.Movable;
import fr.ubx.poo.ubomb.go.character.Player;

public class Box extends Decor implements Movable {
    public Box(Game game, Position position) {
        super(game,position);
    }

    @Override
    public boolean canMove(Direction direction) {
        boolean can = false;
        Position nextPos = direction.nextPosition(getPosition());
        if (game.inside(nextPos)) {
            can = game.getGrid().get(nextPos) == null;
        }
        return can;
    }

    @Override
    public void doMove(Direction direction) {
        Position nextPos = direction.nextPosition(getPosition());
        if (this.canMove(direction))
            setPosition(nextPos);
    }
}
