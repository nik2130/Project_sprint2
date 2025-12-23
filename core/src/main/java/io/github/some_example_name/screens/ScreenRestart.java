package io.github.some_example_name.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;

import io.github.some_example_name.Main;
import io.github.some_example_name.components.Buttons.ExitRestartButton;
import io.github.some_example_name.components.MovingBackground;
import io.github.some_example_name.components.PointCounter;
import io.github.some_example_name.components.Buttons.TextButton;

public class ScreenRestart implements Screen {
    Main main;
    MovingBackground background;

    TextButton buttonRestart;
    PointCounter pointCounter;
    ExitRestartButton exitButton;
    public static int gamePoints;
    public ScreenRestart(Main main){
        this.main = main;
        background = new MovingBackground("fons/restart_bg.png");
        buttonRestart = new TextButton(100,400, "Restart");
        exitButton = new ExitRestartButton(100,100, "Leave");
        pointCounter = new PointCounter(750, 530);
    }

    public void show(){

    }

    public void render(float delta){
        main.camera.update();
        main.batch.setProjectionMatrix(main.camera.combined);
        main.batch.begin();

        if (Gdx.input.justTouched()){
            Vector3 touch = main.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (buttonRestart.isHit((int) touch.x, (int) touch.y)){
                main.setScreen(main.screenGame);
            }
            if (exitButton.isHit((int) touch.x, (int) touch.y)){
                main.setScreen(main.screenMenu);
            }

        }


        background.onDraw(main.batch);
        buttonRestart.draw(main.batch);
        exitButton.draw(main.batch);
        pointCounter.draw(main.batch, gamePoints);

        main.batch.end();
    }

    public void resize(int width, int height) {}

    public void pause() {}

    public void resume() {}

    public void hide() {}

    public void dispose() {
        buttonRestart.dispose();
    }
}
