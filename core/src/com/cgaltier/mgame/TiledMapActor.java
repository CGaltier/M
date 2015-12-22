package com.cgaltier.mgame;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;


/**
 * Created by Christian on 22/12/2015.
 */
public class TiledMapActor extends Actor{
   private TiledMap tiledMap;
   private float mapScale;

   public TiledMapActor (TiledMap tiledMap){
         this.tiledMap= tiledMap;
      }
      public TiledMapTileLayer.Cell getClickedCell(float x, float y){
         TiledMapTileLayer.Cell cell=null;
         TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
         float tileHeight = layer.getTileHeight();
         float tileWidth = layer.getTileWidth();
         int cellX = (int)Math.floor(x*mapScale/tileWidth);
         int cellY = (int)Math.floor(y*mapScale/tileHeight);
         cell = layer.getCell(cellX,cellY);
         Global.logger.info("Clicked cell : " + cell + " : X->" + cellX + ", Y->" + cellY);
         return cell;
      }

   public void setMapScale(float mapScale) {
      this.mapScale = 1.0f/mapScale;
   }
}