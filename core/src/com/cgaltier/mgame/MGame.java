package com.cgaltier.mgame;


import com.badlogic.gdx.Game;

public class MGame extends Game {//InputAdapter implements ApplicationListener {



   public com.cgaltier.mgame.Screens.GameScreen gameScreen;
   public com.cgaltier.mgame.Screens.MainMenuScreen mainMenuScreen;

   @Override
	public void create () {
      gameScreen = new com.cgaltier.mgame.Screens.GameScreen(this);
      mainMenuScreen = new com.cgaltier.mgame.Screens.MainMenuScreen(this);
      setScreen(mainMenuScreen);

   }



}
