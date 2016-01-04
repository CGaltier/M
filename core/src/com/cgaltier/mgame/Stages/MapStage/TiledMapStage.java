package com.cgaltier.mgame.Stages.MapStage;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.cgaltier.mgame.MGame;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.cgaltier.mgame.Utils.Global;


/**
 * Created by Christian on 22/12/2015.
 * //useful tip found on http://stackoverflow.com/questions/24080272/libgdx-how-to-make-tiled-map-tiles-clickable
 * //because I'll be needing that soon
 */
public class TiledMapStage extends AbstractStage{
   public TiledMap tiledMap;
   TiledMapActor actor;
   TiledMapTileLayer.Cell selectionCell;
   TiledMapTile selectionTile;
   int currentCellX;
   int currentCellY;
   boolean hasCurrentCellSelected;
   Vector3 touchedDragStart;
   Vector3 lastTouched;
   boolean dragging ;
   MGame game ;
   float mapScale;
   public static final String LAYER_MAP = "Map";
   public static final String LAYER_SELECTION= "Selection";
   public static final String LAYER_BUILDING= "Building";
   public static final String SELECTION_TILE= "SelectionTile";



   public TiledMapStage (TiledMap map, float mapScale,MGame game){
      this.mapScale = 1.0f/mapScale;
      this.game = game;
      dragging = false ;
      touchedDragStart = new Vector3();
      lastTouched = new Vector3();

      hasCurrentCellSelected=false;
      tiledMap = map;
      actor = new TiledMapActor(this);
      TiledMapTileLayer tileLayer = (TiledMapTileLayer) tiledMap.getLayers().get(LAYER_MAP);
      actor.setBounds(0.0f, 0.0f, tileLayer.getWidth()*tileLayer.getTileWidth(),tileLayer.getHeight()*tileLayer.getTileHeight());
      addActor(actor);

      /*actor = new TiledMapActor(this);
      TiledMapTileLayer tileLayer = (TiledMapTileLayer) tiledMap.getLayers().get(0);

      actor.setBounds(0.0f, 0.0f, tileLayer.getWidth()*tileLayer.getTileWidth(),tileLayer.getHeight()*tileLayer.getTileHeight());
      addActor(actor);
      EventListener eventListener = new TiledMapClickListener(actor);
      actor.addListener(eventListener);
*/
      TiledMapTileLayer selectLayer = (TiledMapTileLayer)tiledMap.getLayers().get(LAYER_SELECTION);
      selectLayer.setOpacity(0.5f);
      TiledMapTileSet tileset = tiledMap.getTileSets().getTileSet(0);
      for (TiledMapTile tile:tileset){
         Object property = tile.getProperties().get(SELECTION_TILE);
         if (property != null) {
            selectionTile = tile;
            selectionCell = new TiledMapTileLayer.Cell();
            selectionCell.setTile(new StaticTiledMapTile(selectionTile.getTextureRegion()));
            break;
         }
      }
   }

   public TiledMapTileLayer getMapLayer() {
      return (TiledMapTileLayer)tiledMap.getLayers().get(LAYER_MAP);
   }

   public void switchSelectCell(int cellX, int cellY) {
      TiledMapTileLayer selectLayer = (TiledMapTileLayer)tiledMap.getLayers().get(LAYER_SELECTION);
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
   @Override
   public boolean keyDown(int keyCode){
      return false;
   }

   @Override
   public boolean scrolled (int amount){
      if (amount >0)
      {
         ((OrthographicCamera)this.getViewport().getCamera()) .zoom+=0.5f;
      }
      else if (amount <0){
         ((OrthographicCamera)this.getViewport().getCamera()).zoom-=0.5f;
      }
      else
      {
         return false;
      }
      ((OrthographicCamera)this.getViewport().getCamera()).update();
      return true;
   }
   @Override
   public boolean touchDown (int x, int y, int pointer, int button){

      if (button == Input.Buttons.MIDDLE){
         Global.logger.info("touch screen Middle button pos " + x + " " + y);
         touchedDragStart.set((float) x, (float) y, 0.0f);
         ((OrthographicCamera) this.getViewport().getCamera()).unproject(touchedDragStart);
         lastTouched.set(touchedDragStart);
         dragging = true;

         return true;
      }
      if (button == Input.Buttons.LEFT){
         Global.logger.info("touch screen Left button pos " + x + " " + y);
         Vector3 clicked = new Vector3(x,y,0.0f);
         getCamera().unproject(clicked);
         processCellClicked(clicked.x, clicked.y);


         ////---- drag on left click
         touchedDragStart.set(clicked);

         lastTouched.set(touchedDragStart);
         dragging = true;

         return true;
      }
      return false;
   }

   private void processCellClicked(float x, float y) {
         TiledMapTileLayer.Cell cell=null;
         TiledMapTileLayer layer = getMapLayer();
         float tileHeight = layer.getTileHeight();
         float tileWidth = layer.getTileWidth();
         int cellX = (int)Math.floor(x*mapScale/tileWidth);
         int cellY = (int)Math.floor(y*mapScale/tileHeight);
         cell = layer.getCell(cellX,cellY);
         Global.logger.info("Clicked cell : " + cell + " : X->" + cellX + ", Y->" + cellY);
         switchSelectCell(cellX, cellY);
   }

   @Override
   public boolean touchDragged (int x, int y, int pointer){
      if (dragging ) {
         Global.logger.info("drag screen pos " + x + " " + y);

         touchedDragStart.set(x, y, 0.0f);
         ((OrthographicCamera)this.getViewport().getCamera()).unproject(touchedDragStart);
         Global.logger.info("drag screen last" + lastTouched.x + " " + lastTouched.y);
         Global.logger.info("drag screen current" + touchedDragStart.x+ " " + touchedDragStart.y);
         float diffX = lastTouched.x - touchedDragStart.x;
         float diffY = lastTouched.y - touchedDragStart.y;
         Global.logger.info("drag screen move" + diffX + " " + diffY);
         ((OrthographicCamera)this.getViewport().getCamera()).position.set(((OrthographicCamera)this.getViewport().getCamera()).position.x + diffX,
         ((OrthographicCamera)this.getViewport().getCamera()).position.y + diffY, 0.0f);
         ((OrthographicCamera)this.getViewport().getCamera()).update();

         //recompute current screen mouse position now that the camera has changed.
         //and memorize it
         lastTouched.set(x, y, 0.0f);
         ((OrthographicCamera)this.getViewport().getCamera()).unproject(lastTouched);
      }
      return true;
   }
   @Override
   public boolean touchUp(int x, int y, int pointer, int button){
      if (dragging) {
         dragging = false;
         return true;
      }
      return false;
   }
   @Override
   public boolean keyUp(int keyCode){

      if(keyCode == Input.Keys.LEFT){
         ((OrthographicCamera)this.getViewport().getCamera()).translate(-32, 0);
         return true;}
      if(keyCode == Input.Keys.RIGHT){
         ((OrthographicCamera)this.getViewport().getCamera()).translate(32, 0);
         return true;}
      if(keyCode == Input.Keys.UP){
         ((OrthographicCamera)this.getViewport().getCamera()).translate(0, -32);
         return true;}
      if(keyCode == Input.Keys.DOWN){
         ((OrthographicCamera)this.getViewport().getCamera()).translate(0, 32);
         return true;}
      if(keyCode == Input.Keys.NUM_1){
         tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
         return true;}
      if(keyCode == Input.Keys.NUM_2){
         tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
         return true;}
      if(keyCode == Input.Keys.ESCAPE){
         game.setScreen(game.mainMenuScreen);
         return true;}
      return false;
   }
}
