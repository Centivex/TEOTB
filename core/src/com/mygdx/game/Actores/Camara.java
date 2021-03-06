package com.mygdx.game.Actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.General.Constante;

import static com.mygdx.game.General.Constante.*;

public class Camara extends Actor {
    private OrthographicCamera cam;

    public Camara(OrthographicCamera cam){
        this.cam=cam;

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        //movimiento de la camara
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            cam.translate(-16* ppm,0);
        }else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            cam.translate(16*ppm,0);
        }else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            cam.translate(0,16*ppm);
        }else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            cam.translate(0,-16*ppm);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setProjectionMatrix(cam.combined);
        cam.update();
    }


}
