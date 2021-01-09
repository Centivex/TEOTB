package com.mygdx.game.Actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.HashMap;

import static com.mygdx.game.General.Constante.*;

public class Player extends Actor {

    //sprites y animaciones
    private TextureAtlas textAtlas;

    // guardamos las animaciones en un HashMap que mapea cada dirección con su animación
	private HashMap<Direction, Animaciones> animacionesParado = new HashMap<>();
	private HashMap<Direction, Animaciones> animacionesCaminando = new HashMap<>();
	private HashMap<Direction, Animaciones> animacionesAtacando = new HashMap<>();
    //para medidas
    private TextureAtlas.AtlasRegion textAtlasRegion;
    private Sprite spritPlayer;
	private Estados estadoActual = Estados.PARADO;


    //cuerpo
    private World world;
    private Body playerbody;
    private Fixture playerFixture;
    private Direction direction = Direction.ABAJO;

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
        animacionesParado.put(Direction.ARRIBA, new Animaciones(textAtlas,"Walk_Up",1));
        animacionesParado.put(Direction.ABAJO, new Animaciones(textAtlas,"Walk_Down",1));
        animacionesParado.put(Direction.IZQUIERDA, new Animaciones(textAtlas,"Walk_Left",1));
        animacionesParado.put(Direction.DERECHA, new Animaciones(textAtlas,"Walk_Right",1));

        //mov
        animacionesCaminando.put(Direction.ARRIBA, new Animaciones(textAtlas,"Walk_Up",4));
        animacionesCaminando.put(Direction.ABAJO, new Animaciones(textAtlas,"Walk_Down",4));
        animacionesCaminando.put(Direction.IZQUIERDA, new Animaciones(textAtlas,"Walk_Left",4));
        animacionesCaminando.put(Direction.DERECHA, new Animaciones(textAtlas,"Walk_Right",4));

        //attack
        animacionesAtacando.put(Direction.ARRIBA, new Animaciones(textAtlas,"Attack_Up",4));
        animacionesAtacando.put(Direction.ABAJO, new Animaciones(textAtlas,"Attack_Down",4));
        animacionesAtacando.put(Direction.IZQUIERDA, new Animaciones(textAtlas,"Attack_Left",4));
        animacionesAtacando.put(Direction.DERECHA, new Animaciones(textAtlas,"Attack_Right",4));

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
            direction = Direction.ARRIBA;
            estadoActual = Estados.CORRIENDO;
        }else  if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            playerbody.setLinearVelocity(0,-50*ppm);
	        direction = Direction.ABAJO;
	        estadoActual = Estados.CORRIENDO;
        }else  if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            playerbody.setLinearVelocity(-50*ppm,0);
	        direction = Direction.IZQUIERDA;
	        estadoActual = Estados.CORRIENDO;
        }else  if (Gdx.input.isKeyPressed(Input.Keys.D) ) {
            playerbody.setLinearVelocity(50*ppm,0);
	        direction = Direction.DERECHA;
	        estadoActual = Estados.CORRIENDO;
        }else{
	        estadoActual = Estados.PARADO;
            playerbody.setLinearVelocity(0,0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            playerbody.setLinearVelocity(0,0);
            estadoActual = Estados.ATACANDO;
        }

        // resetea las animaciones que no se están usando
	    switch (estadoActual){
		    case PARADO:
			    animacionesCaminando.get(direction).reset();
			    animacionesAtacando.get(direction).reset();
			    break;
		    case CORRIENDO:
			    animacionesParado.get(direction).reset();
			    animacionesAtacando.get(direction).reset();
			    break;
		    case ATACANDO:
			    animacionesParado.get(direction).reset();
			    animacionesCaminando.get(direction).reset();
			    break;
	    }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
	    Animaciones animacionActual = null;

	    // pilla la animacion que corresponde al estado
	    switch (estadoActual) {
		    case PARADO:
			    animacionActual = animacionesParado.get(direction);
			    break;
		    case CORRIENDO:
			    animacionActual = animacionesCaminando.get(direction);
			    break;
		    case ATACANDO:
			    animacionActual = animacionesAtacando.get(direction);
	    }

	    animacionActual.play(batch, estadoActual != Estados.PARADO,playerbody.getPosition().x-(((spritPlayer.getWidth()/2))*ppm), playerbody.getPosition().y-((spritPlayer.getHeight()/2)*ppm));
    }

}
