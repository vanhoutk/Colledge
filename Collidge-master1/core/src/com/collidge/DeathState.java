package com.collidge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by stroughm on 09/03/15.
 */
public class DeathState extends GameState
{
    Player player;
    SpriteBatch batch;
    String Deathtext[];
    Sprite Skull, textbox;
    private BitmapFont deathfont;


    DeathState(GameStateManager gsm, Player plr)
    {

        super(gsm);
        player = plr;
        batch = new SpriteBatch();

        Skull = new Sprite (new Texture ("skull.png"));
        Skull.setSize(screenWidth/2f, screenHeight/2f);
        Skull.setPosition(screenWidth/4f,screenHeight/3f );

        textbox = new Sprite(new Texture ("textbox_background_2.png"));
        textbox.setSize(screenWidth/3f, screenHeight/3f);

        Deathtext = new String[4];
        Deathtext[0] = "You lost. Go get a Burrito and try again later";
        Deathtext[1] = "choose your option";
        Deathtext[2] = "No, let's try again!";
        Deathtext[3] = "Sounds great!";

        deathfont = new BitmapFont();
        deathfont.setScale(screenWidth / 400f, screenHeight / 400f);



    }
    @Override
    public void initialize(){}

    //update will update all game logic before drawing
    @Override
    public  void update()
    {

    }


    //after updating has occurred in that single frame. Both what or what hasn't been changed has to be drawn/redrawn
    @Override
    public  void draw()
    {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        deathfont.setColor(Color.RED);

        Skull.draw(batch);

        textbox.setPosition(0, 0);
        textbox.draw(batch);

        textbox.setPosition(screenWidth-textbox.getWidth(), 0);
        textbox.draw(batch);

        deathfont.draw(batch, Deathtext[0], Deathtext[0].length()*1.1f*(screenWidth/400f), (7f*screenHeight/8f));
        deathfont.draw(batch, Deathtext[2], Deathtext[2].length()*0.5f*(screenWidth/400f), (3f*screenHeight/16f));
        deathfont.draw(batch, Deathtext[3], 2f*screenWidth/3f + Deathtext[3].length()*1.3f*(screenWidth/400f), (3f*screenHeight/16f));
        batch.end();
    }

    @Override
    public  boolean tap(float x, float y, int count, int button)
    {
        if (x <= textbox.getWidth() && y >textbox.getY())
        {
            endDeathState();
            return true;
        }

        if (x >=screenWidth-textbox.getWidth() && y > textbox.getY())
        {
            Gdx.app.exit();
            return true;
        }
        return false;
    }

    public void endDeathState()
    {
        deathfont.dispose();
        batch.dispose();
        gsm.EndDeathstate(player);
    }


    //3 touch events that you can handle inside your own class
    //first touchDown(int,int,pointer,button) is from the input handler, the second uses floats and is in the gesture listener(possibly more accurate than ints)
    //Use whichever you want, but only use one, and return false on the other

    @Override
    public  boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public  boolean touchDown(float x,float y, int pointer, int button)
    {
        return false;
    }

    @Override
    public  boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public  boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return false;
    }

    @Override
    public  boolean longPress(float x, float y)
    {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button)
    {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY)
    {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance)
    {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2)
    {
        return false;
    }
}
