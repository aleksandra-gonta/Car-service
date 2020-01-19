package org.example.model.data;

public class MileageStatistics {
    private double min;
    private double max;
    private double avg;

    public MileageStatistics() {
    }

    public MileageStatistics(double min, double max, double avg) {
        this.min = min;
        this.max = max;
        this.avg = avg;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    @Override
    public String toString() {
        return
                " min: " + min +
                ", max: " + max +
                ", avg: " + avg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MileageStatistics that = (MileageStatistics) o;

        if (Double.compare(that.min, min) != 0) return false;
        if (Double.compare(that.max, max) != 0) return false;
        return Double.compare(that.avg, avg) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(min);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(max);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(avg);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
