package com.myfirst.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by DRSPEED-PC on 05.01.2017.
 */
public class Asteroid {
    private Vector2 position;
    private float speed;
    private Texture texture;


    public Asteroid(){
        position = new Vector2(-100 + (float)(Math.random() * Gdx.graphics.getWidth()), -10 + (float)(Math.random() * Gdx.graphics.getHeight()));
        speed = 2.0f + (float)(Math.random() * 10.0f);
        texture = new Texture("asteroid60.tga");

    }

    public void render(SpriteBatch batch){
        batch.draw(texture, position.x, position.y);
    }

    public void recreate(){
        position = new Vector2(-100 + (float)(Math.random() * Gdx.graphics.getWidth()), -10 + (float)(Math.random() * Gdx.graphics.getHeight()));
        speed = 2.0f + (float)(Math.random() * 10.0f);
    }

    public void update(){
        position.x -=  speed;
        if (position.x < -100){
                position.x = Gdx.graphics.getWidth() + 10.0f;
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getRect(){
        return new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
    }
}
