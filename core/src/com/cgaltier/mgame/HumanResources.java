package com.cgaltier.mgame;

/**
 * Created by Christian on 07/01/2016.
 */
public class HumanResources {
   private int cryo;
   private int maintenance;
   private int production;
   private int emergency;
   private int idle;
   private int mission;
   public HumanResources(int cryo, int baseShipCrew){
      this.cryo = cryo;
      this.idle = baseShipCrew;
   }

   public int getMaintenance() {
      return maintenance;
   }

   public void setMaintenance(int maintenance) {
      this.maintenance = maintenance;
   }

   public int getProduction() {
      return production;
   }

   public void setProduction(int production) {
      this.production = production;
   }

   public int getCryo() {
      return cryo;
   }

   public void setCryo(int cryo) {
      this.cryo = cryo;
   }

   public int getEmergency() {
      return emergency;
   }

   public void setEmergency(int emergency) {
      this.emergency = emergency;
   }

   public int getIdle() {
      return idle;
   }

   public void setIdle(int idle) {
      this.idle = idle;
   }

   public int getMission() {
      return mission;
   }

   public void setMission(int mission) {
      this.mission = mission;
   }
}
