package io.github.some_example_name.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

import static io.github.some_example_name.Main.SCR_HEIGH;
import static io.github.some_example_name.Main.SCR_WEIDTH;

import io.github.some_example_name.Main;
import io.github.some_example_name.characters.Bird;
import io.github.some_example_name.characters.Tube;
import io.github.some_example_name.components.MovingBackground;
import io.github.some_example_name.components.PointCounter;

public class ScreenGame implements Screen {
    Texture birdTexture;
    Bird bird;
    Main main;
    int birdX=20,birdY=0;
    int birdSpeed=5;
    Tube[] tubes;
    int tubeCount = 3;
    public static boolean isGameOver;
    static int gamePoints;
    int skinn;
    PointCounter pointCounter;
    MovingBackground movingBackground;
    ScreenMenu screenMenu;
    ScreenRestart screenRestart;
    public void initTubes() {
        tubes = new Tube[tubeCount];
        for (int i = 0; i < tubeCount; i++) {
            tubes[i] = new Tube(tubeCount, i);
        }
    }
    public ScreenGame(Main main){
        this.main = main;
        //birdTexture = new Texture("bird0.png");
        bird = new Bird(20,0,10,200, 150);
        skinn = ScreenSkin.skin;
        initTubes();
        final int pointCounterMarginTop = 60;
        final int pointCounterMarginRight = 400;
        pointCounter = new PointCounter(SCR_WEIDTH - pointCounterMarginRight, SCR_HEIGH - pointCounterMarginTop);
        movingBackground = new MovingBackground("fons/game_bg.png");
    }

    @Override
    public void show(){
        isGameOver = false;
        gamePoints = 0;
        bird.setY(SCR_HEIGH / 2);
        initTubes();
    }

    @Override
    public void render(float delta){
        if (ScreenSkin.skin!=skinn){
            bird = new Bird(20,0,10,200, 150);
            skinn = ScreenSkin.skin;
        }
        if (Gdx.input.justTouched()){
            bird.onClick();
        }
        movingBackground.move();
        bird.fly();
        if (!bird.isInField()){
            System.out.println("not in field");
            isGameOver = true;
        }

        for (Tube tube : tubes){
            if (tube.isHint(bird)){
                System.out.println("hit");
                isGameOver = true;
            } else if (tube.needAddPoint(bird)){
                gamePoints++;
                tube.setPointReceived();
                System.out.println(gamePoints);
            }
        }
        if (isGameOver){
            ScreenRestart.gamePoints = gamePoints;
            main.setScreen(main.screenRestart);
        }
        main.camera.update();


        ScreenUtils.clear(1, 0, 0, 1);
        main.camera.update();
        main.batch.setProjectionMatrix(main.camera.combined);
        main.batch.begin();
        movingBackground.onDraw(main.batch);
        bird.draw(main.batch);
        for (Tube tube : tubes) tube.move();
        for (Tube tube : tubes) tube.draw(main.batch);
        pointCounter.draw(main.batch, gamePoints);
        main.batch.end();

    }


    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        bird.dispose();
    }
}
