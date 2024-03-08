package me.yghee.ch04_design_quality_and_tradeoffs.code;

public class AnyClass {
    public void anyMethod(Rectangle rectangle, int multiple) {
        // 나쁜 예시
        rectangle.setRight( rectangle.getRight() * multiple );
        rectangle.setBottom( rectangle.getBottom() * multiple );

        // 개선
        rectangle.enlarge( multiple );

    }
}
