package fr.ubx.poo.ubomb.go.decor;

import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.Movable;

public class DecorMonster extends Decor implements Movable {
    public DecorMonster(Game game, Position position) {
        super(game, position);
    }

    public DecorMonster(Position position) {
        super(position);
    }

    @Override
    public boolean canMove(Direction direction) {
        return true;
    }

    @Override
    public void doMove(Direction direction) {
        Direction d = Direction.random(); // VERIF SI RANDOM FAITE
        //if(d.nextPosition(getPosition()))
    }
}
