package com.devtest.test02;

import com.devtest.test01.mathfcns.MathFcns;
import com.devtest.test01.stringfcns.StringFcns;

public class App {
    public static void main(String[] args) {
        System.out.println("2+3=" + MathFcns.add(2, 3));
        System.out.println("reverse 'hello' = " + StringFcns.reverse("hello"));
    }
}
