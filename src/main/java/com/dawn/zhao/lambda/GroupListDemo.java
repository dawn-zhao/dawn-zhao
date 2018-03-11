package com.dawn.zhao.lambda;

import com.alibaba.fastjson.JSON;
import jodd.io.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * List普通筛选,及lambda表达式筛选
 *
 * List循环分组及lambda表达式分组
 */
public class GroupListDemo {
    public static void main(String[] args) {
        try {
            String heroJson = FileUtil.readString(Thread.currentThread().getContextClassLoader().getResource("").getPath()+"hero.json");
            List<Hero> heroList = JSON.parseArray(heroJson, Hero.class);
            //筛选出近战英雄
            List<Hero> shortRangeHeros = new ArrayList<>();
            for (Hero hero : heroList) {
                if (ATK_RANGE.SHORT_RANGE.equals(hero.getAtk_range())) {
                    System.out.println(hero);
                    shortRangeHeros.add(hero);
                }
            }

            System.out.println("输出 List");
            System.out.println(JSON.toJSONString(shortRangeHeros));
            System.out.println("Stream");
            //Stream
            List<Hero> shortRangeHerosStream = heroList.stream().filter(hero->ATK_RANGE.SHORT_RANGE.equals(hero.getAtk_range())).collect(Collectors.toList());
            System.out.println("输出 List");
            System.out.println(JSON.toJSONString(shortRangeHerosStream));

            // 普通分组
            Map<ATK_RANGE, List<Hero>> map = new HashMap<>();
            map.put(ATK_RANGE.SHORT_RANGE, new ArrayList<>());
            map.put(ATK_RANGE.LONG_RANGE, new ArrayList<>());
            map.put(ATK_RANGE.NONE, new ArrayList<>());
            for (Hero hero : heroList) {
                if (ATK_RANGE.SHORT_RANGE.equals(hero.getAtk_range())) {
                    map.get(ATK_RANGE.SHORT_RANGE).add(hero);
                    continue;
                }
                if (ATK_RANGE.LONG_RANGE.equals(hero.getAtk_range())) {
                    map.get(ATK_RANGE.LONG_RANGE).add(hero);
                    continue;
                }
                if (ATK_RANGE.NONE.equals(hero.getAtk_range())) {
                    map.get(ATK_RANGE.NONE).add(hero);
                    continue;
                }
            }
            System.out.println(JSON.toJSONString(map));

            //lambda表达式分组
            Map<ATK_RANGE, List<Hero>> mapStream =heroList.stream().collect(Collectors.groupingBy(Hero::getAtk_range));
            System.out.println(JSON.toJSONString(mapStream));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
