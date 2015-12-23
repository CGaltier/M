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
import com.cgaltier.mgame.Utils.Global;
import com.cgaltier.mgame.MGame;
import com.cgaltier.mgame.Stages.MapStage.TiledMapStage;
import com.cgaltier.mgame.Stages.UIStage.UIStage;

/**
 * Created by Christian on 23/12/2015.
 */
public class GameScreen extends AbstractMScreen {
   SpriteBatch batch;
   Texture img;
   ResolutionFileResolver fileResolver;
   OrthographicCamera camera;

   TiledMap tiledMap;
   TiledMapRenderer tiledMapRenderer ;

   TiledMapStage mapStage;
   UIStage uiStage;
   InputMultiplexer inputMultiplexer;

   public float mapScale;
   Vector3 touchedMiddleButton ;
   Vector3 lastTouched;
   boolean dragging ;


   public GameScreen(MGame game){
      super(game);

      dragging = false ;
      touchedMiddleButton = new Vector3();
      lastTouched = new Vector3();
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
      mapStage.setMapScale(mapScale);

   }
   @Override
   public void resize(int width, int height) {
      camera.setToOrtho(false, Global.WORLD_HEIGHT * width / (float) height, Global.WORLD_HEIGHT);
      //camera.setToOrtho(false,width,height);
      camera.update();

      batch.setProjectionMatrix(camera.combined);
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
		/*batch.begin();
		batch.draw(img, 0, 0, 50.0f,50.0f);
		batch.end();*/

      tiledMapRenderer.setView(camera);
      mapStage.act();
      tiledMapRenderer.render();
   }


   @Override
   public boolean keyDown(int keyCode){
      return false;
   }

   @Override
   public boolean scrolled (int amount){
      if (amount >0)
      {
         camera.zoom+=0.5f;
      }
      else if (amount <0){
         camera.zoom-=0.5f;
      }
      else
      {
         return false;
      }
      camera.update();
      return true;
   }
   @Override
   public boolean touchDown (int x, int y, int pointer, int button){

      if (button == Input.Buttons.MIDDLE){
         Global.logger.info("touch screen Middle button pos "+x+" "+y);
         touchedMiddleButton.set((float) x, (float) y, 0.0f);
         camera.unproject(touchedMiddleButton);
         lastTouched.set ( touchedMiddleButton);
         dragging = true;
         return true;
      }
      return false;
   }
   @Override
   public boolean touchDragged (int x, int y, int pointer){
      if (dragging ) {
         Global.logger.info("drag screen pos " + x + " " + y);

         touchedMiddleButton.set(x, y, 0.0f);
         camera.unproject(touchedMiddleButton);
         Global.logger.info("drag screen last" + lastTouched.x + " " + lastTouched.y);
         Global.logger.info("drag screen current" + touchedMiddleButton.x+ " " + touchedMiddleButton.y);
         float diffX = lastTouched.x - touchedMiddleButton.x;
         float diffY = lastTouched.y - touchedMiddleButton.y;
         Global.logger.info("drag screen move" + diffX + " " + diffY);
         camera.position.set(camera.position.x + diffX,
         camera.position.y + diffY, 0.0f);
         camera.update();

         //recompute current screen mouse position now that the camera has changed.
         //and memorize it
         lastTouched.set(x, y, 0.0f);
         camera.unproject(lastTouched);
      }
      return true;
   }
   @Override
   public boolean touchUp(int x, int y, int pointer, int button){
      if (dragging) {
         dragging = false;
         return true;
      }
      return false;
   }
   @Override
   public boolean keyUp(int keyCode){

      if(keyCode == Input.Keys.LEFT){
         camera.translate(-32,0);
         return true;}
      if(keyCode == Input.Keys.RIGHT){
         camera.translate(32,0);
         return true;}
      if(keyCode == Input.Keys.UP){
         camera.translate(0,-32);
         return true;}
      if(keyCode == Input.Keys.DOWN){
         camera.translate(0,32);
         return true;}
      if(keyCode == Input.Keys.NUM_1){
         tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
         return true;}
      if(keyCode == Input.Keys.NUM_2){
         tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
         return true;}
      if(keyCode == Input.Keys.ESCAPE){
         game.setScreen(game.mainMenuScreen);
         return true;}
      return false;
   }

}
