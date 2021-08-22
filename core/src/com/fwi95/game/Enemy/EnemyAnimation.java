package com.fwi95.game.Enemy;

import com.badlogic.gdx.graphics.Texture;

public class EnemyAnimation {
    Enemy enemy;
    Texture currentEnemyTexture;

    public EnemyAnimation(Enemy enemy){
        this.enemy = enemy;
    }

    public Texture getTexture(){
        return currentEnemyTexture;
    }
}
