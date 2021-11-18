package fr.ubx.poo.ubomb.go.decor;

import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;

public class DecorMonster extends Decor {
    public DecorMonster(Game game, Position position) {
        super(game, position);
    }

    public DecorMonster(Position position) {
        super(position);
    }
}
