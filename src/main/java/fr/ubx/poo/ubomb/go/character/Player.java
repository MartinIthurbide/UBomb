/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubomb.go.character;

import fr.ubx.poo.ubomb.engine.Input;
import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.EntityCode;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.Bomb;
import fr.ubx.poo.ubomb.go.GameObject;

import fr.ubx.poo.ubomb.go.Movable;
import fr.ubx.poo.ubomb.go.decor.Box;
import fr.ubx.poo.ubomb.go.decor.Decor;
import fr.ubx.poo.ubomb.go.decor.Door;
import fr.ubx.poo.ubomb.go.decor.bonus.*;


public class Player extends GameObject implements Movable {

    private Direction direction;
    private boolean moveRequested = false;
    private int lives;

    public Player(Game game, Position position, int lives) {
        super(game, position);
        this.direction = Direction.DOWN;
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }

    public Direction getDirection() {
        return direction;
    }

    public void getBackBomb() {

    }

    public void requestMove(Direction direction) {
        if (direction != this.direction) {
            this.direction = direction;
            setModified(true);
        }
        moveRequested = true;
    }

    public final boolean dropBomb (){
        if (game.bombCapacity > 0){
            setModified(true);
            return true;
        }
        return false;
    }

    public final boolean canMove(Direction direction) {
        // Revoir pour voir si on souhaite marcher sur une Box
        boolean can = false;
        Position nextPos = direction.nextPosition(getPosition());
        if (game.inside(nextPos)) {
            can = true;
            if (game.getGrid().get(nextPos) != null) {
                can = game.getGrid().get(nextPos).isWalkable(this);
            }
        }
        return can;
    }

    public void update(long now) {
        if (moveRequested) {
            if (canMove(direction)) {
                doMove(direction);
            }
        }
        moveRequested = false;
    }

    public void doMove(Direction direction) {
        // Check if we need to pick something up
        Position nextPos = direction.nextPosition(getPosition());
        Decor d;
        if ((d = game.getGrid().get(nextPos)) instanceof Bonus) {
           Bonus b = (Bonus) d;
           b.takenBy(this);
        }
        setPosition(nextPos);
    }


    @Override
    public boolean isWalkable(Player player) {
        return false;
    }

    @Override
    public void explode() {
    }

    // Example of methods to define by the player
    // TODO : Programmer les fonctions de récuperation de bonus
    public boolean takeDoor(int gotoLevel) {
        Position nextPos = getDirection().nextPosition(getPosition());
            if((game.getGrid().get(nextPos)) instanceof Door) {
                Door d = (Door) game.getGrid().get(nextPos);
                System.out.println(d.getState());
                d.takenBy(this,1);
                System.out.println(d.getState());
                return true;
            }
            return false;
    }
    public void takeKey() {
        game.nbKeys++;
    }
    public void takeHeart() {
        game.playerHearts++;
    }
    public void takeBombNumberInc() {
        if(game.bombCapacity < game.bombBagCapacity)
            game.bombCapacity++;
    }
    public void takeBombNumberDec() {
        if(game.bombCapacity > 1)
            game.bombCapacity--;
    }
    public void takeBombRangerInc() {
        game.bombRange++;
    }
    public void takeBombRangerDec() {
        if(game.bombRange > 1)
            game.bombRange--;
    }
    public void takePrincess() {
        game.won = true;
    }


    public boolean isWinner() {
        return game.won;
    }

}
