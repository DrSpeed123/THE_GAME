package com.myfirst.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyFirstGame extends ApplicationAdapter {
	SpriteBatch batch;
	Background background;
	Hero hero;
	private Asteroid[] asteroids;
	private final int ASTEROID_COUNT = 20;
	public static Bullet[] bullets;
	private static boolean paused = false;
	private static boolean pause_pressed = false;
    private static int hero_damage = 0;
    private BitmapFont font;

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
        font = new BitmapFont(new FileHandle("font.fnt"));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
        update();
        paused();
		background.render(batch);
		hero.render(batch);
		for (int i = 0; i < asteroids.length; i++) {
			asteroids[i].render(batch);
		}
		for (int i = 0; i < bullets.length; i++) {
			if (bullets[i].isActive())
				bullets[i].render(batch);
		}
        if (hero_damage > 3) {
            font.draw(batch, "GAME OVER!!!", 500, 350);
        }
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public void  update(){
		if (!isPaused()) {
            getHero_damage();
            if (hero_damage <= 3) {
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
                        if (asteroids[i].getRect().contains(bullets[j].getPosition())) {
//				if (asteroids[i].getPosition().cpy().sub(bullets[j].getPosition()).len() < 20){
                            asteroids[i].recreate();
                            bullets[j].destroy();
                        }
                    }
                }
                for (int i = 0; i < asteroids.length; i++) {
                    if (asteroids[i].getRect().contains(hero.getPosition())) {
                        asteroids[i].recreate();
                        hero_damage++;
                    }
                }
            }
     //       font.draw(batch, "Game over!!!", 300, 200);
        }
	}

    public static int getHero_damage() {
        return hero_damage;
    }

    public void paused(){
		if (!isPaused()) {
			if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
				if(!pause_pressed) {
					pause_pressed = true;
					paused = true;
				}
			}
			else
			{
				pause_pressed = false;
			}
		}
		else {
			if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
				if(!pause_pressed) {
					pause_pressed = true;
					paused = false;
				}
			}
			else
			{
				pause_pressed = false;
			}
		}
	}

	public static boolean isPaused() {
		return paused;
	}
}
