package com.tetrun.game.shop;

package com.Tetrun.inventory;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.unlucky.resource.ResourceManager;


public class ShopItem extends Item {

    // 아이템 가격
    public int price;

    //스킨
    public ShopItem(ResourceManager rm, String name, String desc, int rarity, int imgIndex, int sell, int price) {
        super(rm, name, desc, rarity, imgIndex, sell,);
        this.price = price;
        actor = new Image(rm.shopitems[0][imgIndex]);
    }

    //장신구
    public ShopItem(ResourceManager rm, String name, String desc, int type, int sell, int price) {
        super(rm, name, desc, type, sell,);
        this.price = price;
        actor = new Image(rm.shopitems[type - 1][imgIndex]);
    }
}
