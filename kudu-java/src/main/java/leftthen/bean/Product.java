package leftthen.bean;

public class Product {
    private long id;
    private float incomeRate;
    private long effectiveTime;
    private String memo;
    private short productType;
    private double interest;
    private long updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getIncomeRate() {
        return incomeRate;
    }

    public void setIncomeRate(float incomeRate) {
        this.incomeRate = incomeRate;
    }

    public long getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(long effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public short getProductType() {
        return productType;
    }

    public void setProductType(short productType) {
        this.productType = productType;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String   toString() {
        return "Product{" +
                "id=" + id +
                ", incomeRate=" + incomeRate +
                ", effectiveTime=" + effectiveTime +
                ", memo='" + memo + '\'' +
                ", productType=" + productType +
                ", interest=" + interest +
                ", updateTime=" + updateTime +
                '}';
    }
}
