package fr.ubx.poo.ubomb.go;

import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Player;
import fr.ubx.poo.ubomb.go.decor.Box;
import fr.ubx.poo.ubomb.go.decor.Explosion;
import fr.ubx.poo.ubomb.go.decor.bonus.Bonus;

import java.util.ArrayList;

public class Bomb extends GameObject{

    private final int CONST = 50;
    private final int CONSTEXP = 30;
    private int range;
    private int cptBomb;
    private int cptExplode;
    private int etatBomb;
    private boolean exploded;
    public Bomb(Game game, Position position) {
        super(game, position);
        this.etatBomb = 3;
        cptBomb = CONST;
        cptExplode = CONSTEXP;
        exploded = false;
        range = game.bombRange;
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
        if(isExploded() != true) {
            cptBomb--;
            if (cptBomb <= 0) {
                setModified(true);
                reinitCpt(CONST); // delai entre chaque changement d'etat
                etatBomb--;
                System.out.println("etat bombe : "+etatBomb+"\n");
            }
        }

    }

    public void clearExplosions(ArrayList<Explosion> explosions){
        cptExplode --;
        if (cptExplode <= 0){
            for (int i = 0; i < explosions.size();i++){
                explosions.get(i).remove();
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

    public ArrayList<Explosion> explosion() {
        System.out.println("Explosion\n");
        exploded = true;
        int range = getRange();
        Position posBomb = getPosition(); // definition de la position suivante de la bombe
        Direction[] direction = Direction.values();

        ArrayList<Explosion> explosions = new ArrayList<Explosion>();

        explosions.add(new Explosion(getPosition()));
        for( int i = 0; i < direction.length; i++){
            Position nextPos =  posBomb;


            for (int r = 1; r <= range; r++){
                nextPos = direction[i].nextPosition(nextPos);
                GameObject nextObj = game.getGrid().get(nextPos);

                if(game.inside(nextPos)){
                    if (nextObj == null || nextObj instanceof Bonus || nextObj instanceof Box){ // Door et Box pas prise en compte
                        if (nextObj != null)
                            game.getGrid().get(nextPos).remove();
                        System.out.println("Boom à : " + nextPos +"\n");
                        explosions.add(new Explosion(nextPos));
                    }
                }
            }
       }
        return explosions;
    }
}
