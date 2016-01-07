package com.cgaltier.mgame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.cgaltier.mgame.Utils.Global;
import com.cgaltier.mgame.MGame;
import com.cgaltier.mgame.Stages.MapStage.TiledMapStage;
import com.cgaltier.mgame.Stages.UIStage.UIStage;

/**
 * Created by Christian on 23/12/2015.
 */
public class GameScreen extends AbstractMScreen {
   //SpriteBatch batch;
   //Texture img;
   ResolutionFileResolver fileResolver;
   OrthographicCamera camera;

   TiledMap tiledMap;
   TiledMapRenderer tiledMapRenderer ;

   TiledMapStage mapStage;
   UIStage uiStage;
   InputMultiplexer inputMultiplexer;

   public float mapScale;


   public GameScreen(MGame game){
      super(game);

      inputMultiplexer = new InputMultiplexer();

      Global.getInstance().initResolutionList();
      fileResolver = new ResolutionFileResolver(new InternalFileHandleResolver(), Global.resolutionList[0],Global.resolutionList[1],Global.resolutionList[2]);

      camera = new OrthographicCamera();
      tiledMap = new TmxMapLoader().load("asteroid.tmx");

      mapScale = 10.0f/Global.WORLD_HEIGHT;
      tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap,mapScale );



      mapStage = new TiledMapStage(tiledMap, mapScale, game);
      mapStage.getViewport().setCamera(camera);

      uiStage = new UIStage(game);
      //uiStage.setViewport(mapStage.getViewport());


      inputMultiplexer.addProcessor(uiStage);
      inputMultiplexer.addProcessor(mapStage);
      //mapStage.setMapScale(mapScale);

   }
   @Override
   public void resize(int width, int height) {
      camera.setToOrtho(false, Global.WORLD_HEIGHT * width / (float) height, Global.WORLD_HEIGHT);
      //camera.setToOrtho(false,width,height);
      camera.update();
      uiStage.resize(width,height);


      //batch.setProjectionMatrix(camera.combined);
   }

   @Override
   public void show() {
      Gdx.input.setInputProcessor(inputMultiplexer);

   }

   @Override
   public void render (float delta) {
      Gdx.gl.glClearColor(1, 0, 0, 1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
      camera.update();
      uiStage.act(delta);
      mapStage.act(delta);

      //tiledMapRenderer.setView(camera);
      tiledMapRenderer.setView(camera);

      tiledMapRenderer.render();
      uiStage.draw();

   }

   @Override
   public void dispose(){
      tiledMap.dispose();
      mapStage.dispose();
      uiStage.dispose();
   }


}
