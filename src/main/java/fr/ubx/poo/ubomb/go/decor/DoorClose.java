package fr.ubx.poo.ubomb.go.decor;

import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Player;

public class DoorClose extends Door{
    public DoorClose(Game game, Position position) {
        super(game, position);
    }

    public DoorClose(Position position) {
        super(position);
    }

    @Override
    public boolean doorState() {
        return false; // false == La porte est fermée
    }
    public boolean isWalkable(Player player) {
        return false;
    }
}
