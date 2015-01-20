package us.rescyou.sine.Entity;

import us.rescyou.sine.Assets;
import us.rescyou.sine.Main;
import us.rescyou.sine.Level.Level;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends Entity {

	// Private Variables
	private float graphPosition;
	private int lastAbsoluteX;
	private int lastAbsoluteDelta;
	private boolean atPeak;

	// Initialization
	public Player(Level level, int x, int y) {
		super(level, x, y, new Sprite(Assets.player));
		graphPosition = 0;
		lastAbsoluteX = x;
		lastAbsoluteDelta = 0;
	}

	// Updating
	@Override
	public void update() {
		// Move along the virtual 'graph' of the sin function
		graphPosition += 1f / getLevel().getPeriod();
		graphPosition %= 1f;

		// Find the newX coordinate
		int newGraphX = (int) (Math.sin(((2f * Math.PI) / 1f) * graphPosition) * getLevel().getAmplitude());
		int newX = Math.round((newGraphX + ((Main.VIRTUAL_WIDTH / 2f) - (getWidth() / 2f))));

		// Move the player
		moveTo(newX, getY());
		
		// Find peak
		int currentAbsoluteX = getAbsoluteX(); // Position relative to the middle of the screen
		int deltaX = currentAbsoluteX - lastAbsoluteX;
		
		atPeak = (deltaX < 0 && lastAbsoluteDelta > 0);
		
		lastAbsoluteX = currentAbsoluteX;
		lastAbsoluteDelta = deltaX;
	}
	
	// Getters
	public int getAbsoluteX() {
		return (int) Math.abs(getX() - ((Main.VIRTUAL_WIDTH / 2) - (getWidth() / 2)));
	}
	
	public boolean isAtPeak() {
		return atPeak;
	}
}
