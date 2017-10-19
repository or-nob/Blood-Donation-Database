package sample;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by user on 12/16/2015.
 */

public class DateandTime {
    String A;
    String B;
    public DateandTime(String A, String B){
        this.A = A;
        this.B = B;
    }
    public boolean diff() {
        String[] tok1 = A.split("/");
        String[] tok2 = B.split("/");
        String[] reversed = new String[tok1.length];
        for (int i=0; i<tok1.length; i++) {
            reversed[i] = tok1[tok1.length - 1 - i];
        }
        int a = Integer.valueOf(reversed[0]);
        int b = Integer.valueOf(tok2[0]);
        System.out.println(a+" "+b);
        int c, d = 0;
        if(a>=b){ c = a-b;}
        else{ c = (a+30)-b; d = 1;}
        int e = Integer.valueOf(reversed[1]);
        int f = Integer.valueOf(tok2[1]);
        System.out.println(a+" "+b);
        f+=d;
        int g, k = 0;
        if(e>=f) {g = e - f;}
        else {g = (e+12) - f; k++;}
        int s = Integer.valueOf(reversed[2]);
        int t = Integer.valueOf(tok2[2]);
        System.out.println(s+" "+t);
        t+=k;
        if(s-t>=1)return true;
        else{
            if(g>=4)return true;
            else return false;
        }
    }
}


