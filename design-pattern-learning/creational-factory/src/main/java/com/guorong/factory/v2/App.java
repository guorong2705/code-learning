package com.guorong.factory.v2;

class App {
    public static void main(String[] args) {
        // 金币
        Coin coin = CoinFactory.getCoin(CoinFactory.CoinType.GOLD);
        System.out.println(coin.getDescription());
        // 铜币
        coin = CoinFactory.getCoin(CoinFactory.CoinType.COPPER);
        System.out.println(coin.getDescription());
    }
}
