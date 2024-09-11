package day5.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mapping {
    public Category from;
    public Category to;
    private List<RangeMap> ranges;
    private boolean sorted = false;

    public Mapping(Category from, Category to) {
        this.from = from;
        this.to = to;
        this.ranges = new ArrayList<>();
    }

    public void addRange(long from, long to, long length) {
        ranges.add(new RangeMap(new Range(from, length), to));
    }

    public List<Range> map(Range from) {
        if (!sorted) {
            Collections.sort(ranges);
            sorted = true;
        }
        List<Range> rs = new ArrayList<>();
        int a = 0;
        int b = ranges.size()-1;

        if (from.start < ranges.get(a).from.start) {
            if (from.max() < ranges.get(a).from.start) {
                rs.add(from);
                return rs;
            }
            long newStart = from.max()+1;
            long newLength = from.max() - ranges.get(a).from.start + 1;
            rs.add(new Range(from.start, from.length - newLength));
            from.start = newStart;
            from.length = newLength;
        }
        if (from.start >= ranges.get(b).from.start) {
            return ranges.get(b).map(from);
        }

        while (a != b) {
            int mid = (a+b) / 2;

            if (from.start >= ranges.get(mid).from.start) {
                a = mid;
                if (from.start < ranges.get(mid+1).from.start) break;
            } else {
                b = mid;
            }
        }
        RangeMap range = ranges.get(a);
        int i = 0;
        while (from.max() >= range.from.start && a + ++i < ranges.size()) {
            RangeMap nextRange = ranges.get(a+i);
            if (from.max() < nextRange.from.start) {
                rs.addAll(range.map(from));
                break;
            }
            long max = from.max();
            long start = nextRange.from.start;
            from.length = start - from.start;
            rs.addAll(range.map(from));
            from = new Range(start, max-start+1);

            range = nextRange;
        }
        if (a + i == ranges.size() && from.start >= ranges.getLast().from.start) {
            rs.addAll(ranges.getLast().map(from));
        }

        return rs;
    }

    private class RangeMap implements Comparable<RangeMap> {
        Range from;
        long to;

        public RangeMap(Range from, long to) {
            this.from = from;
            this.to = to;
        }

        public List<Range> map(Range r) {
            List<Range> ranges = new ArrayList<>();

            long diff = r.start - this.from.start;
            if (r.start + r.length-1 <= this.from.max()) {
                ranges.add(new Range(to+diff, r.length));
                return ranges;
            }
            if (r.start >= this.from.max()) {
                ranges.add(new Range(r.start, r.length));
                return ranges;
            }
            ranges.add(new Range(this.to+diff, this.from.max() - r.start+1));
            ranges.add(new Range(this.to + this.from.length, r.max() - this.from.max()));

            return ranges;
        }

        @Override
        public int compareTo(RangeMap r) {
            return this.from.compareTo(r.from);
        }
    }
}
