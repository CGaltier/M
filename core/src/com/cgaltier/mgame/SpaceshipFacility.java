package com.cgaltier.mgame;

/**
 * Created by Christian on 19/01/2016.
 */
public class SpaceshipFacility extends Facility {
   @Override
   public void produce(GameWorldController worldController, Resources resources) {
      if (consume(worldController, resources));
      {

      }
   }

   @Override
   public void build(GameWorldController worldController) {

   }

   @Override
   public boolean canBuild(GameWorldController worldController) {
      return false;
   }

   @Override
   public boolean consume(GameWorldController worldController, Resources resources) {
      return false;
   }
}
