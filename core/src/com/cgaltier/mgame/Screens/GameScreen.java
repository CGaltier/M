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
import com.badlogic.gdx.utils.viewport.FitViewport;
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

   TiledMapStage mapStage;
   UIStage uiStage;
   InputMultiplexer inputMultiplexer;



   public GameScreen(MGame game){
      super(game);


      inputMultiplexer = new InputMultiplexer();

      Global.getInstance().initResolutionList();
      fileResolver = new ResolutionFileResolver(new InternalFileHandleResolver(), Global.resolutionList[0],Global.resolutionList[1],Global.resolutionList[2]);

      camera = new OrthographicCamera();

      mapStage = new TiledMapStage(game);
      mapStage.getViewport().setCamera(camera);

      uiStage = new UIStage(game);

      uiStage.setViewport(new FitViewport(1024,768));
      uiStage.getViewport().update(1024, 768, true);

      inputMultiplexer.addProcessor(this);
      inputMultiplexer.addProcessor(uiStage);
      inputMultiplexer.addProcessor(mapStage);
      //mapStage.setMapScale(mapScale);
      pause();
   }
   @Override
   public void resize(int width, int height) {
      camera.setToOrtho(false, Global.WORLD_HEIGHT * width / (float) height, Global.WORLD_HEIGHT);
      //camera.setToOrtho(false,width,height);
      camera.update();
      uiStage.resize(width, height);

      //batch.setProjectionMatrix(camera.combined);
   }
   private void showMenuScreen() {
      game.gameWorld.gameWorldController.pauseGame();
      game.setMainMenuScreen();
   }
   private void showTestScreen() {
      game.gameWorld.gameWorldController.pauseGame();
      game.setTestScreen();
   }

   @Override
   public void show() {
      Gdx.input.setInputProcessor(inputMultiplexer);
      game.gameWorld.gameWorldController.restartGame();

   }
   @Override
   public void resume(){
      game.gameWorld.gameWorldController.restartGame();
   }
   @Override
   public void pause(){
      super.pause();
      game.gameWorld.gameWorldController.pauseGame();
   }


   @Override
   public void render (float delta) {
      Gdx.gl.glClearColor(1, 0, 0, 1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
      camera.update();
      uiStage.act(delta);
      mapStage.act(delta);

      uiStage.updateUI();

      mapStage.draw(camera);
      uiStage.draw();
      game.gameWorld.update(delta);
   }

   @Override
   public void dispose(){

      mapStage.dispose();
      uiStage.dispose();
   }


   @Override
   public boolean keyDown(int keycode) {
      return false;
   }

   @Override
   public boolean keyUp(int keyCode){
      if(keyCode == Input.Keys.ESCAPE){
         showMenuScreen();
         return true;}
      if(keyCode == Input.Keys.F12){
         showTestScreen();
         return true;}
      return false;
   }

   @Override
   public boolean keyTyped(char character) {
      return false;
   }

   @Override
   public boolean touchDown(int screenX, int screenY, int pointer, int button) {
      return false;
   }

   @Override
   public boolean touchUp(int screenX, int screenY, int pointer, int button) {
      return false;
   }

   @Override
   public boolean touchDragged(int screenX, int screenY, int pointer) {
      return false;
   }

   @Override
   public boolean mouseMoved(int screenX, int screenY) {
      return false;
   }

   @Override
   public boolean scrolled(int amount) {
      return false;
   }
}
