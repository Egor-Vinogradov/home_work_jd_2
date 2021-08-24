package by.it_academy.jd2.site_vote.service;

import java.util.*;

public class Utils {

    public static Map<String, Integer> sortedMap(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new ArrayList(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                /*
                В следующей строке отнимаем второе значение от первого, чтобы была сортировка
                в обратном порядке. Реверс
                 */
                return b.getValue() - a.getValue();
            }
        });

        /*
        Записываем значения в LinkedHashMap, чтобы соблюсти порядок заполнения
         */
        Map<String, Integer> mapNew = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry: list) {
            mapNew.put(entry.getKey(), entry.getValue());
        }

        return mapNew;
    }
}
