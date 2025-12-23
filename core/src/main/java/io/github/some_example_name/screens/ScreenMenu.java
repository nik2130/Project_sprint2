package io.github.some_example_name.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;

import io.github.some_example_name.Main;
import io.github.some_example_name.components.Buttons.DifficultButtons.DifficultChange;
import io.github.some_example_name.components.Buttons.ExitTextButton;
import io.github.some_example_name.components.Buttons.MainTextButton;
import io.github.some_example_name.components.MovingBackground;
import io.github.some_example_name.components.Buttons.SkinsButton.SkinButton;

public class ScreenMenu implements Screen {
    Main main;
    MainTextButton mainTextButton;

    MovingBackground background;
    ExitTextButton exitTextButton;
    SkinButton skinButton;
    DifficultChange difficultChange;
    ScreenDifficult screenDifficult;

    public ScreenMenu(Main main){
        this.main = main;
        background = new MovingBackground("fons/restart_bg.png");
        mainTextButton = new MainTextButton(200,400, "Play");
        exitTextButton = new ExitTextButton(200,100, "Leave");
        skinButton = new SkinButton(700,400, "Skins");
        difficultChange = new DifficultChange(700,100, "Difficult");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        main.camera.update();
        main.batch.setProjectionMatrix(main.camera.combined);
        main.batch.begin();
        if (Gdx.input.justTouched()) {
            Vector3 touch = main.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (mainTextButton.isHit((int) touch.x, (int) touch.y)) {
                main.setScreen(main.screenGame);
            }
            if (exitTextButton.isHit((int) touch.x, (int) touch.y)) {
                Gdx.app.exit();
            }
            if (skinButton.isHit((int) touch.x, (int) touch.y)) {
                main.setScreen(main.screenSkin);
            }
            if (difficultChange.isHit((int) touch.x, (int) touch.y)) {
                main.setScreen(main.screenDifficult);
            }
        }
        background.onDraw(main.batch);
        mainTextButton.draw(main.batch);
        exitTextButton.draw(main.batch);
        skinButton.draw(main.batch);
        difficultChange.draw(main.batch);
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
        mainTextButton.dispose();
    }
}
