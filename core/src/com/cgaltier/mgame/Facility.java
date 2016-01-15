package com.cgaltier.mgame;

import com.badlogic.gdx.utils.Array;

/**
 * Created by Christian on 14/01/2016.
 */
public abstract class Facility {

   public abstract void produce (GameWorldController worldController, Resources resources);
   public abstract void build (GameWorldController worldController);
}
