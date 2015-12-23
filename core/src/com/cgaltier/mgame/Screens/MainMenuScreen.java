package com.cgaltier.mgame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.cgaltier.mgame.MGame;

/**
 * Created by Christian on 23/12/2015.
 */
public class MainMenuScreen extends AbstractMScreen {

   public MainMenuScreen(MGame game) {
      super(game);
      this.game = game;

   }

   @Override
   public void show() {
      Gdx.input.setInputProcessor(this);
   }

   @Override
   public void render(float delta) {
      Gdx.gl.glClearColor(1, 1, 0, 1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
   }

   @Override
   public boolean keyUp(int keyCode){
      game.setScreen(game.gameScreen);
      return true;
   }
}
