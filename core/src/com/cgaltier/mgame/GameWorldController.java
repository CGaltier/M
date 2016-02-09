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
      gameWorldData.update(this, delta);
   }

   public long getTimePlayedMs() {
      return gameWorldData.timePlayed;
   }
   public void pauseGame(){
      gameWorldData.pause(true);
   }
   public void restartGame(){
      gameWorldData.pause(false);
   }

   public final HumanResources getHumanResources() {
      return gameWorldData.humanResources;
   }
   public final Resources getResources(){
      return gameWorldData.resources;
   }
}
