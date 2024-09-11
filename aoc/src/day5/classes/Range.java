package day5.classes;

public class Range implements Comparable<day5.classes.Range> {
    public long start;
    public long length;

    public Range(long start, long length) {
        this.start = start;
        this.length = length;
    }

    public long max() {
        return start + length - 1;
    }

    @Override
    public int compareTo(day5.classes.Range r) {
        return Double.compare(this.start, r.start);
    }

    @Override
    public String toString() {
        return start + ": " + length;
    }
}
