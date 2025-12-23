package io.github.some_example_name.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.some_example_name.Main;

public class MovingBackground {
    Texture texture;
    int speed = 2;
    int texture1X, texture2X;
    public MovingBackground(String pathToTexture){
        texture = new Texture(pathToTexture);
        texture1X = 0;
        texture2X = Main.SCR_WEIDTH;
    }

    public void onDraw(Batch batch){
        batch.draw(texture, texture1X, 0, Main.SCR_WEIDTH, Main.SCR_HEIGH);
        batch.draw(texture, texture2X, 0, Main.SCR_WEIDTH, Main.SCR_HEIGH);
    }

    public void move(){
        texture1X -= speed;
        texture2X -= speed;
        if (texture1X <= -Main.SCR_WEIDTH){
            texture1X = Main.SCR_WEIDTH;
        }
        if (texture2X<= -Main.SCR_WEIDTH){
            texture2X = Main.SCR_WEIDTH;
        }
    }

}
