package cn.sqhl.neig.pointsmanager.po;

public class NsEventsinfoWithBLOBs extends NsEventsinfo {
    private String memo;

    private String goods;

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods == null ? null : goods.trim();
    }
}