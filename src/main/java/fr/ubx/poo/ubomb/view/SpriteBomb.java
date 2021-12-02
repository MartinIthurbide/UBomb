package fr.ubx.poo.ubomb.view;

import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.go.Bomb;
import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.decor.Stone;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import static fr.ubx.poo.ubomb.view.ImageResource.BOMB_0;

public class SpriteBomb extends Sprite{

    public SpriteBomb(Pane layer, Image image, GameObject gameObject) {
        super(layer, image, gameObject);
    }

    @Override
    public void updateImage() {
        Bomb b = (Bomb) getGameObject();
        Image image = getImage(b.getEtatBomb());
        setImage(image);

    }
    public Image getImage(int state) {
        return ImageResource.getBomb(state);
    }
}
