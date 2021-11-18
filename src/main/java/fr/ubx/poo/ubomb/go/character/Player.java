/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubomb.go.character;

import fr.ubx.poo.ubomb.engine.GameEngine;
import fr.ubx.poo.ubomb.engine.StatusBar;
import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.EntityCode;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.GameObject;

import fr.ubx.poo.ubomb.engine.StatusBar;

import fr.ubx.poo.ubomb.go.Movable;
import fr.ubx.poo.ubomb.go.decor.Decor;
import fr.ubx.poo.ubomb.go.decor.Decor.*;
import fr.ubx.poo.ubomb.go.decor.bonus.*;

import javax.swing.text.html.parser.Entity;


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
    public void takeDoor(int gotoLevel) {
        System.out.println("Door pas encore implémenté\n");
    }
    public void takeKey() {
        game.nbKeys++;
    }
    public void takeHeart() {
        game.playerHearts++;
    }
    public void takeBombNumberInc() {
        System.out.println("BombNumberInc pas encore implémenté\n");
    }
    public void takeBombNumberDec() {
        System.out.println("BombNumberDec pas encore implémenté\n");
    }
    public void takeBombRangerInc() {
        System.out.println("BombRangerInc pas encore implémenté\n");
    }
    public void takeBombRangerDec() {
        System.out.println("BombRangerDec pas encore implémenté\n");
    }
    public void takePrincess() {
        System.out.println("Princess pas encore implémenté\n");
    }


    public boolean isWinner() {
        return false;
    }


}
