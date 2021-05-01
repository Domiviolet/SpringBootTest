package com.www.http.kits.exercise.security.draft;

import java.util.ArrayList;
import java.util.Collections;

public class Star extends Person {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3 );
        list.add(4);
        list.add(5);
        System.out.println(list);

        Collections.rotate(list,-3);

//        Collections.shuffle(list)
        System.out.println(list);

//        List m = Arrays.asList("one two three four five six seven".split(" "));
//        System.out.println(Collections.max(m));
//
//        List n = Arrays.asList("我 是 复制过来的哈".split(" "));
//        System.out.println(n);
//        Collections.copy(m,n);
//        System.out.println(m);
       String str = "abcddd " ;
        String substring = str.substring(1, 4);
        System.out.println(substring);


    }

}


