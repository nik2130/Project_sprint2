package io.github.some_example_name.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import static io.github.some_example_name.Main.SCR_HEIGH;

import io.github.some_example_name.screens.ScreenGame;
import io.github.some_example_name.screens.ScreenSkin;

public class Bird {
    int x, y;
    int speed;
    Texture texture;
    ScreenSkin screenSkin;
    ScreenGame screenGame;
    private  boolean jump = false;
    private float jumpHeight;
    private final float maxHeighJump = 200;
    Texture[] framesArray;
    int frameCount;
    int width, height;
    public Bird(int x, int y, int speed,int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        if (ScreenSkin.skin==0) {
            framesArray = new Texture[]{
                new Texture("birdTiles/bird0.png"),
                new Texture("birdTiles/bird1.png"),
                new Texture("birdTiles/bird2.png"),
            };
        }
        if (ScreenSkin.skin==1) {
            framesArray = new Texture[]{
                new Texture("birdTiles/birdp0.png"),
                new Texture("birdTiles/birdp1.png"),
                new Texture("birdTiles/birdp2.png"),
            };
        }
        if (ScreenSkin.skin==2) {
            framesArray = new Texture[]{
                new Texture("birdTiles/birdg0.png"),
                new Texture("birdTiles/birdg1.png"),
                new Texture("birdTiles/birdg2.png"),
            };
        }
        this.speed = speed;
    }
    public void fly(){
//        frameCount = (frameCount + 1) % (framesArray.length);
        if (screenGame.isGameOver){
            jump = false;
        }
        if (y >= jumpHeight){
            jump = false;
        }
        if (jump){
            y += speed;
        }else{
            y -= speed;
        }

    }
    public void onClick(){
        jump = true;
        jumpHeight = maxHeighJump + y;
    }
    public boolean isInField(){
        if (y+height < 0){
            return false;
        }
        if (y > SCR_HEIGH){
            return  false;
        }
        return true;
    }
    public void draw(Batch batch) {
        //batch.draw(texture,x,y);
        int frameMultipier = 10;
        batch.draw(framesArray[frameCount / frameMultipier], x, y, width, height);
        if (frameCount++ == framesArray.length * frameMultipier - 1){
            frameCount = 0;
        }
    }
    public void dispose(){
        for (Texture texture: framesArray ){
            texture.dispose();
        }

    }

    public void setY(int y) {
        this.y = y;
    }
}
