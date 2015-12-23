package com.cgaltier.mgame.Stages.MapStage;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Christian on 22/12/2015.
 */
public class TiledMapCellActor extends Actor {
   private TiledMap tiledMap;
   private TiledMapTileLayer tileLayer;
   public TiledMapTileLayer.Cell cell;

   public TiledMapCellActor(TiledMap tiledMap, TiledMapTileLayer layer, TiledMapTileLayer.Cell cell){
      this.tiledMap= tiledMap;
      this.tileLayer = layer;
      this.cell = cell;
   }
}
