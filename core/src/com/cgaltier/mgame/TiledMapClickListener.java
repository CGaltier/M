package com.cgaltier.mgame;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Christian on 22/12/2015.
 */
public class TiledMapClickListener extends ClickListener {
      private TiledMapActor actor;
      public TiledMapClickListener  (TiledMapActor actor){
         this.actor =actor;
      }
      @Override
      public void clicked (InputEvent event, float x, float y){

         Global.logger.info("Map clicked "+x+" "+y);
         actor.getClickedCell(x,y);
      }
   }
