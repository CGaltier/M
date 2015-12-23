package com.cgaltier.mgame.Stages.MapStage;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cgaltier.mgame.Utils.Global;


/**
 * Created by Christian on 22/12/2015.
 */

public class TiledMapActor extends Actor{
   private TiledMapStage tiledMapStage;

   public TiledMapActor (TiledMapStage tiledMapStage){
         this.tiledMapStage= tiledMapStage;
      }
}