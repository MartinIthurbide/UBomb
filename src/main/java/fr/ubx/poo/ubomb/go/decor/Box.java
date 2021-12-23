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
        boolean can = true;
        Position nextPos = direction.nextPosition(getPosition());
        // todo : condition ne marche pas
        /*if (game.inside(nextPos)){
            can = true;
        }*/
        return can;
    }

    @Override
    public void doMove(Direction direction) {
        //if (canMove(nextPos))
        setPosition(direction.nextPosition(getPosition()));
        System.out.println("Position Box : "+getPosition()+"\n");
    }
}
