package us.rescyou.sine.Entity;

import us.rescyou.sine.Assets;
import us.rescyou.sine.Level.Level;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Coin extends Entity {

	// Initialization
	public Coin(Level level, int x, int y) {
		super(level, x, y, new Sprite(Assets.coin));
	}

	// Updating
	@Override
	public void update() {
		translate(0, -getSpeed());
	}
	
	// Getters
	public int getSpeed() {
		return Level.COIN_GAP_DISTANCE / getLevel().getPeriod();
	}
}
