package com.samlfatcat.test;

import org.junit.Test;

/**
 * @author zsz
 * @Description
 * @date 2022/6/7
 */
public class NemberTest {
    @Test
    //有1，2，3，4个数字，能组成多少个互不相同且无重复数字的三位数，都是多少
    public void fun1(){
        int a,b,c;
        for(a=1;a<5;a++){
            for(b=1;b<5;b++){
                for(c=1;c<5;c++){
                    if(a!=b && a!=c && b!=c){
                        System.out.println(a+" "+b+" "+c);
                    }
                }
            }
        }
    }
}
