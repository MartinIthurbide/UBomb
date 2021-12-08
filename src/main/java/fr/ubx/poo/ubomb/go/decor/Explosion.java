package fr.ubx.poo.ubomb.go.decor;

import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Player;

public class Explosion extends Decor{

    public Explosion(Game game, Position position) {
        super(game, position);
    }

    public Explosion(Position position) {
        super(position);
    }

    @Override

    public boolean isWalkable(Player player) {
        return true;
    }


}
