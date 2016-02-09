package com.cgaltier.mgame;
import com.cgaltier.mgame.GoodType;

public enum Good {
   //bulk extracted by mining modules, bulk processed into Gas and minerals
   Bulk ("Bulk",GoodType.BULK),
   Gas ("Gas",GoodType.BULK),
   Minerals ("Minerals", GoodType.BULK),
   //Gas produced by processing Gas bulk
   Nitrogen("Nitrogen",GoodType.NATURAL),
   Oxygen("Nitrogen",GoodType.NATURAL),
   Hydrogen ("Hydrogen",GoodType.NATURAL),
   //Minerals produced by processing mineral bulk
   Gold ("Hydrogen",GoodType.NATURAL),
   Aluminium ("Aluminium",GoodType.NATURAL),
   Titanium ("Titanium",GoodType.NATURAL),
   Silicon ("Silicon",GoodType.NATURAL),
   Carbon("Carbon",GoodType.NATURAL),
   Rare_Elements("Rare elements",GoodType.NATURAL),
   //Base module produced
   Base_module("Base module", GoodType.PRODUCED),
   //Specialised modules by transforming base module
   Life_module("Life module", GoodType.PRODUCED),
   Production_module("Production module", GoodType.PRODUCED),
   Jump_point_module("Jump point module", GoodType.PRODUCED),
   //advanced materials
   Electronics("Electronics", GoodType.PRODUCED),
   Graphen("Graphen", GoodType.PRODUCED),
   //Life sustainability
   Food("Food", GoodType.PRODUCED),
   Air("Air", GoodType.PRODUCED),
   Water("Water", GoodType.PRODUCED),
   BioWaste("Biowaste",GoodType.PRODUCED),
   CarbonDioxyde("CarbonDioxyde",GoodType.PRODUCED),
   //Energy
   Energy("Energy",GoodType.ENERGY);





   public final String name;
   public final GoodType type;
   int bit;

   Good(String name, GoodType type){
      this.name=name;
      this.type=type;
      bit = 1<<this.ordinal();
   }
   public boolean isInSet(int flag){
      return (flag & this.bit)== this.bit;
   }
   public static int getFlag(Good... types){
      int result = 0;
      for (Good good:types){
         result |= good.bit;
      }
      return result;
   }

};