package com.guorong.factory.v3;

class App {
    public static void main(String[] args) {
        // 金币
        Coin coin = CoinFactory.createCoin(CoinFactory.CoinType.GOLD);
        System.out.println(coin.getDescription());
        // 铜币
        coin = CoinFactory.createCoin(CoinFactory.CoinType.COPPER);
        System.out.println(coin.getDescription());
    }
}
