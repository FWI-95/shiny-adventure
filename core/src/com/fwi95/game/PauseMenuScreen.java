package com.fwi95.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class PauseMenuScreen implements Screen {

	final ShinyAdventure game;
    final GameScreen gamescreen;

	OrthographicCamera camera;

    Music backgroundMusic;

	public PauseMenuScreen(final ShinyAdventure game, GameScreen gamescreen) {
		this.game = game;
        this.gamescreen = gamescreen;

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
        
		game.font.draw(game.batch, "Pause ", 100, 150);
		game.font.draw(game.batch, "Tap anywhere to resume!", 100, 100);
        game.font.draw(game.batch, "Press Escape to end game!", 100, 50);
		game.batch.end();

		if (Gdx.input.isTouched()) {
            backgroundMusic.stop();
			game.setScreen(gamescreen);
			dispose();
		}

        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
            System.exit(0);
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
