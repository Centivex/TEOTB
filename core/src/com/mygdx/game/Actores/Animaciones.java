package com.mygdx.game.Actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.mygdx.game.General.Constante;

import static com.mygdx.game.General.Constante.*;

public  class Animaciones {

    //animacion
    private TextureAtlas textAtlas;
    private TextureRegion[] textRegion;
    private Animation<TextureRegion> anim;
    private float frame=0;

    //medidas
    private Sprite sprit;

    public Animaciones(TextureAtlas textAtlas, String nomFrame, int numFrame){
        this.textAtlas=textAtlas;

        //esto es para obtener la medida del primero y asi despues escalar
        TextureAtlas.AtlasRegion textAtlasRegion= textAtlas.findRegion(nomFrame,0);
        sprit= new Sprite(textAtlasRegion);

        //montamos la animacion
        textRegion= new TextureRegion[numFrame];

        for(int i=0;i<numFrame;i++){
            textRegion[i]= (textAtlas.findRegion(nomFrame,i));
        }

        anim= new Animation<TextureRegion>(0.1f, textRegion);
    }

    public void play(Batch batch,boolean mov, float posx, float posy){

        if(mov==true) {
            if (frame==0){
                frame=1;
            }
            frame += Gdx.graphics.getDeltaTime();
            System.out.println("frame "+frame);

        }

        TextureRegion currentFrame = anim.getKeyFrame(frame, mov);
        batch.draw(currentFrame, posx, posy, sprit.getWidth()*ppm,sprit.getHeight()*ppm);

    }

    public void reset(){
        frame=0;
    }

}
