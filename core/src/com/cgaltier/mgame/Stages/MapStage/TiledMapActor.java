package com.cgaltier.mgame.Stages.MapStage;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cgaltier.mgame.Utils.Global;


/**
 * Created by Christian on 22/12/2015.
 */
public class TiledMapActor extends Actor{
   private TiledMapStage tiledMapStage;
   private float mapScale;

   public TiledMapActor (TiledMapStage tiledMapStage){
         this.tiledMapStage= tiledMapStage;
      }
      public TiledMapTileLayer.Cell getClickedCell(float x, float y){
         TiledMapTileLayer.Cell cell=null;
         TiledMapTileLayer layer = tiledMapStage.getMapLayer();
         float tileHeight = layer.getTileHeight();
         float tileWidth = layer.getTileWidth();
         int cellX = (int)Math.floor(x*mapScale/tileWidth);
         int cellY = (int)Math.floor(y*mapScale/tileHeight);
         cell = layer.getCell(cellX,cellY);
         Global.logger.info("Clicked cell : " + cell + " : X->" + cellX + ", Y->" + cellY);
         tiledMapStage.switchSelectCell(cellX, cellY);
         return cell;
      }

   public void setMapScale(float mapScale) {
      this.mapScale = 1.0f/mapScale;
   }
}