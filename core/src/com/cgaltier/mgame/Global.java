package com.cgaltier.mgame;

import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver.Resolution;

/**
 * Created by Christian on 22/12/2015.
 */
public class Global {
   public static Global instance=null;
   public static final float WORLD_HEIGHT = 60.0f;//world is 60 whatever (let's say km) tall.

   private Global (){
      instance =this;
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
}
