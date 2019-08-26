package com.example.cookiemonster;

import java.util.Random;

public class CookieMonster {
    int cookie;
    int eaten;
    int timer;
    Random random=new Random();
    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }



    public CookieMonster() {
        cookie = 0;
        eaten = 0;
        Random random = new Random() ;
        timer = random.nextInt(5)+1;
    }

    public CookieMonster(int cookie, int eaten) {
        this.cookie = cookie;
        this.eaten = eaten;
    }

    public int getCookie() {
        return cookie;
    }

    public void setCookie(int cookie) {
        this.cookie = cookie;
    }

    public int getEaten() {
        return eaten;
    }

    public void setEaten(int eaten) {
        this.eaten = eaten;
    }
    public void GetSomeCookie(){


    }
    public void EatSomeCookie(int clock){
        if(timer==clock)
        {
            if(cookie!=0)
            {
                eaten+=this.cookie;
                this.cookie=0;
            }
            timer=random.nextInt(6)+(clock+1);
        }
        else timer=random.nextInt(6)+(clock+1);
    }

    @Override
    public String toString() {
        return "CookieMonster{" +
                "cookie=" + cookie +
                ", eaten=" + eaten +
                ", timer=" + timer +
                ", random=" + random +
                '}';
    }
}
