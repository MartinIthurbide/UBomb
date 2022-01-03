/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubomb.go.character;

import fr.ubx.poo.ubomb.engine.GameEngine;
import fr.ubx.poo.ubomb.game.*;
import fr.ubx.poo.ubomb.go.GameObject;

import fr.ubx.poo.ubomb.go.decor.Box;
import fr.ubx.poo.ubomb.go.decor.Decor;
import fr.ubx.poo.ubomb.go.decor.Door;
import fr.ubx.poo.ubomb.go.decor.bonus.*;
import javafx.geometry.Pos;

import java.io.IOException;


public class Player extends Character {

    public final int CONSTINV = 100;
    private int cptInvincibility;
    private boolean moveRequested = false;
    public boolean blockDoor = false;



    public Player(Game game, Position position, int lives) {

        super(game, position,lives);
        cptInvincibility = CONSTINV;
    }



    public void requestMove(Direction direction) {
        if (direction != getDirection()) {
            setDirection(direction);
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
        boolean can = false;
        Position nextPos = direction.nextPosition(getPosition());
        if (game.inside(nextPos)) {
            can = true;
            if (game.getGrid().get(nextPos) instanceof Box)
                return true;
            if (game.getGrid().get(nextPos) != null) {
                can = game.getGrid().get(nextPos).isWalkable(this);
            }
        }
        return can;
    }


    public void update(long now) {
        if (moveRequested) {
            blockDoor = false;
            if (canMove(getDirection())) {
                doMove(getDirection());
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

        if ((d = game.getGrid().get(nextPos)) instanceof Box){
            Box b = (Box) d;
            if (boxMove(b.getPosition())){
                b.doMove(direction);
            }
        }
        setPosition(nextPos);
    }

    public boolean boxMove (Position box){
        Position nextPos = direction.nextPosition(box);

        if (!game.inside(nextPos))
            return false;

        if(game.getGrid().get(nextPos) instanceof Decor)
            return false;

        return true;
    }

    @Override
    public boolean isWalkable(Player player) {
        return false;
    }

    // Example of methods to define by the player
    public boolean takeDoor(int gotoLevel) {
        Position nextPos = getDirection().nextPosition(getPosition());
            if((game.getGrid().get(nextPos)) instanceof Door) {
                Door d = (Door) game.getGrid().get(nextPos);
                //System.out.println(d.getState());
                d.open();
                //System.out.println(d.getState());
                return true;
            }
            return false;
    }

    public void goToNextLevel (int level, Door door) throws IOException {
        // change le monde
        if(!blockDoor) {
            System.out.println("Changement de monde");
            game.changeLevel(level, door);
        }

    }

    public void playerCollision(GameObject g){
        if (getPosition().equals(g.getPosition())) {
            if (!isInvincible()){
                System.out.println("Damage taken\n");
                takeDamage();
                setInvincibility(true);
            }
        }

    }


    public void reinitCpt(int cpt) {
        cptInvincibility = cpt;
    }

    public void updateInvincibility() {
        cptInvincibility --;
        if (cptInvincibility <= 0){
            setInvincibility(false);
            reinitCpt(CONSTINV);
        }
    }


    public void takeKey() {
        game.nbKeys++;
    }
    public void takeHeart() {
        setLives(getLives()+1);
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
