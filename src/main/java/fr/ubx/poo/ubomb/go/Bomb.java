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
        if (cptBomb <= 0) {
            setModified(true);
            reinitCpt(CONST); // delai entre chaque changement d'etat
            etatBomb--;
            System.out.println("etat bombe : "+etatBomb+"\n");

            if(isExploded() == true) {
                //todo
                // faire exploser la bombe en appelant le sprite
                boom();
            }
        }


    }

    private void boom() {
        System.out.println("Je suis là !!\n");
    }

    public int getRange() {
        return range;
    }

    public int getEtatBomb() {
        return etatBomb;
    }

    public boolean isExploded(){
        return exploded;
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
