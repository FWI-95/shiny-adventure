package com.fwi95.game.Player;

public class Player  {
    PlayerBrain Brain;
    PlayerAnimation Animation;
    public Player(){

    }

    public Texture getTexture(){
        return Animation.getTexture();
    }
    
}
