package com.cgaltier.mgame;

/**
 * Created by Christian on 19/01/2016.
 */
public enum FacilityType {
   ENERGY, EXTRACTION, PRODUCTION;
   int bit;

   FacilityType(){
      bit = 1<<this.ordinal();
   }
   public boolean isInSet(int flag){
      return (flag & this.bit)== this.bit;
   }
   public static int getFlag(FacilityType... types){
      int result = 0;
      for (FacilityType factoryType:types){
         result |= factoryType.bit;
      }
      return result;
   }
}
