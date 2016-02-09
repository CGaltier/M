package com.cgaltier.mgame.Stages.MapStage;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.cgaltier.mgame.MGame;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.cgaltier.mgame.Utils.Global;

import java.awt.event.InputEvent;


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
   public static final String LAYER_MAP = "Map";
   public static final String LAYER_SELECTION= "Selection";
   public static final String LAYER_BUILDING= "Building";
   public static final String SELECTION_TILE= "SelectionTile";


   TiledMapRenderer tiledMapRenderer ;
   public float mapScale;


   public TiledMapStage (MGame game){

      this.game = game;

      //this.mapScale = 10.0f*(1.0f/Global.WORLD_HEIGHT);//1.0f/(10.0f/Global.WORLD_HEIGHT);
      this.mapScale = .20f;
      tiledMap = new TmxMapLoader().load("asteroid.tmx");
      tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap,mapScale );

      this.mapScale = 1.0f/this.mapScale;
      dragging = false ;
      touchedDragStart = new Vector3();
      lastTouched = new Vector3();

      hasCurrentCellSelected=false;

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
      getCamera().update();
      return true;
   }
   @Override
   public boolean touchDown (int x, int y, int pointer, int button){

      if (button == Input.Buttons.MIDDLE){
         Global.logger.info("touch screen Middle button pos " + x + " " + y);
         touchedDragStart.set((float) x, (float) y, 0.0f);
         getCamera().unproject(touchedDragStart);
         lastTouched.set(touchedDragStart);
         dragging = true;
         return true;
      }
      if (button == Input.Buttons.LEFT){
         Global.logger.info("touch screen Left button pos " + x + " " + y);
         Vector3 clicked = new Vector3(x,y,0.0f);
         getCamera().unproject(clicked);
         ////---- drag on left click
         touchedDragStart.set(clicked);

         lastTouched.set(touchedDragStart);
         //dragging = true;

         return true;
      }

      return false;
   }

   private void processCellClicked(float x, float y) {
         TiledMapTileLayer.Cell cell=null;
         TiledMapTileLayer layer = getMapLayer();
         float tileHeight = layer.getTileHeight();
         float tileWidth = layer.getTileWidth();
         int cellX = (int)Math.floor((x*mapScale)/(tileWidth+2.0f));
         int cellY = (int)Math.floor((y*mapScale)/(tileHeight+2.0f));
         cell = layer.getCell(cellX,cellY);
         Global.logger.info("Clicked cell : X->" + cellX + ", Y->" + cellY + " Pos: " + x + ", " + y);
         switchSelectCell(cellX, cellY);
   }


   @Override
   public boolean touchDragged (int x, int y, int pointer){
      if (dragging ) {
         Global.logger.info("drag screen pos " + x + " " + y);

         touchedDragStart.set(x, y, 0.0f);
         getCamera().unproject(touchedDragStart);
         Global.logger.info("drag screen last" + lastTouched.x + " " + lastTouched.y);
         Global.logger.info("drag screen current" + touchedDragStart.x+ " " + touchedDragStart.y);
         float diffX = lastTouched.x - touchedDragStart.x;
         float diffY = lastTouched.y - touchedDragStart.y;
         Global.logger.info("drag screen move" + diffX + " " + diffY);
         ((OrthographicCamera)getCamera()).position.set(((OrthographicCamera)this.getViewport().getCamera()).position.x + diffX,
         ((OrthographicCamera)getCamera()).position.y + diffY, 0.0f);
         getCamera().update();

         //recompute current screen mouse position now that the camera has changed.
         //and memorize it
         lastTouched.set(x, y, 0.0f);
         getCamera().unproject(lastTouched);
      }
      return true;
   }

   @Override
   public boolean touchUp(int x, int y, int pointer, int button){

      if (button == Input.Buttons.MIDDLE && dragging){
         dragging=false;
         return true;
      }
      if (button == Input.Buttons.LEFT){
         Vector2 clicked2D = new Vector2(x,y);
         Global.logger.info("touch screen Left button pos " + x + " " + y);
         screenToStageCoordinates(clicked2D);
         Global.logger.info("Click stage coordinates" + clicked2D.x + " " + clicked2D.y);
         Vector3 clicked = new Vector3(x,y,0.0f);
         getCamera().unproject(clicked);
         processCellClicked(clicked.x, clicked.y);
         return true;
      }

      return false;
   }
   @Override
   public boolean keyUp(int keyCode){

      if(keyCode == Input.Keys.LEFT){
         ((OrthographicCamera)getCamera()).translate(-32, 0);
         return true;}
      if(keyCode == Input.Keys.RIGHT){
         ((OrthographicCamera)getCamera()).translate(32, 0);
         return true;}
      if(keyCode == Input.Keys.UP){
         ((OrthographicCamera)getCamera()).translate(0, -32);
         return true;}
      if(keyCode == Input.Keys.DOWN){
         ((OrthographicCamera)getCamera()).translate(0, 32);
         return true;}
      if(keyCode == Input.Keys.NUM_1){
         tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
         return true;}
      if(keyCode == Input.Keys.NUM_2){
         tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
         return true;}
      return false;
   }



   public void draw(OrthographicCamera camera) {
      super.draw();
      tiledMapRenderer.setView(camera);

      tiledMapRenderer.render();
   }
   @Override
   public void dispose(){
      super.dispose();
      tiledMap.dispose();
   }
}
