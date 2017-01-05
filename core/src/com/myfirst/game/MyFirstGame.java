package com.myfirst.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyFirstGame extends ApplicationAdapter {
	SpriteBatch batch;
	Background background;
	Hero hero;
	private Asteroid[] asteroids;
	private final int ASTEROID_COUNT = 20;
	public static Bullet[] bullets;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Background();
		hero = new Hero();
		asteroids = new Asteroid[ASTEROID_COUNT];
		for (int i = 0; i < asteroids.length; i++) {
			asteroids[i] = new Asteroid();
		}
		bullets = new Bullet[50];
		for (int i = 0; i < bullets.length; i++) {
			bullets[i] = new Bullet();
		}
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		background.render(batch);
		hero.render(batch);
		for (int i = 0; i < asteroids.length; i++) {
			asteroids[i].render(batch);
		}
		for (int i = 0; i < bullets.length; i++) {
			if (bullets[i].isActive())
				bullets[i].render(batch);
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public void  update(){
		background.update();
		hero.update();
		for (int i = 0; i < asteroids.length; i++) {
			asteroids[i].update();
		}
		for (int i = 0; i < bullets.length; i++) {
			if (bullets[i].isActive())
				bullets[i].update();
		}
		for (int i = 0; i < asteroids.length; i++) {
			for (int j = 0; j < bullets.length; j++) {
				if (asteroids[i].getRect().contains(bullets[j].getPosition())){
//				if (asteroids[i].getPosition().cpy().sub(bullets[j].getPosition()).len() < 20){
					asteroids[i].recreate();
					bullets[j].destroy();
				}
			}

		}
	}
}
