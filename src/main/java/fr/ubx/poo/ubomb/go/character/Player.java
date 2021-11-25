/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubomb.go.character;

import fr.ubx.poo.ubomb.engine.Input;
import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
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

    public void requestMove(Direction direction) {
        if (direction != this.direction) {
            this.direction = direction;
            setModified(true);
        }
        moveRequested = true;
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
            getBonus((Bonus) d);
        }
        setPosition(nextPos);
    }

    private void getBonus(Bonus b) {
        if(b instanceof Key) {
            takeKey();
            b.takenBy(this);
        }
        if(b instanceof Heart){
            takeHeart();
            b.takenBy(this);
        }
        if(b instanceof BombNumberDec){
            takeBombNumberDec();
            b.takenBy(this);
        }
        if(b instanceof BombNumberInc){
            takeBombNumberInc();
            b.takenBy(this);
        }
        if(b instanceof BombRangeDec){
            takeBombRangerDec();
            b.takenBy(this);
        }
        if(b instanceof BombRangeInc){
            takeBombRangerInc();
            b.takenBy(this);
        }
        if(b instanceof Princess) {
            takePrincess();
            b.takenBy(this);
        }

    }


    @Override
    public boolean isWalkable(Player player) {
        return false;
    }

    @Override
    public void explode() {
    }

    public void pushBox (Box box, Input input){
        Position boxPosition = box.getPosition();
        Direction playerDirection = getDirection();
        Position playerNextPosition = playerDirection.nextPosition(getPosition());

        if (input.isMoveUp() && box.canMove(Direction.UP)){
            // appel à la fonction move de BOX
            box.doMove(Direction.UP);
        }
        if (input.isMoveDown() && box.canMove(Direction.DOWN)){
            box.doMove(Direction.UP);
        }
        if (input.isMoveLeft() && box.canMove(Direction.LEFT)){
            box.doMove(Direction.UP);
        }
        if (input.isMoveRight() && box.canMove(Direction.RIGHT)){
                box.doMove(Direction.UP);
        }
    }

    // Example of methods to define by the player
    // TODO : Programmer les fonctions de récuperation de bonus
    public void takeDoor(int gotoLevel) {
        if(game.nbKeys > 0){
            Position nextPos = getDirection().nextPosition(getPosition());
            if((game.getGrid().get(nextPos)) instanceof Door) {
                Door d = (Door) game.getGrid().get(nextPos);
                System.out.println(d.getState());
                d.takenBy(this,1);
                System.out.println(d.getState());
            }
        }
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
