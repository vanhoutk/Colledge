package com.collidge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Kris on 24-Jan-15. Last modified 24-Feb-15.
 *
 * Will contain all of the drawing and display involved with the InventoryState
 *
 * TODO: Comment code
 * TODO: Clean up code
 */
public class InventoryState extends GameState
{
    private String[] itemNames;

    Player player;
    SpriteBatch batch;
    Texture texture;
    Sprite itemSquare, textbox, textbox2, backButton, greenSquare, greenCircle;
    String[] itemInfoText, itemNameText, itemEquipText;
    Sprite[] itemImages, bitNumbers;
    int equipNum, itemNum, selectedItem;

    private BitmapFont infoFont;

    float sqSide = screenWidth/7;
    float spacing = (screenWidth - 5 * sqSide)/6;

    InventoryState(GameStateManager gsm, Player plr)
    {
        super(gsm);
        player = plr;

        texture = new Texture("inventory_slot_background.png");
        itemSquare = new Sprite(texture);
        textbox = new Sprite(texture);
        textbox2 = new Sprite(new Texture("textbox_background_2.png"));
        backButton = new Sprite(new Texture("back_button.png"));
        greenCircle = new Sprite(new Texture("greenCircleBlack.png"));
        greenSquare = new Sprite(new Texture("greenSquareBlack.jpg"));
        batch = new SpriteBatch();
        infoFont = new BitmapFont();

        bitNumbers = new Sprite[10];
        bitNumbers[0] = new Sprite(new Texture("zero.png"));
        bitNumbers[1] = new Sprite(new Texture("one.png"));
        bitNumbers[2] = new Sprite(new Texture("two.png"));
        bitNumbers[3] = new Sprite(new Texture("three.png"));
        bitNumbers[4] = new Sprite(new Texture("four.png"));
        bitNumbers[5] = new Sprite(new Texture("five.png"));
        bitNumbers[6] = new Sprite(new Texture("six.png"));
        bitNumbers[7] = new Sprite(new Texture("seven.png"));
        bitNumbers[8] = new Sprite(new Texture("eight.png"));
        bitNumbers[9] = new Sprite(new Texture("nine.png"));

        initialize();
    }


    //initialize is the same as constructor but sometimes you want to reset an object without rebuilding it.
    @Override
    public void initialize()
    {
        itemInfoText = new String[11];
        itemNameText = new String[11];
        itemEquipText = new String[11];
        itemNameText[0] = "Inventory";
        itemInfoText[0] = "Touch any of the items above to display information.";
        itemEquipText[0] = " ";

        /**
         * Note: below code is needed to output debug messages to logcat
         */
        //Gdx.app.setLogLevel(Application.LOG_DEBUG);
        selectedItem = 0;

        itemNames = new String[10];
        itemImages = new Sprite[10];
        equipNum = player.getEquipList().length;
        itemNum = player.getItemList().length;

        for(int i = 0; i < equipNum; i++)
        {
            itemNames[i] = player.getEquipList()[i];
            //texture = new Texture(player.items.getItemImage(itemNames[i]));
            //Gdx.app.debug("Inventory State", "Message number 1");
            //Gdx.app.debug("ItemImageName", player.items.getItemImage(itemNames[i]));

            itemImages[i] = new Sprite(new Texture(player.items.getItemImage(itemNames[i])));
            itemNameText[i + 1] = itemNames[i];
            itemInfoText[i + 1] = player.items.getItemText(itemNames[i]);
            if(player.items.getItemType(itemNames[i]).equals("Weapon"))
            {
                if(player.equippedWeapon.equals(itemNames[i]))
                {
                    itemEquipText[i + 1] = "UnEquip";
                }
                else
                {
                    itemEquipText[i + 1] = "Equip";
                }
            }
            else
            {
                if(player.equippedArmour.equals(itemNames[i]))
                {
                    itemEquipText[i + 1] = "UnEquip";
                }
                else
                {
                    itemEquipText[i + 1] = "Equip";
                }
            }
        }
        for(int i = 0; i < itemNum; i++)
        {
            itemNames[equipNum + i] = player.getItemList()[i];
            //texture = new Texture(player.items.getItemImage(itemNames[equipNum + i]));

            itemImages[equipNum + i] = new Sprite(new Texture(player.items.getItemImage(itemNames[equipNum + i])));
            itemNameText[equipNum + i + 1] = itemNames[equipNum + i];
            itemInfoText[equipNum + i + 1] = player.items.getItemText(itemNames[equipNum + i]);

            /**
             * Kris -- Commented out to be replaced with usable combat items code
             */
            //itemEquipText[equipNum + i + 1] = "Cannot use outside of combat";
            itemEquipText[equipNum + i + 1] = "Use item";
        }
    }

