package com.cgaltier.mgame;

/**
 * Created by Christian on 14/01/2016.
 */
public abstract class Facility {
   public int agglomeratedType;

   public boolean isType (FacilityType type){
      return type.isInSet(agglomeratedType);
   }
   Facility (FacilityType... types){
      agglomeratedType = FacilityType.getFlag(types);
   }
   public abstract void produce (GameWorldController worldController, Resources resources);
   public abstract void build (GameWorldController worldController);
   public abstract boolean canBuild(GameWorldController worldController);
   public abstract boolean consume(GameWorldController worldController, Resources resources) ;
}
