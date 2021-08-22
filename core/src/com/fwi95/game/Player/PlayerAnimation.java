package com.fwi95.game.Player;

import com.badlogic.gdx.graphics.Texture;

public class PlayerAnimation {
    Player player;
    Texture currentPlayerTexture;

    public PlayerAnimation(Player player){
        this.player = player;

        
    }

    public Texture getTexture(){
        return currentPlayerTexture;
    }
}
