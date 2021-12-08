package fr.ubx.poo.ubomb.go;

import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Player;
import fr.ubx.poo.ubomb.go.decor.Stone;
import fr.ubx.poo.ubomb.go.decor.Tree;
import fr.ubx.poo.ubomb.go.decor.bonus.Bonus;
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
                // faire passer l'explosion en tant que décor pour l'explosion
                explode();
            }
        }


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
        System.out.println("Explosion\n");
        exploded = true;
        //todo
        Position posBomb = getPosition(); // definition de la position suivante de la bombe
        Direction[] direction = Direction.values();
        // todo : gérer les cas pour la range
        for( int i = 0; i < direction.length ; i++){
            Position nextPos =  direction[i].nextPosition(posBomb);
            if ( game.getGrid().get(nextPos) == null || (game.getGrid().get(nextPos) instanceof Bonus)){ // Door et Box pas prise en compte
                //todo
                // exploser jusqu'à la rang

            }

       }

    }
}
