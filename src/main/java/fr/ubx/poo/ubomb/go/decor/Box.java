package fr.ubx.poo.ubomb.go.decor;

import fr.ubx.poo.ubomb.game.*;
import fr.ubx.poo.ubomb.go.Movable;
import fr.ubx.poo.ubomb.go.character.Player;
import fr.ubx.poo.ubomb.go.decor.bonus.Bonus;
import javafx.geometry.Pos;

public class Box extends Decor implements Movable {
    public Box(Position position) {
        super(position);
    }


    @Override
    public boolean canMove(Direction direction) {
        return true;
    }

    @Override
    public void doMove(Direction direction) {
        Position nextPos = direction.nextPosition(getPosition());
        setPosition(nextPos);
        //game.getGrid().set(nextPos,this);
        //game.getGrid().remove(getPosition());
        // todo : changer la position dans la grid
        System.out.println("Position Box : "+getPosition()+"\n");
    }
}
