package com.cgaltier.mgame;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Christian on 13/01/2016.
 */
public class Resources {
   public HashMap<String, Good> resources;

   public void NaturalResources(){
      resources = new HashMap<String, Good>();
      for (Good.eNaturalResource value: Good.eNaturalResource.values())
      {
         resources.put(Good.Name(value), new Good(Good.Name(value)));
      }
      for (Good.eProducedGood value: Good.eProducedGood.values())
      {
         resources.put(Good.Name(value), new Good(Good.Name(value)));
      }
      resources.put(Good.Name(Good.eEnergy.ENERGY), new Good(Good.Name(Good.eEnergy.ENERGY)));
   }


   public int getStock(Good.eEnergy resource){
      resources.get(Good.Name(resource)).getStock();
      return 0;
   }
   public int getStock(Good.eNaturalResource resource){
      resources.get(Good.Name(resource)).getStock();
      return 0;
   }
   public int getStock(Good.eProducedGood resource){
      resources.get(Good.Name(resource)).getStock();
      return 0;
   }

   public void setStock(Good.eEnergy resource, int value){
      resources.get(Good.Name(resource)).setStock(value);
   }
   public void setStock(Good.eNaturalResource resource, int value){
      resources.get(Good.Name(resource)).setStock(value);
   }
   public void setStock(Good.eProducedGood resource, int value){
      resources.get(Good.Name(resource)).setStock(value);
   }

   public void add(Good.eEnergy resource, int value){
      resources.get(Good.Name(resource)).addValue(value);
   }
   public void add(Good.eNaturalResource resource, int value){
      resources.get(Good.Name(resource)).addValue(value);
   }
   public void add(Good.eProducedGood resource, int value){
      resources.get(Good.Name(resource)).addValue(value);
   }

   public void remove(Good.eEnergy resource, int value){
      resources.get(Good.Name(resource)).removeValue(value);
   }
   public void remove(Good.eProducedGood resource, int value){
      resources.get(Good.Name(resource)).removeValue(value);
   }
   public void remove(Good.eNaturalResource resource, int value){
      resources.get(Good.Name(resource)).removeValue(value);
   }
}
