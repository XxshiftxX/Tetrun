package com.tetrun.game.shop;

package com.tetrun.game.shop;

import com.badlogic.gdx.utils.Array;
import com.unlucky.resource.ResourceManager;


public class Shop {

    // 아이템이 타입에 따라 분리됨
    // 0 - 스킨, 1 - 장신구
    public Array<Array<ShopItem>> items;

    public Shop(ResourceManager rm) {
        items = new Array<Array<ShopItem>>();
        for (int i = 0; i < 2; i++) items.add(new Array<ShopItem>());

        // 아이템 체우기
        for (int rarity = 0; rarity < rm.shopItems.size; rarity++) {
            for (int i = 0; i < rm.shopItems.get(rarity).size; i++) {
                ShopItem item = rm.shopItems.get(rarity).get(i);
                ShopItem shopItem;
                //스킨
                if (item.type == 0) {
                    shopItem = new ShopItem(rm, item.name, item.desc, item.rarity, item.imgIndex, item.sell, item.price);
                    items.get(0).add(shopItem);
                }
                // 장신구
                else if (item.type == 1) {
                    shopItem = new ShopItem(rm, item.name, item.desc, item.rarity, item.imgIndex, item.sell, item.price);
                    items.get(1).add(shopItem);
                }
            }
        }
    }
}
