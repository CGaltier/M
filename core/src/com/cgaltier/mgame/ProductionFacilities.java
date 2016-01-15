package com.cgaltier.mgame;


import com.badlogic.gdx.utils.Array;



/**
 * Created by Christian on 13/01/2016.
 */
public class ProductionFacilities {
   Array<Facility> facilities;


   public ProductionFacilities(){
      facilities = new Array<Facility> ();

   }
   public void addFacility (Facility facility){
      facilities.add(facility);
   }
   public void produce ( GameWorldController gameWorldController, Resources resources){
      for (Facility facility:facilities){
         if (facility != null)
         facility.produce(gameWorldController, resources);
      }
   }
}
