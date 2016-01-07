package com.cgaltier.mgame;

import com.cgaltier.mgame.Utils.Global;

/**
 * Created by Christian on 07/01/2016.
 */
public class GameWorldData {
   public HumanResources humanResources;
   public GameWorldData (){
      humanResources = new HumanResources(Global.HUMAN_RESOURCES_START, Global.HUMAN_RESOURCES_STARTSHIPCREW);
   }
}
