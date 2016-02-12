package com.cgaltier.mgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Disposable;
import com.cgaltier.mgame.Utils.Global;

/**
 * Created by Christian on 12/02/2016.
 */
public class MyShader implements Disposable{
   private String fileName;
   ShaderProgram program;
   private float resolutionUniform []=new float[3];
   private boolean hasResolutionUniform;
   int locationUniform;

   public MyShader (String fileName ){
      if (fileName==null){
         program = null;
         this.fileName= null;
         hasResolutionUniform =false;
      }
      else {
         program = new ShaderProgram(Gdx.files.internal("shaders/" + fileName + ".vert"), Gdx.files.internal("shaders/" + fileName + ".frag"));
         if ( !program.isCompiled() ) {
            Global.logger.info(program.getLog());
            throw new IllegalArgumentException("Shader " + fileName + "not compiled");
         }
         this.fileName = fileName;
         if (program !=null){
            locationUniform = program.fetchUniformLocation("resolution3",false);
            if (locationUniform !=-1)
               hasResolutionUniform =true;
         }
         if (hasResolutionUniform){
            resolutionUniform[0]=Gdx.graphics.getWidth();
            resolutionUniform[1]=Gdx.graphics.getHeight();
            resolutionUniform[2]=0;
         }
      }
   }


   @Override
   public void dispose() {
      if (program!=null)
         program.dispose();
   }

   public ShaderProgram getProgram() {
      return program;
   }

   public void setResolutionUniform() {
      if (hasResolutionUniform)
         program.setUniform3fv(locationUniform,resolutionUniform,0,3);
   }
}
