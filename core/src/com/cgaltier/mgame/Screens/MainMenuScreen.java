package com.cgaltier.mgame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.cgaltier.mgame.MGame;
import com.cgaltier.mgame.Utils.Global;

/**
 * Created by Christian on 23/12/2015.
 */
public class MainMenuScreen extends AbstractMScreen {
   Stage UIMainMenu;
   public MainMenuScreen(MGame game) {
      super(game);
      this.game = game;

      UIMainMenu = new Stage();

      Gdx.input.setInputProcessor(UIMainMenu);

      Table table = new Table();
      UIMainMenu .addActor(table);
      table.setFillParent(true);
      TextButton button = new TextButton("Start Game", game.mAssets.getSkin());
      button.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeEvent event, Actor actor) {
            //Global.logger.info("button changed");
            setGameScreen();
         }
      });
      /*
      button.addListener(new InputListener(){
         public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
            Global.logger.info("button touched");
            return true;
         }
      });
      */
      //table.debug();

      table.add(button);
   }

   @Override
   public void show() {
      //Gdx.input.setInputProcessor(this);
      Gdx.input.setInputProcessor(UIMainMenu);
   }

   @Override
   public void render(float delta) {
      Gdx.gl.glClearColor(1, 1, 0, 1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
      UIMainMenu.act(Gdx.graphics.getDeltaTime());
      UIMainMenu.draw();
   }
   public void setGameScreen(){
      game.setScreen(game.gameScreen);
   }
/*
   @Override
   public boolean keyUp(int keyCode){
      game.setScreen(game.gameScreen);
      return true;
   }*/
   @Override
   public void resize (int width, int height){
      UIMainMenu.getViewport().update(width, height, true);
   }
   @Override
   public void dispose(){
      UIMainMenu.dispose();
   }
}
