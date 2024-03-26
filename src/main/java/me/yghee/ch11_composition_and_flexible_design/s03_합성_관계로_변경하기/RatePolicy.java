package me.yghee.ch11_composition_and_flexible_design.s03_합성_관계로_변경하기;

public interface RatePolicy {

    Money calculate( Phone phone );
}
