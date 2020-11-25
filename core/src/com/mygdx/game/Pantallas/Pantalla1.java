package com.mygdx.game.Pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Actores.Camara;
import com.mygdx.game.Actores.Player;
import com.mygdx.game.General.BaseScreen;
import com.mygdx.game.General.Constante;

import static com.mygdx.game.General.Constante.*;

public class Pantalla1 extends BaseScreen {
    //mundo
    private World world;
    private Box2DDebugRenderer b2render;

    //Camara y escenario
    private OrthographicCamera cam;
    private Viewport viewp;
    private Stage stage;

    //Actores
    private Camara camara;
    private Player player;

    public Pantalla1(Game game) {
        super(game);

        //mundo
        world= new World(new Vector2(0,0), true);
        b2render=new Box2DDebugRenderer();

        //Camara
        cam= new OrthographicCamera();
        viewp=new FitViewport(256*ppm,256*ppm,cam);

        //---------------------------------------------------------------------------------------------------------
        //Actores
        camara=new Camara(cam);
        player=new Player(world);

        //---------------------------------------------------------------------------------------------------------
        //Escenario
        stage=new Stage(viewp);
        stage.addActor(camara);
        stage.addActor(player);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //escenario y mundo
        stage.act();
        world.step(1/60f,6,2);
        stage.draw();
        b2render.render(world,cam.combined);
    }

    @Override
    public void dispose() {
        camara.remove();
        player.getPlayerbody().destroyFixture(player.getPlayerFixture());
        world.destroyBody(player.getPlayerbody());
        player.remove();
        stage.dispose();
        b2render.dispose();
        world.dispose();
    }
}
