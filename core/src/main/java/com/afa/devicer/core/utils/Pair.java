package com.afa.devicer.core.utils;

public class Pair<T> {

    private T start;
    private T end;

    public Pair() {
        this.start = null;
        this.end = null;
    }

    public Pair(T start, T end) {
        this();
        this.start = start;
        this.end = end;
    }

    public Pair(T value) {
        this();
        this.start = value;
        this.end = value;
    }

    public T getStart() {
        return start;
    }

    public void setStart(T start) {
        this.start = start;
    }

    public T getEnd() {
        return end;
    }

    public void setEnd(T end) {
        this.end = end;
    }

    public boolean isEmpty() {
        if (this.end == null || this.start == null) {
            return true;
        }
        return false;
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((end == null) ? 0 : end.hashCode());
        result = prime * result + ((start == null) ? 0 : start.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        @SuppressWarnings("unchecked")
        Pair<T> other = (Pair<T>) obj;
        if (end == null) {
            if (other.end != null)
                return false;
        } else if (!end.equals(other.end))
            return false;
        if (start == null) {
            if (other.start != null)
                return false;
        } else if (!start.equals(other.start))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Pair [start=" + start + ", end=" + end + "]";
    }
}
