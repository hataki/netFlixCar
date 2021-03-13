package com.nfc.cloudeureka;

import java.math.BigInteger;

public class Myheader {

    final static int size = 2147483647 ;
    final static String lenth = "2147483647" ;
    public static void main(String[] args) throws InterruptedException {

        Myheader h = new Myheader() ;

        Thread t1  = new Thread(() ->  h.ab(1)) ;
        Thread t2  = new Thread(() ->  h.ab(2)) ;
        Thread t3  = new Thread(() ->  h.ab(3)) ;
        Thread t4  = new Thread(() ->  h.ab(4)) ;

        /**
         * 非公平锁
         *
         */
        t4.start();
        t2.start();
        t3.start();
        t1.start();

//        t1.start();
//        t2.start();
//        t3.start();
//        t4.start();
    }

    public synchronized String ab(int x ){


        long sum = size + size ;
        System.err.println(sum);
        BigInteger bigInteger = new BigInteger(lenth) ;
        bigInteger = bigInteger.add(bigInteger);
        System.err.println(bigInteger);
        System.err.println(x );
        return "ok";
    }

}
