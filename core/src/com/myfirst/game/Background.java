package com.myfirst.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by DRSPEED-PC on 05.01.2017.
 */
public class Background {
    private Texture texture;
    private Texture startex;
    private Star[] stars;
    private final int STARS_COUNT = 100;


    public Background(){
        texture = new Texture("staticback.jpg");
        startex = new Texture("star12.tga");
        stars = new Star[STARS_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star();
        }
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, 0, 0);
        for (int i = 0; i < stars.length; i++) {
            batch.draw(startex, stars[i].position.x , stars[i].position.y);
        }
    }

    public void update(){
        for (int i = 0; i < stars.length; i++) {
            stars[i].update();
        }
    }

    class Star{
        private Vector2 position;
        private float speed;

        public Star() {
            this.position = new Vector2((float)(Math.random() * Gdx.graphics.getWidth()), (float)(Math.random() * Gdx.graphics.getHeight()));
            this.speed = 1.0f * (float)(Math.random() * 5.0f);
        }

        public void update(){
            position.x -= speed;
            if (position.x < 0){
                position.x = Gdx.graphics.getWidth();
            }
        }
    }
}
