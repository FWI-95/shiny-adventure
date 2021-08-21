package com.fwi95.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {

	final ShinyAdventure game;

	OrthographicCamera camera;

    Music backgroundMusic;

	public MainMenuScreen(final ShinyAdventure game) {
		this.game = game;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1920, 1080);

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sfx/music/Menu_-_Lost_In_Time.ogg"));
		backgroundMusic.setLooping(true);
	}


	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		game.font.draw(game.batch, "Welcome to Drop!!! ", 100, 150);
		game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
		game.batch.end();

		if (Gdx.input.isTouched()) {
            backgroundMusic.stop();
			game.setScreen(new GameScreen(game));
			dispose();
		}
	}


    @Override
    public void show() {
        // TODO Auto-generated method stub
        backgroundMusic.play();
    }


    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void pause() {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void resume() {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void hide() {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }

}