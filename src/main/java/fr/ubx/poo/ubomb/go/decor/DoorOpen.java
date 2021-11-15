package fr.ubx.poo.ubomb.go.decor;

import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;

public class DoorOpen extends Door{

    public DoorOpen(Game game, Position position) {
        super(game, position);
    }

    public DoorOpen(Position position) {
        super(position);
    }

    @Override
    public boolean doorState() {
        return true; // true == La porte est ouverte
    }
}