    //update will update all game logic before drawing
    @Override
    public void update()
    {

    }

    //after updating has occured in that single frame. Both what or what hasn't been changed has to be drawn/redrawn
    @Override
    public void draw()
    {
        Gdx.gl.glClearColor(150/255f, 106/255f, 73/255f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        itemSquare.setSize(sqSide, sqSide);
        itemSquare.setPosition(spacing, screenHeight - (sqSide + spacing/2));
        itemSquare.draw(batch);

        itemSquare.setPosition((2 * spacing) + sqSide, screenHeight - (sqSide + spacing/2));
        itemSquare.draw(batch);

        itemSquare.setPosition((3 * spacing) + 2 * sqSide, screenHeight - (sqSide + spacing/2));
        itemSquare.draw(batch);

        itemSquare.setPosition((4 * spacing) + 3 * sqSide, screenHeight - (sqSide + spacing/2));
        itemSquare.draw(batch);

        itemSquare.setPosition((5 * spacing) + 4 * sqSide, screenHeight - (sqSide + spacing/2));
        itemSquare.draw(batch);

        itemSquare.setPosition(spacing, screenHeight - 2 * (sqSide + spacing/2));
        itemSquare.draw(batch);

        //coffeeCup.setSize(8 * sqSide/10, 8 * sqSide/10);
        //coffeeCup.setPosition(spacing + sqSide / 10, screenHeight - ((192 * sqSide / 100) + spacing));
        //coffeeCup.draw(batch);

        itemSquare.setPosition((2 * spacing) + sqSide, screenHeight - 2 * (sqSide + spacing/2));
        itemSquare.draw(batch);

        itemSquare.setPosition((3 * spacing) + 2 * sqSide, screenHeight - 2 * (sqSide + spacing/2));
        itemSquare.draw(batch);

        itemSquare.setPosition((4 * spacing) + 3 * sqSide, screenHeight - 2 * (sqSide + spacing/2));
        itemSquare.draw(batch);

        itemSquare.setPosition((5 * spacing) + 4 * sqSide, screenHeight - 2 * (sqSide + spacing/2));
        itemSquare.draw(batch);

        //itemImages[0].setSize(8 * sqSide/10, 8 * sqSide/10);
        //itemImages[0].setPosition(spacing + sqSide/10, screenHeight - ((92 * sqSide/100) + spacing/2));
        //itemImages[0].draw(batch);

        //itemImages[1].setSize(8 * sqSide/10, 8 * sqSide/10);
        //itemImages[1].setPosition(spacing + sqSide / 10, screenHeight - ((192 * sqSide / 100) + spacing));
        //itemImages[1].draw(batch);

        for(int i = 0; i < equipNum + itemNum; i++)
        {
            int x = i / 2;
            int y = i % 2;

            itemImages[i].setSize(8 * sqSide/10, 8 * sqSide/10);
            itemImages[i].setPosition((spacing + sqSide/10) + x * (spacing + sqSide), screenHeight - ((((y * 100) + 90) * sqSide/100) + (y + 1) * spacing / 2));
            itemImages[i].draw(batch);

            if(player.items.getItemType(itemNames[i]).equals("Energy") || player.items.getItemType(itemNames[i]).equals("Health"))
            {
                int quantity = player.items.getItemQuantity(itemNames[i]);
                bitNumbers[quantity/10].setSize(10 * sqSide/75, 10 * sqSide/75);
                bitNumbers[quantity/10].setPosition((spacing + sqSide/10) + x * (spacing + sqSide), screenHeight - ((((y * 10) + 9) * sqSide/10) + (y + 1) * spacing / 2));
                bitNumbers[quantity/10].draw(batch);

                bitNumbers[quantity%10].setSize(10 * sqSide/75, 10 * sqSide/75);
                bitNumbers[quantity%10].setPosition((spacing + sqSide/10) + x * (spacing + sqSide) + 3 * bitNumbers[quantity/10].getWidth() / 4, screenHeight - ((((y * 10) + 9) * sqSide/10) + (y + 1) * spacing / 2));
                bitNumbers[quantity%10].draw(batch);
            }
            else if(player.items.getItemType(itemNames[i]).equals("Weapon"))
            {
                if(itemNames[i].equals(player.equippedWeapon))
                {
                    greenSquare.setSize(12 * sqSide / 75, 12 * sqSide / 75);
                    greenSquare.setPosition((spacing + sqSide/10) + x * (spacing + sqSide), screenHeight - ((((y * 10) + 9) * sqSide/10) + (y + 1) * spacing / 2));
                    greenSquare.draw(batch);
                    itemEquipText[i + 1] = "UnEquip";
                }
                else
                {
                    itemEquipText[i + 1] = "Equip";
                }
            }
            else if(player.items.getItemType(itemNames[i]).equals("Armour"))
            {
                if(itemNames[i].equals(player.equippedArmour))
                {
                    greenCircle.setSize(12 * sqSide / 75, 12 * sqSide / 75);
                    greenCircle.setPosition((spacing + sqSide/10) + x * (spacing + sqSide), screenHeight - ((((y * 10) + 9) * sqSide/10) + (y + 1) * spacing / 2));
                    greenCircle.draw(batch);
                    itemEquipText[i + 1] = "UnEquip";
                }
                else
                {
                    itemEquipText[i + 1] = "Equip";
                }
            }
        }



        //textbox.setSize(screenWidth - (2 * (spacing+sqSide)), sqSide);
        textbox.setSize(screenWidth - 2 * spacing, sqSide);
        textbox.setPosition(spacing, spacing/2);
        textbox.draw(batch);

        //textbox.setSize(screenWidth - (2 * (spacing+sqSide)), sqSide);
        textbox.setSize(sqSide, sqSide);
        textbox.setPosition(spacing, spacing/2);
        textbox.draw(batch);

        textbox2.setSize(4  * sqSide / 3, sqSide);
        textbox2.setPosition(screenWidth - (spacing + (4 * sqSide/3)), spacing/2);
        textbox2.draw(batch);

        backButton.setSize(sqSide, sqSide);
        backButton.setPosition(spacing, spacing/2);
        backButton.draw(batch);

        infoFont.setColor(Color.BLACK);
        infoFont.setScale(screenWidth / 400f, screenHeight / 400f);

        infoFont.draw(batch, itemNameText[selectedItem], (3 * spacing / 2) + sqSide, sqSide);
        infoFont.drawWrapped(batch, itemInfoText[selectedItem], (3 * spacing / 2) + sqSide, (sqSide - infoFont.getLineHeight()), screenWidth - (5 * spacing/2 + 7 * sqSide / 3));

        if(itemEquipText[selectedItem].equals("Equip"))
        {
            infoFont.setScale(screenWidth / 300f, screenHeight / 300f);
            infoFont.draw(batch, "Equip", screenWidth - (4 * spacing / 3 + sqSide), sqSide - infoFont.getLineHeight());
        }
        else if(itemEquipText[selectedItem].equals("UnEquip"))
        {
            infoFont.setScale(screenWidth / 350f, screenHeight / 350f);
            infoFont.draw(batch, "UnEquip", screenWidth - (5 * spacing / 3 + sqSide), sqSide - 9 * infoFont.getLineHeight() / 8);
        }
        else
        {
            /**
             * Kris -- Commented out to be replaced with usable combat items
             */
            //infoFont.drawWrapped(batch, itemEquipText[selectedItem], screenWidth - (7 * spacing / 4 + sqSide), sqSide, 7 * sqSide / 6, BitmapFont.HAlignment.CENTER);
            infoFont.setScale(screenWidth / 300f, screenHeight / 300f);
            infoFont.drawWrapped(batch, itemEquipText[selectedItem], screenWidth - (6 * spacing / 4 + sqSide), sqSide - infoFont.getLineHeight()/2, sqSide, BitmapFont.HAlignment.CENTER);
        }


        batch.end();
    }

    /**
     * 3 touch events that you can handle inside your own class
     * first touchDown(int,int,pointer,button) is from the input handler, the second uses floats and is in the gesture listener(possibly more accurate than ints)
     * Use whichever you want, but only use one, and return false on the other
     */

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchDown(float x,float y, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return false;
    }


    /**
     *  Note: Y values are measured from the top of the screen for tap
     */
    @Override
    public boolean tap(float x, float y, int count, int button)
    {
        if(x > spacing && x < spacing + sqSide)
        {
            if(y > (screenHeight - (sqSide + spacing/2)) && y < (screenHeight - spacing/2))
            {
                gsm.closeInventory();
            }
            else if(y > (spacing/2) && y < (sqSide + spacing/2) && (equipNum + itemNum) >= 1)
            {
                selectedItem = 1;
            }
            else if(y > (sqSide + spacing) && y < (2 * sqSide + spacing) && (equipNum + itemNum) >= 2)
            {
                selectedItem = 2;
            }
        }
        else if(x > (2 * spacing + sqSide) && x < 2 * (spacing + sqSide))
        {
            if(y > (spacing/2) && y < (sqSide + spacing/2) && (equipNum + itemNum) >= 3)
            {
                selectedItem = 3;
            }
            else if(y > (sqSide + spacing) && y < (2 * sqSide + spacing) && (equipNum + itemNum) >= 4)
            {
                selectedItem = 4;
            }
        }
        else if(x > (3 * spacing + 2 * sqSide) && x < 3 * (spacing + sqSide))
        {
            if(y > (spacing/2) && y < (sqSide + spacing/2) && (equipNum + itemNum) >= 5)
            {
                selectedItem = 5;
            }
            else if(y > (sqSide + spacing) && y < (2 * sqSide + spacing) && (equipNum + itemNum) >= 6)
            {
                selectedItem = 6;
            }
        }
        else if(x > (4 * spacing + 3 * sqSide) && x < 4 * (spacing + sqSide))
        {
            if(y > (spacing/2) && y < (sqSide + spacing/2) && (equipNum + itemNum) >= 7)
            {
                selectedItem = 7;
            }
            else if(y > (sqSide + spacing) && y < (2 * sqSide + spacing) && (equipNum + itemNum) >= 8)
            {
                selectedItem = 8;
            }
        }
        else if(x > (5 * spacing + 4 * sqSide) && x < 5 * (spacing + sqSide))
        {
            if(y > (spacing/2) && y < (sqSide + spacing/2) && (equipNum + itemNum) >= 9)
            {
                selectedItem = 9;
            }
            else if(y > (sqSide + spacing) && y < (2 * sqSide + spacing) && (equipNum + itemNum) >= 10)
            {
                selectedItem = 10;
            }
        }
        if(x > (screenWidth - (spacing + 4 * sqSide / 3)) && x < (screenWidth - spacing))
        {
            if(y > (screenHeight - (sqSide + spacing/2)) && y < (screenHeight - spacing/2))
            {
                if(itemEquipText[selectedItem].equals("Equip"))
                {
                    player.equipItem(itemNames[selectedItem - 1]);
                }
                else if(itemEquipText[selectedItem].equals("UnEquip"))
                {
                    player.unequipItem(itemNames[selectedItem - 1]);
                }
                else if(itemEquipText[selectedItem].equals("Use item"))
                {
                    player.useItem(itemNames[selectedItem - 1]);
                    if(player.items.getItemQuantity(itemNames[selectedItem-1]) == 0)
                    {
                        initialize();
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean longPress(float x, float y)
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
