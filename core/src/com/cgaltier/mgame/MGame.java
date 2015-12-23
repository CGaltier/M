package com.cgaltier.mgame;


import com.badlogic.gdx.Game;
import com.cgaltier.mgame.Screens.GameScreen;
import com.cgaltier.mgame.Screens.MainMenuScreen;
public class MGame extends Game {//InputAdapter implements ApplicationListener {


   public MAssets mAssets;
   public GameScreen gameScreen;
   public MainMenuScreen mainMenuScreen;

   @Override
	public void create () {
      mAssets = new MAssets();
      mAssets.loadAssets();
      gameScreen = new com.cgaltier.mgame.Screens.GameScreen(this);
      mainMenuScreen = new com.cgaltier.mgame.Screens.MainMenuScreen(this);
      setScreen(mainMenuScreen);

   }
   @Override
   public void dispose(){
      gameScreen.dispose();
      mainMenuScreen.dispose();
      mAssets.dispose();
   }


}
