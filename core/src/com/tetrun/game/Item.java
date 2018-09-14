package com.tetrun.game.shop.;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.unlucky.resource.ResourceManager;
import com.unlucky.resource.Util;

public class Item {

    // 아이디
    public String name;
    // 렌더링 이름
    public String labelName;
    // 툴팁 렌더링
    public String desc;
    /**
    0 - 스킨
    1 - 장신구
     */

    public int type;

    /**
     * 0 - 일반 (60%)
     * 1 - 희희 (25%)
     * 2 - 영웅 (10%)
     * 3 - 전설 (5%)
     */
    public int rarity;


    public int sell = 0;


    // 착용여부
    public boolean equipped = false;

    // 렌더링
    public Image actor;
    public int imgIndex;


    public Item(ResourceManager rm, String name, String desc, int rarity, int imgIndex, int sell) {
        this.name = name;
        this.desc = desc;
        this.rarity = rarity;
        this.imgIndex = imgIndex;
        this.sell = sell;
        type = 0;
        actor = new Image(rm.items20x20[0][imgIndex]);
        labelName = name;
    }


    public Item(ResourceManager rm, String name, String desc, int rarity, int imgIndex, int sell) {
        this.name = name;
        this.desc = desc;
        this.rarity = rarity;
        this.imgIndex = imgIndex;
        this.sell = sell;
        type = 1;
        actor = new Image(rm.items20x20[1][imgIndex]);
        labelName = name;
    }

    public Item(ResourceManager rm, String name, String desc, int type, int rarity, int imgIndex, int minLevel, int maxLevel,
                int mhp, int dmg, int acc, int sell) {
        this.name = name;
        this.desc = desc;
        this.type = type;
        this.rarity = rarity;
        this.imgIndex = imgIndex;
        this.sell = sell;
        actor = new Image(rm.items20x20[type][imgIndex]);
        labelName = name;
    }


    public String getFullDesc() {
        String ret = "";
        if (type == 0) {
            ret += "\n\npress ??? to use";
        } else if (type == 1) {
            ret = desc;
            ret = ret.trim();
            return ret;
        }

        public String getDialogName() {
            switch (rarity) {
                case 0:
                    ret = "[COMMON] " + name;
                    break;
                case 1:
                    ret = "[RARE] " + name;
                    break;
                case 2:
                    ret = "[EPIC] " + name;
                    break;
                case 3:
                    ret = "[LEGENDARY] " + name;
                    break;
            }
            return ret;
        }

    }
}
