package com.cgaltier.mgame.Screens;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.cgaltier.mgame.MGame;

/**
 * Created by Christian on 23/12/2015.
 */
public abstract class AbstractMScreen extends InputAdapter implements Screen {

   MGame game;
   public AbstractMScreen (MGame game){
      this.game = game;
   }
   @Override
   public void resize(int width, int height) {

   }

   @Override
   public void pause() {

   }

   @Override
   public void resume() {

   }

   @Override
   public void hide() {

   }

   @Override
   public void dispose() {

   }

}
