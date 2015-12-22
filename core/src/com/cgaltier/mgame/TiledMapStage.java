package com.cgaltier.mgame;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;


/**
 * Created by Christian on 22/12/2015.
 * //useful tip found on http://stackoverflow.com/questions/24080272/libgdx-how-to-make-tiled-map-tiles-clickable
 * //because I'll be needing that soon
 */
public class TiledMapStage extends Stage{
   private TiledMap tiledMap;
   TiledMapActor actor;

   public TiledMapStage (TiledMap map){
      tiledMap = map;

      actor = new TiledMapActor(tiledMap);
      TiledMapTileLayer tileLayer = (TiledMapTileLayer) tiledMap.getLayers().get(0);

      actor.setBounds(0.0f, 0.0f, tileLayer.getWidth()*tileLayer.getTileWidth(),tileLayer.getHeight()*tileLayer.getTileHeight());
      addActor(actor);
      EventListener eventListener = new TiledMapClickListener(actor);
      actor.addListener(eventListener);


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
}
