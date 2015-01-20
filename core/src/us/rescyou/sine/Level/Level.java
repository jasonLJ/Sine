package us.rescyou.sine.Level;

import java.util.ArrayList;

import us.rescyou.sine.Assets;
import us.rescyou.sine.Main;
import us.rescyou.sine.Entity.Coin;
import us.rescyou.sine.Entity.Player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Level {

	// Configuration
	public static final int PLAYER_Y_POSITION = 300;
	public static final int COIN_GAP_DISTANCE = 720;

	// Private Variables
	private Main main;
	private Player player;
	private ArrayList<Coin> coins;
	private int period;
	private float amplitude;
	private float lastSpawnX;

	// Sprites
	private Sprite background;
	private Sprite divider;
	private Sprite leftMarker;
	private Sprite rightMarker;

	// Initialization
	public Level(Main main) {
		this.main = main;
		player = new Player(this, -1000, PLAYER_Y_POSITION); // Will later be brought in-bounds on update
		coins = new ArrayList<Coin>();
		period = Main.STARTING_PERIOD;
		amplitude = Main.STARTING_AMPLITUDE;
		lastSpawnX = 1;

		divider = new Sprite(Assets.divider);
		divider.setX((Main.VIRTUAL_WIDTH / 2) - (divider.getWidth() / 2));

		background = new Sprite(Assets.background);
		leftMarker = new Sprite(Assets.marker);
		rightMarker = new Sprite(Assets.marker);
	}

	// Updating
	public void update() {
		// Update the markers
		int halfScreenPosition = (int) ((Main.VIRTUAL_WIDTH / 2) - (leftMarker.getWidth() / 2));
		leftMarker.setX(halfScreenPosition - amplitude);
		rightMarker.setX(halfScreenPosition + amplitude);

		// Entity spawning
		if(player.isAtPeak()) {
			spawnCoin();
		}
		
		// Entity updating
		for (Coin coin : coins) {
			coin.update();
		}

		player.update();
	}

	public void draw(SpriteBatch batch) {
		// Background drawing
		background.draw(batch);
		leftMarker.draw(batch);
		rightMarker.draw(batch);
		divider.draw(batch);
		
		// Entity drawing
		for (Coin coin : coins) {
			coin.draw(batch);
		}

		player.draw(batch);
	}

	// Spawning
	private void spawnCoin() {
		coins.add(new Coin(this, ((Main.VIRTUAL_WIDTH / 2) - (Assets.coin.getWidth() / 2)) + getCoinSpawnX(), getCoinSpawnY()));
	}
	
	public int getCoinSpawnY() {
		int y = 0;
		while(y < Main.VIRTUAL_HEIGHT) {
			y += COIN_GAP_DISTANCE;
		}
		return y;
	}
	
	public int getCoinSpawnX() {
		// Int between MIN and MAX spawn X
		int returnX = (int) (Main.MINIMUM_COIN_SPAWN_X + (Math.random() * (Main.MAXIMUM_COIN_SPAWN_X - Main.MINIMUM_COIN_SPAWN_X)));
		
		boolean flipNegative = lastSpawnX > 0;
		if(flipNegative) {
			returnX = -returnX;
		}
		
		lastSpawnX = returnX;
		return returnX;
	}
	
	// Getters
	public int getPeriod() {
		return period;
	}

	public float getAmplitude() {
		return amplitude;
	}

	
	// Setters
	public void setPeriod(int period) {
		this.period = period;
	}

	public void setAmplitude(float amplitude) {
		this.amplitude = amplitude;
	}
}
