package fr.ubx.poo.ubomb.go.decor;

import fr.ubx.poo.ubomb.game.*;
import fr.ubx.poo.ubomb.go.Movable;
import fr.ubx.poo.ubomb.go.character.Player;

public class Box extends Decor implements Movable {
    public Box(Position position) {
        super(position);
    }

    @Override
    public boolean canMove(Direction direction) {
        if (!game.inside(direction.nextPosition(this.getPosition())))
            return false;
        Decor d = this.game.getGrid().get(direction.nextPosition(this.getPosition()));
        if (d != null)
            return false;
        return true;
    }

    @Override
    public void doMove(Direction direction) {
        Position nextPos = direction.nextPosition(getPosition());
        setPosition(nextPos);
    }
}
