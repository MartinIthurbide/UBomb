package fr.ubx.poo.ubomb.game;
import fr.ubx.poo.ubomb.game.EntityCode;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class GridRepoFile extends GridRepo{
    public GridRepoFile(Game game) {
        super(game);
    }

    char EOL = '\n';

    public Grid load(int level, String name) throws IOException {
        FileReader in = new FileReader("src/main/resources/sample/"+name);
        char buffer[] = new char[4096];
        StringBuilder s = new StringBuilder();
        int n;
        while((n = in.read(buffer)) > 0) {
            s.append(buffer,0,n);
        }
        System.out.println(s);

        int  width = 0, height = 0;

        // Calcul width
        while(EOL != s.charAt(width)) {
            width++;
        }

        // Calcul height
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == EOL)
                height++;
        }

        // Log
        //System.out.println("Width : " + width + "\nHeight : " + height);

        int cpt = 0;

        // Creating grid
        Grid g = new Grid(width,height);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                //int spot = i + j * (width+1);
                Position position = new Position(i, j);
                EntityCode e = EntityCode.fromCode(s.charAt(cpt));
                g.set(position,processEntityCode(e,position));
                cpt++;
                //System.out.println("index string : " + spot);
            }
            cpt++;
        }
        return g;
    }
}
