package com.mygdx.game.Actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.General.Constante;

import static com.mygdx.game.General.Constante.*;

public class Player extends Actor {

    //sprites y animaciones
    private TextureAtlas textAtlas;
    private Animaciones animaciones;
    private int a=0;
    private boolean mov=false;

    private TextureAtlas.AtlasRegion textAtlasRegion;
    private Sprite spritPlayer;


    //cuerpo
    private World world;
    private Body playerbody;
    private Fixture playerFixture;

    public Body getPlayerbody() {
        return playerbody;
    }

    public Fixture getPlayerFixture() {
        return playerFixture;
    }

    public Player(World world){
        this.world=world;

        //sprite
        textAtlas=new TextureAtlas(Gdx.files.internal("Player/Player.atlas"));
        textAtlasRegion= textAtlas.findRegion("Walk_Down",0);
        spritPlayer= new Sprite(textAtlasRegion);
        //spritPlayer.setScale(ppm);

        //--------------------------------------------------------------------------------------------------------
        //animacion

        animaciones=new Animaciones(textAtlas,"Walk_Up",4);
        //animaciones[1]=new Animaciones(textAtlas,"Walk_Down",4);
        //animaciones[2]=new Animaciones(textAtlas,"Walk_Left",4);
        //animaciones[3]=new Animaciones(textAtlas,"Walk_Right",4);

        //--------------------------------------------------------------------------------------------------------
        //cuerpo
        BodyDef defPlayer= new BodyDef();
        defPlayer.position.set((spritPlayer.getWidth()/2f)-1*ppm,spritPlayer.getHeight()/2);
        defPlayer.type= BodyDef.BodyType.DynamicBody;
        playerbody= world.createBody(defPlayer);


        PolygonShape playerShape= new PolygonShape();
        playerShape.setAsBox(5*ppm,5*ppm);
        playerFixture= playerbody.createFixture(playerShape,1);
        playerShape.dispose();

        playerbody.setTransform(0,0,0);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        //moviemiento
        if (Gdx.input.isKeyPressed(Input.Keys.W) && playerbody.getLinearVelocity().y<=2*ppm) {
            playerbody.applyLinearImpulse(new Vector2(0,1f*ppm),playerbody.getWorldCenter(),true);
            a=0;
            mov=true;
        }else  if (Gdx.input.isKeyPressed(Input.Keys.S) && playerbody.getLinearVelocity().y>=-2*ppm) {
            playerbody.applyLinearImpulse(new Vector2(0,-1f*ppm),playerbody.getWorldCenter(),true);
            a=1;
            mov=true;
        }else  if (Gdx.input.isKeyPressed(Input.Keys.A)&& playerbody.getLinearVelocity().x>=-2*ppm) {
            playerbody.applyLinearImpulse(new Vector2(-1f*ppm,0),playerbody.getWorldCenter(),true);
            a=2;
            mov=true;
        }else  if (Gdx.input.isKeyPressed(Input.Keys.D) && playerbody.getLinearVelocity().x<=2*ppm) {
            playerbody.applyLinearImpulse(new Vector2(1f*ppm,0),playerbody.getWorldCenter(),true);
            a=3;
            mov=true;
        }else{
            playerbody.setLinearVelocity(0,0);
            mov=false;
        }


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //spritPlayer.setPosition(playerbody.getPosition().x-((spritPlayer.getWidth()/2)-1*ppm), playerbody.getPosition().y-(spritPlayer.getHeight()/2));
        //spritPlayer.draw(batch);

        if(a==0){
            animaciones.play(batch,mov,0,0);
        }



    }

}
