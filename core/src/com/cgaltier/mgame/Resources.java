package com.cgaltier.mgame;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Christian on 13/01/2016.
 */
public class Resources {
   public HashMap<String, GoodStock> resources;

   public Resources(){
      resources = new HashMap<String, GoodStock>();
      for (Good value: Good.values())
      {
         resources.put(value.name, new GoodStock(value));
      }
   }


   public int getStock(Good good){
      resources.get(good.name).getStock();
      return 0;
   }

   public void setStock(Good good, int value){
      resources.get(good.name).setStock(value);
   }

   public void add(Good good, int value){
      resources.get(good.name).addValue(value);
   }

   public void remove(Good good, int value){
      resources.get(good.name).removeValue(value);
   }

   public void add(Resources turnProduction) {
      Iterator iterator = turnProduction.resources.entrySet().iterator();
      while (iterator.hasNext()){
         HashMap.Entry entry = (HashMap.Entry )(iterator.next());
         if (entry !=null)
            add(((GoodStock)entry.getValue()).good,((GoodStock)entry.getValue()).getStock());
      }
   }

}
