package com.www.http.kits.exercise.security.draft;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LIstTest {
    @Test
    public void test01(){
        ArrayList<String> list = new ArrayList<>();
        list.add(0, "Monday");
        list.add(1, "Monday");
        list.add(2, "Tuesday");
        list.add(3, "Tuesday");
        list.add(4, "Wednesday");
        Set<String> set = new HashSet<>();

        ArrayList<String> listTransition = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(!listTransition.contains(list.get(i))){
                listTransition.add(list.get(i));
            }
        }
        System.out.println(listTransition);
    }
}
