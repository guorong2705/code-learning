package com.guorong.adapter.v1;

/**
 * 对象适配器模式
 */
class App {
    public static void main(String[] args) {
        // 渔船适配器
        FishBoatAdapter fishBoatAdapter = new FishBoatAdapter();
        // 赛艇船长
        Captain captain = new Captain(fishBoatAdapter);
        captain.row();
    }
}
