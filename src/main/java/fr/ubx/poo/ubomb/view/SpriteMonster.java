package fr.ubx.poo.ubomb.view;

import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.decor.DecorMonster;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import static fr.ubx.poo.ubomb.view.ImageResource.*;

public final class SpriteMonster extends Sprite {

    public SpriteMonster(Pane layer, Image image, GameObject gameObject) {
        super(layer, image, gameObject);
    }

    public static Sprite create(Pane layer, GameObject gameObject) {
        if (gameObject instanceof DecorMonster)
            return new Sprite(layer, MONSTER_DOWN.getImage(), gameObject);
        throw new RuntimeException("Unsupported sprite for decor " + gameObject);
    }

    public void updateImage() {
    }
}
