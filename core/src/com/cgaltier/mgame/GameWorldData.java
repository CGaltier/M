package com.cgaltier.mgame;

import com.badlogic.gdx.utils.Array;
import com.cgaltier.mgame.Utils.Global;

/**
 * Created by Christian on 07/01/2016.
 */
public class GameWorldData {
   public float lastDeltaProduction;
   public int energyResources;
   public HumanResources humanResources;
   public Resources materialResources;
   public ProductionFacilities facilities;
   public GameWorldData (){
      lastDeltaProduction = 0.0f;
      humanResources = new HumanResources(Global.HUMAN_RESOURCES_START, Global.HUMAN_RESOURCES_STARTSHIPCREW);
      materialResources = new Resources();
      facilities = new ProductionFacilities();
   }

   public void update(GameWorldController worldController, float delta){
      lastDeltaProduction += delta;
      Resources turnProduction = new Resources();
      if (lastDeltaProduction>=Global.PRODUCTION_DELTATIME) {
         lastDeltaProduction = 0.0f;
         facilities.produce(worldController, turnProduction);
         //update stock

      }
   }
}
