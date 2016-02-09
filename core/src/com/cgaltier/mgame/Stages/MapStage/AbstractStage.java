package com.cgaltier.mgame.Stages.MapStage;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.cgaltier.mgame.Utils.Global;

/**
 * Created by Christian on 23/12/2015.
 */
public class AbstractStage extends Stage {
   @Override
   public boolean keyDown(int keyCode){
      return false;
   }

   @Override
   public boolean scrolled (int amount){
      return false;
   }

   @Override
   public boolean touchDown (int x, int y, int pointer, int button){
      return false;
   }
   @Override
   public boolean touchDragged (int x, int y, int pointer){
      return false;
   }
   @Override
   public boolean touchUp(int x, int y, int pointer, int button){
      return false;
   }
   @Override
   public boolean keyUp(int keyCode){
      return false;
   }
}
