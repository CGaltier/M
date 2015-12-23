package com.cgaltier.mgame.Stages.MapStage;

import com.cgaltier.mgame.Stages.MapStage.TiledMapActor;
import com.cgaltier.mgame.Stages.MapStage.TiledMapCellActor;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;


/**
 * Created by Christian on 22/12/2015.
 * //useful tip found on http://stackoverflow.com/questions/24080272/libgdx-how-to-make-tiled-map-tiles-clickable
 * //because I'll be needing that soon
 */
public class TiledMapStage extends Stage{
   public TiledMap tiledMap;
   TiledMapActor actor;
   TiledMapTileLayer.Cell selectionCell;
   TiledMapTile selectionTile;
   int currentCellX;
   int currentCellY;
   boolean hasCurrentCellSelected;

   public TiledMapStage (TiledMap map){
      hasCurrentCellSelected=false;
      tiledMap = map;

      actor = new TiledMapActor(this);
      TiledMapTileLayer tileLayer = (TiledMapTileLayer) tiledMap.getLayers().get(0);

      actor.setBounds(0.0f, 0.0f, tileLayer.getWidth()*tileLayer.getTileWidth(),tileLayer.getHeight()*tileLayer.getTileHeight());
      addActor(actor);
      EventListener eventListener = new TiledMapClickListener(actor);
      actor.addListener(eventListener);

      TiledMapTileLayer selectLayer = (TiledMapTileLayer)tiledMap.getLayers().get("Selection");
      selectLayer.setOpacity(0.5f);
      TiledMapTileSet tileset = tiledMap.getTileSets().getTileSet(0);
      for (TiledMapTile tile:tileset){
         Object property = tile.getProperties().get("SelectionTile");
         if (property != null) {
            selectionTile = tile;
            selectionCell = new TiledMapTileLayer.Cell();
            selectionCell.setTile(new StaticTiledMapTile(selectionTile.getTextureRegion()));
            break;//selectionTile = new StaticTiledMapTile(selectionTile.getTextureRegion());
         }
      }


      /*for (MapLayer layer : tiledMap.getLayers()){
         tileLayer = (TiledMapTileLayer) layer;
         createActorsForLayer(tileLayer);
      }*/
   }

   private void createActorsForLayer(TiledMapTileLayer tileLayer) {
      float tileHeight = tileLayer.getTileHeight();
      float tileWidth= tileLayer.getTileWidth();
      for (int x = 0 ; x < tileLayer.getWidth(); ++x){
         for (int y = 0 ; y < tileLayer.getHeight(); ++y){
            TiledMapTileLayer.Cell cell = tileLayer.getCell(x,y);
            TiledMapCellActor actor = new TiledMapCellActor(tiledMap,tileLayer,cell);
            actor.setBounds(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
            addActor(actor);
            EventListener eventListener = new TiledMapCellClickListener(actor);
            actor.addListener(eventListener);
         }
      }
   }

   public void setMapScale(float mapScale) {
      actor.setMapScale(mapScale);
   }

   public TiledMapTileLayer getMapLayer() {
      return (TiledMapTileLayer)tiledMap.getLayers().get("Map");
   }

   public void switchSelectCell(int cellX, int cellY) {
      TiledMapTileLayer selectLayer = (TiledMapTileLayer)tiledMap.getLayers().get("Selection");
      TiledMapTileLayer.Cell cell = selectLayer.getCell(cellX, cellY);
      //Object property = cell.getTile().getProperties().get("SelectionTile");
      if (cell == null)
      {
         if (hasCurrentCellSelected)
            selectLayer.setCell(currentCellX, currentCellY, null);
         selectLayer.setCell(cellX, cellY, selectionCell);
         hasCurrentCellSelected=true;
         currentCellX=cellX;
         currentCellY=cellY;


      }
      else
      {
         selectLayer.setCell(cellX, cellY, null);
         hasCurrentCellSelected=false;
      }
   }
}
