package fr.ubx.poo.ubomb.go.character;

import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.Movable;

public class Monster extends GameObject implements Movable {
    //TODO: Réussir à afficher les monstres
    private Direction direction;
    private boolean alive;

    public Monster(Position position) {
        super(position);
        this.direction = Direction.DOWN;
        this.alive = true;
    }

    @Override
    public boolean isWalkable(Player player) {
        return true;
    }

    @Override
    public boolean canMove(Direction direction) {
        return true;
    }

    @Override
    public void doMove(Direction direction) {

    }
}
