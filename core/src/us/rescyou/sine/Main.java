package us.rescyou.sine;

import us.rescyou.sine.Level.Level;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Main extends ApplicationAdapter implements InputProcessor {

	// Configuration
	public static final int VIRTUAL_WIDTH = 900;
	public static final int VIRTUAL_HEIGHT = 1600;
	public static final int TICKS_PER_SECOND = 60;
	public static final int STARTING_PERIOD = TICKS_PER_SECOND;
	public static final float STARTING_AMPLITUDE = VIRTUAL_WIDTH / 3;
	public static final int MINIMUM_COIN_SPAWN_X = 10;
	public static final int MAXIMUM_COIN_SPAWN_X = 400;

	// Private Variables
	private SpriteBatch batch;
	private long lastTime;
	private int timer;
	private Level level;

	// Camera Variables
	private OrthographicCamera camera;
	private Viewport viewport;

	// Initialization
	@Override
	public void create() {
		batch = new SpriteBatch();
		
		loadAssets();

		lastTime = System.currentTimeMillis();
		timer = 0;
		level = new Level(this);

		camera = new OrthographicCamera();
		camera.position.set(VIRTUAL_WIDTH / 2, VIRTUAL_HEIGHT / 2, 0);
		viewport = new StretchViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, camera);
		viewport.apply();

		Gdx.input.setInputProcessor(this);
	}

	// Updating
	@Override
	public void render() {
		// Clearing the screen
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();

		// Update limiting
		long currentTime = System.currentTimeMillis();
		long deltaTime = currentTime - lastTime;
		timer += deltaTime;

		int secondsPerMillisecond = 1000;
		int millisPerUpdate = secondsPerMillisecond / TICKS_PER_SECOND;

		if (timer >= millisPerUpdate) {
			// Update the camera
			batch.setProjectionMatrix(camera.combined);

			update();
			draw(batch);

			timer -= millisPerUpdate;
		}

		lastTime = currentTime;
	}

	private void update() {
		level.update();
	}

	private void draw(SpriteBatch batch) {
		batch.begin();
		level.draw(batch);
		batch.end();
	}

	// Miscellaneous Methods
	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		camera.position.set(VIRTUAL_WIDTH / 2, VIRTUAL_HEIGHT / 2, 0);
	}

	private void loadAssets() {
		Assets.background = new Texture(Gdx.files.internal("background.png"));
		Assets.player = new Texture(Gdx.files.internal("player.png"));
		Assets.coin = new Texture(Gdx.files.internal("coin.png"));
		Assets.divider = new Texture(Gdx.files.internal("divider.png"));
		Assets.marker = new Texture(Gdx.files.internal("marker.png"));
	}
	
	// Input
	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Input.Keys.LEFT:
			camera.translate(-1f, 0f);
			break;
		case Input.Keys.RIGHT:
			camera.translate(1f, 0f);
			break;
		case Input.Keys.UP:
			camera.translate(0f, -1f);
			break;
		case Input.Keys.DOWN:
			camera.translate(0f, 1f);
			break;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
