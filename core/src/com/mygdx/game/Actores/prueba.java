package com.mygdx.game.Actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.mygdx.game.General.Constante.ppm;

public class prueba extends Actor {

    private boolean mov=false;

    //sprites y animaciones
    private TextureAtlas textAtlas;
    private Animaciones animaciones;
    private Animaciones animaciones2;

    private int a=1;

    private Texture text;
    private Texture text2;

    public prueba(){

        //sprite
        textAtlas=new TextureAtlas(Gdx.files.internal("Player/Player.atlas"));

        //--------------------------------------------------------------------------------------------------------
        //animacion
        animaciones= new Animaciones(textAtlas,"Walk_Down",4);
        animaciones2= new Animaciones(textAtlas,"Walk_Right",4);


    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            a=1;
            mov=true;
        }else if (Gdx.input.isKeyPressed(Input.Keys.W)){
            a=2;
            mov=true;

        }else {
            mov = false;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (a==1) {
            animaciones.play(batch, mov,0,0);
        }else if (a==2) {
            animaciones2.play(batch, mov,0,0);
        }

    }


}
