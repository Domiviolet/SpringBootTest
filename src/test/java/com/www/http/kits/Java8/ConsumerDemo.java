package com.www.http.kits.Java8;

import java.util.function.Consumer;

/**
 *
 */

public class ConsumerDemo {

    public static void printInfo(String[] strArr, Consumer<String> con1, Consumer<String> con2) {

        for (int i = 0; i < strArr.length; i++) {
            con1.andThen(con2).accept(strArr[i]);
        }

    }

    public static void main(String[] args) {
        String[] strArr = {"迪丽热巴,女", "郑爽,女", "杨紫,女"};
        printInfo(strArr, (message) -> {
            System.out.print("姓名:" + message.split(",")[0] + "。  ");
        }, (message) -> {
            System.out.println("性别:" + message.split(",")[1] + "。");
        });
    }

}
