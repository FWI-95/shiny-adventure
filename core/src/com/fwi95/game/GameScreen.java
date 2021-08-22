package com.fwi95.game;


import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen {
	final ShinyAdventure game;

	Sound shootSound;
	Music backgroundMusic;
	OrthographicCamera camera;
	// Rectangle bucket;
	Array<Rectangle> enemies;
	long lastSpawnTime;
	int enemiesKilled;

	TiledMap tiledMap;
	TiledMapRenderer tiledMapRenderer;

	public GameScreen(final ShinyAdventure game) {
		this.game = game;

		// load the images for the droplet and the bucket, 64x64 pixels each
		dropImage = new Texture(Gdx.files.internal("droplet.png"));
		bucketImage = new Texture(Gdx.files.internal("bucket.png"));

		// background music
		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sfx/music/Action_-_Keep_Moving.ogg"));
		backgroundMusic.setLooping(true);

		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1920, 1080);

		// create a Rectangle to logically represent the bucket
		bucket = new Rectangle();
		bucket.x = 800 / 2 - 64 / 2; // center the bucket horizontally
		bucket.y = 20; // bottom left corner of the bucket is 20 pixels above
						// the bottom screen edge
		bucket.width = 64;
		bucket.height = 64;

		// create the raindrops array and spawn the first raindrop
		enemies = new Array<Rectangle>();
		spawnEnemy();

		tiledMap = new TmxMapLoader().load("maps/Default.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

	}

	private void spawnEnemy() {
		Rectangle raindrop = new Rectangle();
		raindrop.x = MathUtils.random(0, 800 - 64);
		raindrop.y = 480;
		raindrop.width = 64;
		raindrop.height = 64;
		enemies.add(raindrop);
		lastSpawnTime = TimeUtils.nanoTime();
	}

	@Override
	public void render(float delta) {	
		// clear the screen with a dark blue color. The
		// arguments to clear are the red, green
		// blue and alpha component in the range [0,1]
		// of the color to be used to clear the screen.
		ScreenUtils.clear(0, 0, 0.2f, 1);

		// tell the camera to update its matrices.
		camera.update();

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		game.batch.setProjectionMatrix(camera.combined);

		// begin a new batch and draw the bucket and
		// all drops
		game.batch.begin();
		game.font.draw(game.batch, "Drops Collected: " + enemiesKilled, 0, 480);
		game.batch.draw(bucketImage, bucket.x, bucket.y, bucket.width, bucket.height);
		for (Rectangle raindrop : enemies) {
			game.batch.draw(dropImage, raindrop.x, raindrop.y);
		}
		game.batch.end();

		// process user input
		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			bucket.x = touchPos.x - 64 / 2;
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			bucket.x -= 200 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			bucket.x += 200 * Gdx.graphics.getDeltaTime();

		// make sure the bucket stays within the screen bounds
		if (bucket.x < 0)
			bucket.x = 0;
		if (bucket.x > 800 - 64)
			bucket.x = 800 - 64;

		// check if we need to create a new raindrop
		if (TimeUtils.nanoTime() - lastSpawnTime > 1000000000)
			spawnEnemy();

		// move the raindrops, remove any that are beneath the bottom edge of
		// the screen or that hit the bucket. In the later case we increase the
		// value our drops counter and add a sound effect.
		Iterator<Rectangle> iter = enemies.iterator();
		while (iter.hasNext()) {
			Rectangle raindrop = iter.next();
			raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
			if (raindrop.y + 64 < 0)
				iter.remove();
			if (raindrop.overlaps(bucket)) {
				enemiesKilled++;
				shootSound.play();
				iter.remove();
			}
		}

		//check for escape
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			backgroundMusic.pause();
			game.setScreen(new PauseMenuScreen(game, this));
		}

		tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			camera.translate(-16,0);
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			camera.translate(16,0);
		}
		if(Gdx.input.isKeyPressed(Keys.UP)){
			camera.translate(0,16);
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)){
			camera.translate(0,-16);
		}
		if(Gdx.input.isKeyPressed(Keys.NUM_1)){
			tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
		}
		if(Gdx.input.isKeyJustPressed(Keys.NUM_2)){
			tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		// start the playback of the background music
		// when the screen is shown
		backgroundMusic.play();
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		dropImage.dispose();
		bucketImage.dispose();
		shootSound.dispose();
		backgroundMusic.dispose();
	}
}