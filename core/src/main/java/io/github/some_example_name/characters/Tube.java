package io.github.some_example_name.characters;

import static io.github.some_example_name.Main.SCR_HEIGH;
import static io.github.some_example_name.Main.SCR_WEIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.Random;

import io.github.some_example_name.screens.ScreenDifficult;

public class Tube {
    Texture textureUpperTube;
    Texture textureDownTube;
    int gapY;
    int gapHeight = 400;
    int padding = 100;
    int x;
    int widht = 200;
    int height = 700;
    Random random;
    int distanseBetweenTubes;
    public int speed;
    boolean isPointReceived;
    int num;
    public  Tube(int tubeCount, int tubeIdx) {
        random = new Random();

        gapY = gapHeight / 2 + padding + random.nextInt(SCR_HEIGH - 2 * (padding + gapHeight / 2));
        distanseBetweenTubes = (SCR_WEIDTH + widht) / (tubeCount - 1);
        x = distanseBetweenTubes * tubeIdx + SCR_WEIDTH;

        textureUpperTube = new Texture("tubes/tube_flipped.png");
        textureDownTube = new Texture("tubes/tube.png");
        isPointReceived = false;
    }
    public void move(){
        if (ScreenDifficult.diff == 0){
            speed = 10;
        }else if (ScreenDifficult.diff == 1){
            speed = 20;
        }else if (ScreenDifficult.diff == 2){
            speed = 30;
        }
        num++;
        if (num>100){
            num = 0;
            speed++;
        }
        x-=speed;
        if (x < -widht){
            isPointReceived = false;
            x = SCR_WEIDTH + distanseBetweenTubes;
            gapY = gapHeight / 2 + padding + random.nextInt(SCR_HEIGH - 2 * (padding + gapHeight / 2));
        }
    }
    public void draw(Batch batch) {
        batch.draw(textureUpperTube, x, gapY + gapHeight / 2, widht, height);
        batch.draw(textureDownTube, x, gapY - gapHeight / 2 - height, widht, height);
    }
    public boolean isHint(Bird bird){
        if (bird.y <= gapY - gapHeight /2 && bird.x + bird.width >= x && bird.x <= x+widht){
            return true;
        }
        if (bird.y + bird.height >= gapY + gapHeight / 2 && bird.x + bird.width >= x && bird.x <= widht){
            return true;
        }
        return false;
    }
    public boolean needAddPoint(Bird bird){

        return bird.x > x + widht && !isPointReceived;
    }
    public  void  setPointReceived(){
        isPointReceived=true;
    }
    public void dispose() {
        textureDownTube.dispose();
        textureUpperTube.dispose();
    }
}
