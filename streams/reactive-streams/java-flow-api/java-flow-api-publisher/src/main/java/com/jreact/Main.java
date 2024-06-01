package com.jreact;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        MySubscriber mysSubsciber = new MySubscriber();
        mysSubsciber.flowPublisher();
    }
}