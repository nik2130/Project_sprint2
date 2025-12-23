package io.github.some_example_name;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.some_example_name.screens.ScreenDifficult;
import io.github.some_example_name.screens.ScreenGame;
import io.github.some_example_name.screens.ScreenMenu;
import io.github.some_example_name.screens.ScreenRestart;
import io.github.some_example_name.screens.ScreenSkin;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public static final int SCR_WEIDTH=1280;
    public static final int SCR_HEIGH=720;
    public OrthographicCamera camera;
    public SpriteBatch batch;
    private Texture birdTexture;
    int birdX=0,birdY=0;
    int birdSpeed=5;
    public ScreenRestart screenRestart;
    public ScreenGame screenGame;
    public ScreenMenu screenMenu;

    public ScreenSkin screenSkin;
    public ScreenDifficult screenDifficult;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false,SCR_WEIDTH,SCR_HEIGH);
        batch = new SpriteBatch();
//        image = new Texture("bird0.png");

        screenGame = new ScreenGame(this);
        screenRestart = new ScreenRestart(this);
        screenMenu = new ScreenMenu(this);
        screenSkin = new ScreenSkin(this);
        screenDifficult = new ScreenDifficult(this);
        setScreen(screenMenu);
    }


    @Override
    public void dispose() {
        batch.dispose();
        //birdTexture.dispose();
    }
}
