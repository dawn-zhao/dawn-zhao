package com.dawn.zhao.lambda;

import com.alibaba.fastjson.JSON;

import static com.dawn.zhao.lambda.ATK_RANGE.NONE;

public class Hero {
    private int id;
    private String name;
    private ATK_RANGE atk_range=NONE;
    private int hp;
    private int mp;
    private int physical_atk;//物理攻击值
    private int magic_atk;//魔法攻击值
    private int physical_defense;//物理防守值

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public ATK_RANGE getAtk_range() {
        return atk_range;
    }

    public void setAtk_range(ATK_RANGE atk_range) {
        this.atk_range = atk_range;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getPhysical_atk() {
        return physical_atk;
    }

    public void setPhysical_atk(int physical_atk) {
        this.physical_atk = physical_atk;
    }

    public int getMagic_atk() {
        return magic_atk;
    }

    public void setMagic_atk(int magic_atk) {
        this.magic_atk = magic_atk;
    }

    public int getPhysical_defense() {
        return physical_defense;
    }

    public void setPhysical_defense(int physical_defense) {
        this.physical_defense = physical_defense;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
