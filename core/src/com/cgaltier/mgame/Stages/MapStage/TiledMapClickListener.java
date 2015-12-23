package com.cgaltier.mgame.Stages.MapStage;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.cgaltier.mgame.Stages.MapStage.TiledMapActor;

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

         com.cgaltier.mgame.Utils.Global.logger.info("Map clicked "+x+" "+y);
         actor.getClickedCell(x,y);
      }
   }
