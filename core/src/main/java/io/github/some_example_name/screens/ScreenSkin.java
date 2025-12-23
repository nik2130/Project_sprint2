package io.github.some_example_name.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;

import io.github.some_example_name.Main;
import io.github.some_example_name.components.Buttons.ExitScreenButton;
import io.github.some_example_name.components.Buttons.MainTextButton;
import io.github.some_example_name.components.Buttons.SkinsButton.SkinV0Button;
import io.github.some_example_name.components.Buttons.SkinsButton.SkinV1Button;
import io.github.some_example_name.components.Buttons.SkinsButton.SkinV2Button;
import io.github.some_example_name.components.MovingBackground;
import io.github.some_example_name.components.Buttons.SkinsButton.SkinButton;

public class ScreenSkin implements Screen {
    Main main;

    MovingBackground background;
    ExitScreenButton exitScreenButton;
    SkinV0Button skinV0Button;
    SkinV1Button skinV1Button;
    SkinV2Button skinV2Button;
    public static int skin = 0;
    public ScreenSkin(Main main){
        this.main = main;
        background = new MovingBackground("fons/restart_bg.png");
        exitScreenButton = new ExitScreenButton(100,100, "Leave");
        skinV0Button = new SkinV0Button(600,100, "Skin №1");
        skinV1Button = new SkinV1Button(600,300, "Skin №2");
        skinV2Button = new SkinV2Button(600,500, "Skin №3");
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
            if (skinV0Button.isHit((int) touch.x, (int) touch.y)) {
                skin = 0;
            }
            if (skinV1Button.isHit((int) touch.x, (int) touch.y)) {
                skin = 1;
            }
            if (skinV2Button.isHit((int) touch.x, (int) touch.y)) {
                skin = 2;
            }
        }
        background.onDraw(main.batch);
        exitScreenButton.draw(main.batch);
        skinV1Button.draw(main.batch);
        skinV2Button.draw(main.batch);
        skinV0Button.draw(main.batch);
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
        skinV0Button.dispose();
        skinV1Button.dispose();
        skinV2Button.dispose();
    }
}
