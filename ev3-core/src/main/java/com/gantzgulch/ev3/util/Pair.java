package com.gantzgulch.ev3.util;

import java.util.Objects;

public class Pair<L, R> {

    private final L left;
    private final R right;

    public Pair(final L left, final R right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }

    @Override
    public boolean equals(final Object obj) {

        if (!(obj instanceof Pair)) {
            return false;
        }

        final Pair<L, R> o = Cast.cast(obj);

        return Objects.equals(this.left, o.left) //
                && Objects.equals(this.right, o.right);
    }
    
    @Override
    public int hashCode() {
        return left.hashCode() ^ right.hashCode();
    }

}
