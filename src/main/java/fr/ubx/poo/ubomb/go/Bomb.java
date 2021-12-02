package fr.ubx.poo.ubomb.go;

import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Player;

public class Bomb extends GameObject{

    private int range;
    private int cptBomb;
    private int etatBomb;

    public Bomb(Game game, Position position) {
        super(game, position);
        this.etatBomb = 3;
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
            reinitCpt(15);
            etatBomb--;
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
}
