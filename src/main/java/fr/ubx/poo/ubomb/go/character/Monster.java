package fr.ubx.poo.ubomb.go.character;

import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.Movable;

public class Monster extends GameObject implements Movable {

    private Direction direction;
    private int lives;


    public Monster(Game game, Position position, int lives) {
        super(game, position);
        this.direction = Direction.DOWN;
        this.lives = lives;
    }

    public void update(long now) {
        direction = Direction.random();
        if(now%(game.monsterVelocity+5)==0)
            if (canMove(direction)) {
                doMove(direction);
            }
    }

    @Override
    public boolean isWalkable(Player player) {
        return true;
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
        if (canMove(direction)) {
            setPosition(nextPos);
        }
    }


    // Getters
    public Direction getDirection() {
        return direction;
    }

    public int getLives() {
        return lives;
    }
}
