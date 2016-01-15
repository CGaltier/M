package com.cgaltier.mgame;

import com.cgaltier.mgame.Utils.Global;


/**
 * Created by Christian on 15/01/2016.
 */
public class Good {
   public enum eNaturalResource {NITROGEN, OXYGEN, HYDROGEN, GOLD, ALUMINIUM, TITANIUM, SILICON, RARE_ELEMENTS, CARBON }
   public enum eProducedGood {FOOD, LIFE_MODULE, PRODUCTION_MODULE, ELECTRONICS, GRAPHEN, AIR, WATER, JUMP_POINT_MODULE}
   public enum eEnergy {ENERGY}
   public String name;
   public int stock;


   public Good (String name){
      this.name = name;
      stock=0;
   }
   public int getStock(){return stock;}
   public void setStock(int value) {stock=value;}
   public String getName(){return name;}
   public void addValue (int value){stock+=value;}
   public void removeValue(int value) {
      stock-=value;
      if (stock <0){
         Global.logger.info(name+" : OOPS ! Negative stock here !");
         setStock(0);
      }
   }
   public static String Name(eEnergy type){
      switch (type){
         case ENERGY:
            return "Energy";
      }
      return "unknown";
   }
   public static String Name(eProducedGood type){
      switch (type){
         case FOOD:
            return "Food";
         case LIFE_MODULE :
            return "Life module";
         case PRODUCTION_MODULE :
            return "Production module";
         case ELECTRONICS :
            return "Electronics";
         case GRAPHEN :
            return "Graphen";
         case AIR :
            return "Air";
         case WATER :
            return "Water";
         case JUMP_POINT_MODULE :
            return "Jump point module";
      }
      return "unknown";
   }
   public static String Name(eNaturalResource type) {
      switch (type){
         case NITROGEN :
            return "Nitrogen";
         case OXYGEN :
            return "Oxygen";
         case HYDROGEN :
            return "Hydrogen";
         case GOLD :
            return "Gold";
         case ALUMINIUM :
            return "Aluminium";
         case TITANIUM :
            return "Titanium";
         case SILICON :
            return "Silicon";
         case RARE_ELEMENTS :
            return "Rare Elements";
         case CARBON :
            return "Carbon";
      }
      return "unknown";
   }
}
