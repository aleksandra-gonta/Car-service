package org.example.model.data;

import java.math.BigDecimal;

public class PriceStatistics {
    private BigDecimal min;
    private BigDecimal max;
    private BigDecimal avg;

    public PriceStatistics() {
    }

    public PriceStatistics(BigDecimal min, BigDecimal max, BigDecimal avg) {
        this.min = min;
        this.max = max;
        this.avg = avg;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public BigDecimal getAvg() {
        return avg;
    }

    public void setAvg(BigDecimal avg) {
        this.avg = avg;
    }

    @Override
    public String toString() {
        return
                "min: " + min +
                ", max: " + max +
                ", avg: " + avg ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PriceStatistics that = (PriceStatistics) o;

        if (min != null ? !min.equals(that.min) : that.min != null) return false;
        if (max != null ? !max.equals(that.max) : that.max != null) return false;
        return avg != null ? avg.equals(that.avg) : that.avg == null;
    }

    @Override
    public int hashCode() {
        int result = min != null ? min.hashCode() : 0;
        result = 31 * result + (max != null ? max.hashCode() : 0);
        result = 31 * result + (avg != null ? avg.hashCode() : 0);
        return result;
    }
}
