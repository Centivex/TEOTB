package com.mygdx.game.Pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Actores.Camara;
import com.mygdx.game.General.BaseScreen;

public class Pantalla1 extends BaseScreen {

    //Camara y escenario
    private OrthographicCamera cam;
    private Viewport viewp;
    private Stage stage;

    //Actores
    private Camara camara;

    public Pantalla1(Game game) {
        super(game);

        //Camara
        cam= new OrthographicCamera();
        viewp=new FitViewport(256,256,cam);

        //---------------------------------------------------------------------------------------------------------
        //Actores
        camara=new Camara(cam);

        //---------------------------------------------------------------------------------------------------------
        //Escenario
        stage=new Stage(viewp);
        stage.addActor(camara);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        camara.remove();
        stage.dispose();
    }
}
