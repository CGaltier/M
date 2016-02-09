package com.cgaltier.mgame;

import com.cgaltier.mgame.Utils.Global;

/**
 * Created by Christian on 15/01/2016.
 */
public class GoodStock {

   public Good good;
   public int stock;


   public GoodStock(Good good){
      this.good= good;
      stock=0;
   }
   public int getStock(){return stock;}
   public void setStock(int value) {stock=value;}
   public String getName(){return good.name;}
   public void addValue (int value){stock+=value;}
   public void removeValue(int value) {
      stock-=value;
      if (stock <0){
         Global.logger.info(good.name+" : OOPS ! Negative stock here !");
         setStock(0);
      }
   }
}
