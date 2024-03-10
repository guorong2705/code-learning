package com.guorong.factory.v1;

class App {
    public static void main(String[] args) {
        // 获取金币
        Coin coin = CoinFactory.getCoin(CoinFactory.CoinType.GOLD);
        System.out.println(coin.getDescription());
        // 获取铜币
        coin = CoinFactory.getCoin(CoinFactory.CoinType.COPPER);
        System.out.println(coin.getDescription());
    }
}
