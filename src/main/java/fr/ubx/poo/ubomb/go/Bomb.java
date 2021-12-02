package fr.ubx.poo.ubomb.go;

import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Player;
import fr.ubx.poo.ubomb.view.SpriteBomb;

import java.util.Map;

public class Bomb extends GameObject{

    private final int CONST = 50;
    private int range;
    private int cptBomb;
    private int etatBomb;
    private boolean exploded;
    public Bomb(Game game, Position position) {
        super(game, position);
        this.etatBomb = 3;
        cptBomb = CONST;
        exploded = false;
    }

    public Bomb(Position position) {
        super(position);
    }

    @Override
    public boolean isWalkable(Player player) {
        return true;
    }

    private void reinitCpt(int cpt) {
        cptBomb = cpt;
    }

    public void update() {
        cptBomb--;
        System.out.println("cpt bombe : "+cptBomb+"\n");
        if (cptBomb <= 0) {
            setModified(true);
            reinitCpt(CONST);
            etatBomb--;

            if(exploded) {
                //todo
            }
        }


    }

    private void boom() {
        remove();
        explode();
    }

    public int getRange() {
        return range;
    }

    public int getEtatBomb() {
        return etatBomb;
    }

    @Override
    public void remove() {
        super.remove();
    }

    @Override
    public void explode() {
        super.explode();
        exploded = true;

    }
}
