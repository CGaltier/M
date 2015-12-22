package com.cgaltier.mgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MGame implements ApplicationListener {

	SpriteBatch batch;
	Texture img;
   ResolutionFileResolver fileResolver;
   OrthographicCamera camera;

   @Override
	public void create () {
      Global.getInstance().initResolutionList();
      fileResolver = new ResolutionFileResolver(new InternalFileHandleResolver(), Global.resolutionList[0],Global.resolutionList[1],Global.resolutionList[2]);
		batch = new SpriteBatch();
		img = new Texture(fileResolver.resolve("badlogic.jpg"));
      camera = new OrthographicCamera();

	}

   @Override
   public void resize(int width, int height) {
      camera.setToOrtho(false, Global.WORLD_HEIGHT*width/(float)height,Global.WORLD_HEIGHT);
      batch.setProjectionMatrix(camera.combined);
   }

   @Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
      camera.update();
		batch.begin();
		batch.draw(img, 0, 0, 50.0f,50.0f);
		batch.end();
	}

   @Override
   public void pause() {

   }

   @Override
   public void resume() {

   }

   @Override
   public void dispose() {

   }


}
