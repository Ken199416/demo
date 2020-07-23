package com.example.demo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class WhyException {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        String s = "";
        map.put(1,"0");
        map.put(2,"8");
        map.put(3,"7");
        map.put(4,"6");
        map.put(5,"5");
        map.put(6,"hehe");
        map.put(7,"3");
        map.put(8,"hehe");
        map.put(9,"2");
        map.put(10,"1");
        Set<Integer> integers = map.keySet();
        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            String remove = map.remove(key);
            if(map.containsValue(remove)){
                s=remove+"出现了两次";
                break;
            }
        }
        s= s.equals("")?"null":s;
        System.out.println(s);

    }
}
