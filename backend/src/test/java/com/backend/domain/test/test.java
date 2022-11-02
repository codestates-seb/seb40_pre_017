package com.backend.domain.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Test
    @DisplayName("아래 쿼리를 파싱한다.")
    public void SearchQueryParsing(){
        String q = "sadf [java] sadf [Python]";



        Pattern p = Pattern.compile("\\[([ㄱ-ㅎ가-핳a-zA-Z\\d]+)\\]");

        // 검색할 문자열 패턴 : 숫자
        Matcher m = p.matcher(q);			// 문자열 설정

        List<String> tagName = new ArrayList<>();

        while (m.find()) {
            tagName.add(m.group().substring(1,m.group().length()-1));
        }

        String noTagOneSpaceString = m.replaceAll("").replaceAll("\\s+", " ");


        System.out.println(tagName);
        System.out.println(noTagOneSpaceString);

    }

    @Test
    public void test(){
        ArrayList<Object> objects = new ArrayList<>();
        objects.get(0);
    }

    }






