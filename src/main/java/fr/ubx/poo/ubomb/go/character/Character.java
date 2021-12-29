package fr.ubx.poo.ubomb.go.character;

import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.Movable;

public abstract class Character extends GameObject implements Movable {

    Direction direction;
    private int lives;
    private boolean invincibility;

    public Character(Game game, Position position, int lives) {
        super(game, position);
        this.direction = Direction.DOWN;
        this.lives = lives;
        this.invincibility = false;

    }

    public Character(Position position) {
        super(position);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }

    public abstract boolean canMove(Direction direction);

    public abstract void doMove(Direction direction);

    public abstract void update(long now);

    public boolean isInvincible() {
        return invincibility;
    }

    public void setInvincibility(boolean invincibility) {
        this.invincibility = invincibility;
    }

    public void takeDamage() {
        setLives(getLives()-1);
    }

}
