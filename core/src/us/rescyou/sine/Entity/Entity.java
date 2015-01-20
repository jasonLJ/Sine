package us.rescyou.sine.Entity;

import us.rescyou.sine.Level.Level;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {

	// Private Variables
	private Level level;
	private int x, y;
	private Sprite sprite;

	// Initialization
	public Entity(Level level, int x, int y, Sprite sprite) {
		this.level = level;
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		sprite.setX(x);
		sprite.setY(y);
	}

	// Updating
	public abstract void update();

	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
	}

	// Miscellaneous
	public void moveTo(int x, int y) {
		setX(x);
		setY(y);
	}
	
	public void translate(int deltaX, int deltaY) {
		setX(getX() + deltaX);
		setY(getY() + deltaY);
	}
	
	// Getters
	public Level getLevel() {
		return level;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Sprite getSprite() {
		return sprite;
	}
	
	public float getWidth() {
		return getSprite().getWidth();
	}
	
	public float getHeight() {
		return getSprite().getHeight();
	}
	
	// Setters
	public void setLevel(Level level) {
		this.level = level;
	}
	
	public void setX(int x) {
		this.x = x;
		sprite.setX(x);
	}

	public void setY(int y) {
		this.y = y;
		sprite.setY(y);
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
}
