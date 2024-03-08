package me.yghee.ch04_design_quality_and_tradeoffs.code;

public class Rectangle {
    private int left;
    private int top;
    private int right;
    private int bottom;

    public Rectangle ( int left, int top, int right, int bottom ) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public int getLeft () {
        return left;
    }

    public int getTop () {
        return top;
    }

    public int getRight () {
        return right;
    }

    public int getBottom () {
        return bottom;
    }

    public void setLeft ( int left ) {
        this.left = left;
    }

    public void setTop ( int top ) {
        this.top = top;
    }

    public void setRight ( int right ) {
        this.right = right;
    }

    public void setBottom ( int bottom ) {
        this.bottom = bottom;
    }

    // Rectangle을 변경하는 주체는 자기 자신이다.
    public void enlarge ( int multiple ) {
        right *= multiple;
        bottom *= multiple;
    }
}
