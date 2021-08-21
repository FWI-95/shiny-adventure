package com.fwi95.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class PauseMenuScreen implements Screen {

	final ShinyAdventure game;
    final GameScreen gamescreen;

	OrthographicCamera camera;

	public PauseMenuScreen(final ShinyAdventure game, GameScreen gamescreen) {
		this.game = game;
        this.gamescreen = gamescreen;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
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
