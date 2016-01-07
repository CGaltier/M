package com.cgaltier.mgame.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver.Resolution;
import com.badlogic.gdx.utils.Logger;

/**
 * Created by Christian on 22/12/2015.
 */
public class Global {
   public static final String WORMHOLE_IMG = "wormhole.png";
   public static final float WORMHOLE_ANIM_DURATION = 1/8.0f;
   public static final int HUMAN_RESOURCES_START = 1000;
   public static final int HUMAN_RESOURCES_STARTSHIPCREW = 10;
   public static final String HR_CRYO_IMAGE = "cryo";
   public static final String HR_IDLE_IMAGE = "idle";
   public static final String HR_MAINTENANCE_IMAGE = "maintenance";
   public static final String HR_PRODUCTION_IMAGE = "production";
   public static final String HR_MISSION_IMAGE = "mission";
   public static final String HR_EMERGENCY_IMAGE = "emergency";
   public static final String UI_IMAGES_ATLAS = "UIImages.atlas";
   public static Global instance=null;
   public static Logger logger;
   public static final float WORLD_HEIGHT = 60.0f;//world is 60 whatever (let's say km) tall.
   public static final String SKIN_ASSET="skin.json";
   public static final String PLIC_SOUND="plic.mp3";
   public static final String WORMHOLE_ANIM_ATLAS="wormhole_anim.atlas";
   public static final UIStrings strings = new UIStrings();

   private Global (){
      instance =this;
      logger = new Logger(Gdx.app.getClass().toString(),Logger.INFO);
      logger.info("Creation");
   }
   public static Global getInstance(){
      if (instance==null)
         new Global();
      return instance;
   }

   public static Resolution[] resolutionList ;
   public void initResolutionList (){
      resolutionList = new Resolution[]{ new Resolution (800,600,"600"),new Resolution(1280,720,"720"), new Resolution (1920,1080,"1080")};
   }


   public static class UIStrings {

      public String MOCKUP_NUMBER_DISPLAY="XX";

      public UIStrings(){

      };
   }
}
