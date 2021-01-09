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
    private Animaciones animaciones[] = new Animaciones[8];
    private int a=0;
    private boolean play=false;
    //para medidas
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

        //parado
        animaciones[0]=new Animaciones(textAtlas,"Walk_Up",1);
        animaciones[1]=new Animaciones(textAtlas,"Walk_Down",1);
        animaciones[2]=new Animaciones(textAtlas,"Walk_Left",1);
        animaciones[3]=new Animaciones(textAtlas,"Walk_Right",1);

        //mov
        animaciones[4]=new Animaciones(textAtlas,"Walk_Up",4);
        animaciones[5]=new Animaciones(textAtlas,"Walk_Down",4);
        animaciones[6]=new Animaciones(textAtlas,"Walk_Left",4);
        animaciones[7]=new Animaciones(textAtlas,"Walk_Right",4);

        //attack
 /*        animaciones[4]=new Animaciones(textAtlas,"Attack_Up",4);
        animaciones[5]=new Animaciones(textAtlas,"Attack_Down",4);
        animaciones[6]=new Animaciones(textAtlas,"Attack_Left",4);
        animaciones[7]=new Animaciones(textAtlas,"Attack_Right",4);*/

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
            a=4;
            play=true;
        }else  if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            playerbody.setLinearVelocity(0,-50*ppm);
            a=5;
            play=true;
        }else  if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            playerbody.setLinearVelocity(-50*ppm,0);
            a=6;
            play=true;
        }else  if (Gdx.input.isKeyPressed(Input.Keys.D) ) {
            playerbody.setLinearVelocity(50*ppm,0);
            a=7;
            play=true;
        }else{
            playerbody.setLinearVelocity(0,0);
            play=false;
        }


        //parado
        if (play==false){
            animaciones[a].reset();
            if (a==4){
                a=0;
            }else if (a==5){
                a=1;
            }else if (a==6){
                a=2;
            }else if (a==7){
                a=3;
            }
        }

        //mmmmmmmmmmmmm
        /*if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            playerbody.setLinearVelocity(0,0);
            a=a+4;
            play=true;
        }
*/

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        animaciones[a].play(batch,play,playerbody.getPosition().x-(((spritPlayer.getWidth()/2))*ppm), playerbody.getPosition().y-((spritPlayer.getHeight()/2)*ppm));


    }

}
