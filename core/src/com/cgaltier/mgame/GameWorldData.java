package com.cgaltier.mgame;

import com.badlogic.gdx.utils.Array;
import com.cgaltier.mgame.Utils.Global;

/**
 * Created by Christian on 07/01/2016.
 */
public class GameWorldData {
   public boolean pause;
   public long lastTimeSinceUpdate;
   public long timePlayed;
   public float lastDeltaProduction;
   public HumanResources humanResources;
   public Resources resources;
   public ProductionFacilities facilities;
   public GameWorldData (){
      lastDeltaProduction = 0.0f;
      humanResources = new HumanResources(Global.HUMAN_RESOURCES_START, Global.HUMAN_RESOURCES_STARTSHIPCREW);
      resources= new Resources();
      facilities = new ProductionFacilities();
      timePlayed=0;
      this.pause=true;
   }

   public void update(GameWorldController worldController, float delta){
      lastDeltaProduction += delta;
      Resources turnProduction = new Resources();
      if (lastDeltaProduction>=Global.PRODUCTION_DELTATIME) {
         lastDeltaProduction = 0.0f;
         facilities.produce(worldController, turnProduction);
         //update stock
         resources.add(turnProduction);
      }
      if (!this.pause){
         long time = System.currentTimeMillis();
         timePlayed +=time - lastTimeSinceUpdate;
         lastTimeSinceUpdate = time;
      }
   }

   public void pause (boolean pauseOn){

      if (this.pause && !pauseOn)
         lastTimeSinceUpdate = System.currentTimeMillis();
      this.pause = pauseOn;
   }

}
