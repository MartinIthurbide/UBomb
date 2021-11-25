package fr.ubx.poo.ubomb.view;

import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.decor.Stone;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import static fr.ubx.poo.ubomb.view.ImageResource.BOMB_0;

public class SpriteBomb extends Sprite{

    public SpriteBomb(Pane layer, Image image, GameObject gameObject) {
        super(layer, image, gameObject);
    }

    public static Sprite create(Pane layer, GameObject gameObject) {
        if (gameObject instanceof Stone)
            return new Sprite(layer, BOMB_0.getImage(), gameObject);
        throw new RuntimeException("Unsupported sprite for bomb " + gameObject);
    }
}
