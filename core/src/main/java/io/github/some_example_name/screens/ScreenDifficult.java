package io.github.some_example_name.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;

import io.github.some_example_name.Main;
import io.github.some_example_name.components.Buttons.DifficultButtons.DifficultEasy;
import io.github.some_example_name.components.Buttons.DifficultButtons.DifficultHard;
import io.github.some_example_name.components.Buttons.DifficultButtons.DifficultMedium;
import io.github.some_example_name.components.Buttons.ExitScreenButton;
import io.github.some_example_name.components.Buttons.SkinsButton.SkinV0Button;
import io.github.some_example_name.components.Buttons.SkinsButton.SkinV1Button;
import io.github.some_example_name.components.Buttons.SkinsButton.SkinV2Button;
import io.github.some_example_name.components.MovingBackground;

public class ScreenDifficult implements Screen {
    Main main;

    MovingBackground background;
    ExitScreenButton exitScreenButton;
    DifficultEasy difficultEasy;
    DifficultMedium difficultMedium;
    DifficultHard difficultHard;

    public static int diff = 0;
    public ScreenDifficult(Main main){
        this.main = main;
        background = new MovingBackground("fons/restart_bg.png");
        exitScreenButton = new ExitScreenButton(100,100, "Leave");
        difficultEasy = new DifficultEasy(600,100, "Easy");
        difficultMedium = new DifficultMedium(600,300, "Medium");
        difficultHard = new DifficultHard(600,500, "Hard");
    }

    public void show() {

    }

    public void render(float delta) {
        main.camera.update();
        main.batch.setProjectionMatrix(main.camera.combined);
        main.batch.begin();
        if (Gdx.input.justTouched()) {
            Vector3 touch = main.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (exitScreenButton.isHit((int) touch.x, (int) touch.y)){
                main.setScreen(main.screenMenu);
            }
            if (difficultEasy.isHit((int) touch.x, (int) touch.y)) {
                diff = 0;
            }
            if (difficultMedium.isHit((int) touch.x, (int) touch.y)) {
                diff = 1;
            }
            if (difficultHard.isHit((int) touch.x, (int) touch.y)) {
                diff = 2;
            }
        }
        background.onDraw(main.batch);
        exitScreenButton.draw(main.batch);
        difficultEasy.draw(main.batch);
        difficultMedium.draw(main.batch);
        difficultHard.draw(main.batch);
        main.batch.end();
    }
    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        exitScreenButton.dispose();
        difficultEasy.dispose();
        difficultMedium.dispose();
        difficultHard.dispose();
    }
}
