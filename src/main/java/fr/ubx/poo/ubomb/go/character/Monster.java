package fr.ubx.poo.ubomb.go.character;

import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.Movable;
import fr.ubx.poo.ubomb.go.decor.Door;

public class Monster extends Character {

    private int velCpt = game.monsterVelocity;


    public Monster(Game game, Position position, int lives) {
        super(game, position,lives);
    }

    void reinitCpt(int addValue) {
        this.velCpt = game.monsterVelocity + addValue;
    }


    public void update(long now) {
        velCpt--;
        if(velCpt == 0) {
            reinitCpt(100);
            setDirection(Direction.random());
            if (canMove(getDirection())) {
                doMove(getDirection());
            }
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

    public void monsterCollision(GameObject g) {
        if (getPosition().equals(g.getPosition())) {

            if (!isInvincible()) {
                System.out.println("Damage\n");
                takeDamageMonster();
                setInvincibility(true);
            }
        }
    }

    public void takeDamageMonster() {
        setLives(game.monsterLives--);
    }

}
