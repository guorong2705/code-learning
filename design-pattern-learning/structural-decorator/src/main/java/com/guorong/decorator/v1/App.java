package com.guorong.decorator.v1;

class App {
    public static void main(String[] args) {
        SimpleTroll simpleTroll = new SimpleTroll();
        simpleTroll.attack();
        System.out.println("=====================");
        ClubbedTroll clubbedTroll = new ClubbedTroll(simpleTroll);
        clubbedTroll.attack();
    }
}
