package fr.ubx.poo.ubomb.go.decor;

import fr.ubx.poo.ubomb.game.*;
import fr.ubx.poo.ubomb.go.Movable;
import fr.ubx.poo.ubomb.go.character.Player;
import fr.ubx.poo.ubomb.go.decor.bonus.Bonus;

public class Box extends Decor implements Movable {
    public Box(Position position) {
        super(position);
    }

    @Override
    public boolean canMove(Direction direction) {
        Position nextPos = direction.nextPosition(getPosition());
        // todo : condition ne marche pas
        if (game.getGrid().get(nextPos) instanceof Bonus){
            return false;
        }
        if (!game.inside(nextPos)){
            return false;
        }
        return true;
    }

    @Override
    public void doMove(Direction direction) {
        setPosition(direction.nextPosition(getPosition()));
        System.out.println("Position Box : "+getPosition()+"\n");
    }
}
