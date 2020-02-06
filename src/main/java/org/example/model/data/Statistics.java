/*This is class returning Mileage and Price Statistics in one object*/
package org.example.model.data;

public class Statistics {
    private MileageStatistics mileageStatistics;
    private PriceStatistics priceStatistics;

    public Statistics() {
    }

    public Statistics(MileageStatistics mileageStatistics, PriceStatistics priceStatistics) {
        this.mileageStatistics = mileageStatistics;
        this.priceStatistics = priceStatistics;
    }

    public MileageStatistics getMileageStatistics() {
        return mileageStatistics;
    }

    public void setMileageStatistics(MileageStatistics mileageStatistics) {
        this.mileageStatistics = mileageStatistics;
    }

    public PriceStatistics getPriceStatistics() {
        return priceStatistics;
    }

    public void setPriceStatistics(PriceStatistics priceStatistics) {
        this.priceStatistics = priceStatistics;
    }

    @Override
    public String toString() {
        return
                "MileageStatistics " + mileageStatistics +
                ", PriceStatistics " + priceStatistics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Statistics that = (Statistics) o;

        if (mileageStatistics != null ? !mileageStatistics.equals(that.mileageStatistics) : that.mileageStatistics != null)
            return false;
        return priceStatistics != null ? priceStatistics.equals(that.priceStatistics) : that.priceStatistics == null;
    }

    @Override
    public int hashCode() {
        int result = mileageStatistics != null ? mileageStatistics.hashCode() : 0;
        result = 31 * result + (priceStatistics != null ? priceStatistics.hashCode() : 0);
        return result;
    }
}
