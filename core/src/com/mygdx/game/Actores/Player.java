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
    private Animaciones animaciones[] = new Animaciones[4];
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

        //sprite solo para medidas
        textAtlas=new TextureAtlas(Gdx.files.internal("Player/Player.atlas"));
        textAtlasRegion= textAtlas.findRegion("Walk_Down",0);
        spritPlayer= new Sprite(textAtlasRegion);
        //spritPlayer.setScale(ppm);

        //--------------------------------------------------------------------------------------------------------
        //animacion

        animaciones[0]=new Animaciones(textAtlas,"Walk_Up",4);
        animaciones[1]=new Animaciones(textAtlas,"Walk_Down",4);
        animaciones[2]=new Animaciones(textAtlas,"Walk_Left",4);
        animaciones[3]=new Animaciones(textAtlas,"Walk_Right",4);

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
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            playerbody.setLinearVelocity(0,50*ppm);
            a=0;
            mov=true;
        }else  if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            playerbody.setLinearVelocity(0,-50*ppm);
            a=1;
            mov=true;
        }else  if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            playerbody.setLinearVelocity(-50*ppm,0);
            a=2;
            mov=true;
        }else  if (Gdx.input.isKeyPressed(Input.Keys.D) ) {
            playerbody.setLinearVelocity(50*ppm,0);
            a=3;
            mov=true;
        }else{
            playerbody.setLinearVelocity(0,0);
            mov=false;
        }


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        animaciones[a].play(batch,mov,playerbody.getPosition().x-(((spritPlayer.getWidth()/2))*ppm), playerbody.getPosition().y-((spritPlayer.getHeight()/2)*ppm));



    }

}
