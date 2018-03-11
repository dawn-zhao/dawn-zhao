package com.dawn.zhao.lambda;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 1.8 新增方法
 * Files.lines
 * 按照文件内的内容,每一行解析为一个字符串
 *
 *
 */
public class FilesLinesDemo {
    public static void main(String[] args) {
        try {
            List<Hero> heroes = new ArrayList<>();
            List<String> heroList = Files.lines(Paths.get(Thread.currentThread().getContextClassLoader().getResource("").getPath().substring(1)+"英雄数据"))
                    .filter(data -> !Strings.isNullOrEmpty(data)).collect(Collectors.toList());
            for (int i = 0; i <heroList.size() ; i+=7) {
                Hero hero = new Hero();
                int id = (i / 7) + 1;
                hero.setId(id);
                hero.setName(heroList.get(i));
                hero.setHp(Integer.parseInt(heroList.get(i+1)));
                hero.setMp(Integer.parseInt(heroList.get(i+2)));
                hero.setPhysical_atk(Integer.parseInt(heroList.get(i+3)));
                hero.setMagic_atk(Integer.parseInt(heroList.get(i+4)));
                hero.setPhysical_defense(Integer.parseInt(heroList.get(i+5)));
                heroes.add(hero);
            }
            List<String> atks = Files.lines(Paths.get(Thread.currentThread().getContextClassLoader().getResource("").getPath().substring(1)+"攻击范围"))
                    .filter(data -> !Strings.isNullOrEmpty(data)).collect(Collectors.toList());
            heroes.forEach(hero -> {
                if((hero.getId()* 7-1)<heroes.size()) {
                    boolean fal = atks.get((hero.getId() * 6 - 1)).equals("近战");
                    if (fal) {
                        hero.setAtk_range(ATK_RANGE.SHORT_RANGE);
                    } else {
                        hero.setAtk_range(ATK_RANGE.LONG_RANGE);
                    }
                }
            });
            System.out.println(JSON.toJSONString(atks));
            System.out.println(JSON.toJSONString(heroes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
