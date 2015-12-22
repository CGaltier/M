package com.cgaltier.mgame;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import com.badlogic.gdx.InputMultiplexer;
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
import com.badlogic.gdx.Input.Keys;

public class MGame extends InputAdapter implements ApplicationListener {

	SpriteBatch batch;
	Texture img;
   ResolutionFileResolver fileResolver;
   OrthographicCamera camera;

   TiledMap tiledMap;
   TiledMapRenderer tiledMapRenderer ;

   TiledMapStage mapStage;
   InputMultiplexer inputMultiplexer;

   public float mapScale;
   @Override
	public void create () {
      inputMultiplexer = new InputMultiplexer();

      Global.getInstance().initResolutionList();
      fileResolver = new ResolutionFileResolver(new InternalFileHandleResolver(), Global.resolutionList[0],Global.resolutionList[1],Global.resolutionList[2]);
		batch = new SpriteBatch();
		img = new Texture(fileResolver.resolve("badlogic.jpg"));
      camera = new OrthographicCamera();
      tiledMap = new TmxMapLoader().load("asteroid.tmx");

      mapScale = 10.0f/Global.WORLD_HEIGHT;
      tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap,mapScale );



      mapStage = new TiledMapStage(tiledMap);

      mapStage.getViewport().setCamera(camera);

      inputMultiplexer.addProcessor(this);
      inputMultiplexer.addProcessor(mapStage);
      mapStage.setMapScale (mapScale);
      Gdx.input.setInputProcessor(inputMultiplexer);
   }

   @Override
   public void resize(int width, int height) {
      camera.setToOrtho(false, Global.WORLD_HEIGHT*width/(float)height,Global.WORLD_HEIGHT);
      //camera.setToOrtho(false,width,height);
      camera.update();

      batch.setProjectionMatrix(camera.combined);
   }

   @Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
      camera.update();
		/*batch.begin();
		batch.draw(img, 0, 0, 50.0f,50.0f);
		batch.end();*/

      tiledMapRenderer.setView(camera);
      mapStage.act();
      tiledMapRenderer.render();
	}

   @Override
   public void pause() {

   }

   @Override
   public void resume() {

   }

   @Override
   public void dispose() {

   }
   @Override
   public boolean keyDown(int keyCode){
      return false;
   }

   @Override
   public boolean keyUp(int keyCode){
      if(keyCode == Keys.LEFT)
         camera.translate(-32,0);
      if(keyCode == Input.Keys.RIGHT)
         camera.translate(32,0);
      if(keyCode == Input.Keys.UP)
         camera.translate(0,-32);
      if(keyCode == Input.Keys.DOWN)
         camera.translate(0,32);
      if(keyCode == Input.Keys.NUM_1)
         tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
      if(keyCode == Input.Keys.NUM_2)
         tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
      return false;
   }

}
