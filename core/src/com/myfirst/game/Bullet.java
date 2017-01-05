package com.myfirst.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by DRSPEED-PC on 05.01.2017.
 */
public class Bullet {
    private Vector2 position;
    private float speed;
    private Texture texture;
    private boolean active;


    public Bullet(){
        position = new Vector2(0.0f, 0.0f);
        speed = 10.0f;
        active = false;
        if (texture == null)
        texture = new Texture("bullet20.tga");

    }

    public void render(SpriteBatch batch){
        batch.draw(texture, position.x, position.y, 20, 10);
    }

    public void update(){
        position.x +=  speed;
        if (position.x > Gdx.graphics.getWidth() + 10.0f){
            active = false;
        }
    }

    public void destroy(){
        active = false;
    }

    public void setup(float x, float y){
        position.x = x;
        position.y = y;
        active = true;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        destroy();
    }

    public Vector2 getPosition() {
        return position;
    }
}
