package com.cgaltier.mgame;

/**
 * Created by Christian on 07/01/2016.
 */
public class GameWorld {
   public GameWorldData gameWorldData;
   public GameWorldController gameWorldController;
   public GameWorld(){
      gameWorldData = new GameWorldData();
      gameWorldController= new GameWorldController(gameWorldData);
   }
   public void update(float delta){
      //things to do here
      updateResources(delta);
   }
   public void updateResources (float delta){
      gameWorldController.update(delta);
   }
}
