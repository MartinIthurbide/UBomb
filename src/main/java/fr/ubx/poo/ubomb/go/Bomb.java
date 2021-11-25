package fr.ubx.poo.ubomb.go;

import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Player;

public class Bomb extends GameObject{
    public Bomb(Game game, Position position) {
        super(game, position);
    }

    public Bomb(Position position) {
        super(position);
    }

    @Override
    public boolean isWalkable(Player player) {
        return true;
    }
}
