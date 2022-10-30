package com.backend.domain.test;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class test {
    @Test
    public void test1(){

        List<Long> list = new ArrayList<>();
            for (long i = 0; i < 10000000; i++) {
                 list.add(i);
            }
           long t = System.currentTimeMillis();



        System.out.println("실행 시간: " + (System.currentTimeMillis() - t) + " ms");


        }

    @Test
    public void test2(){
        List<Long> list = new ArrayList<>();
        for (long i = 0; i < 10000000; i++) {
            list.add(i);
        }
        long t = System.currentTimeMillis();

        long sum = 0;
        for (Long aLong : list) {
            sum+=aLong;
        }
        System.out.println("sum = " + sum);

        System.out.println("실행 시간: " + (System.currentTimeMillis() - t) + " ms") ;
    }

    }






