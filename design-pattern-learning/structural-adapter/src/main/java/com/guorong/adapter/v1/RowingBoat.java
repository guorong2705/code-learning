package com.guorong.adapter.v1;

/**
 * 赛艇
 */
interface RowingBoat {
    // 赛艇航行
    void row();
}

/**
 * 渔船
 */
class FishingBoat {
    // 渔船航行
    public void sail() {
        System.out.println("渔船航行..............");
    }
}

/**
 * 赛艇船长
 */
class Captain {
    // 赛艇
    private final RowingBoat rowingBoat;

    public Captain(RowingBoat rowingBoat) {
        this.rowingBoat = rowingBoat;
    }

    // 船长开船
    public void row() {
        rowingBoat.row();
    }
}

/**
 * 适配器：将渔船适配为赛艇
 */
class FishBoatAdapter implements RowingBoat {
    // 渔船
    private final FishingBoat fishingBoat;

    public FishBoatAdapter() {
        this.fishingBoat = new FishingBoat();
    }

    @Override
    public void row() {
        fishingBoat.sail();
    }
}