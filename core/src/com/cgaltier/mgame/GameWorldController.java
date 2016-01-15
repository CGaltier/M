package com.cgaltier.mgame;

/**
 * Created by Christian on 13/01/2016.
 */
public class GameWorldController {
   private final GameWorldData gameWorldData;

   public GameWorldController(GameWorldData gameWorldData) {
      this.gameWorldData = gameWorldData;
   }

   public void update(float delta) {
      gameWorldData.update(this,delta);
   }
}
