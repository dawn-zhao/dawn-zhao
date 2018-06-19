package com.dawn.zhao.utils;

import java.util.*;

public class MapSortUtil {

    /**
     * 排序，按照value降序
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    private <K, V extends Comparable<? super V>> Map<K, V> sortByValueDescending(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, (o1, o2) -> {
            int compare = new Integer((String)o1.getValue()).compareTo(new Integer((String)o2.getValue()));
            return -compare;
        });

        Map<K, V> result = new LinkedHashMap<>();
        int i = 0;
        for (Map.Entry<K, V> entry : list) {
            if(i++ < 20){
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    /**
     * 按照key升序
     * @param map
     * @param count
     * @param <K>
     * @param <V>
     * @return
     */
    private <K, V extends Comparable<? super V>> List<K> sortByKeyAscending(Map<K, V> map, int count) {
        List<K> list = new LinkedList<>(map.keySet());
        Collections.sort(list, Comparator.comparing(o -> new Integer((String) o)));
        return list.subList(0, count);
    }
}
