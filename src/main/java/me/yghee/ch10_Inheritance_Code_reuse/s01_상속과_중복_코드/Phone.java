package me.yghee.ch10_Inheritance_Code_reuse.s01_상속과_중복_코드;

import me.yghee.ch02_object_oriented_programming.s05_추상화와_유연성.Money;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Phone {


    private Money amount;
    private Money regularAmount;
    private Duration seconds;
    private List<Call> calls = new ArrayList<>();
    private double taxRate;

   public Phone( Money amount, Duration seconds, double taxRate ) {
       this.amount = amount;
       this.seconds = seconds;
       this.taxRate = taxRate;
   }

    public Money calculateFee() {
        Money result = Money.ZERO;
        for ( Call call : calls ) {
                regularAmount.times( ( double ) (call.getDuration().getSeconds() / seconds.getSeconds()) );
        }

        return result.plus( result.times( taxRate ) );
    }

    public List<Call> getCalls() {
        return calls;
    }

    public Money getAmount() {
        return amount;
    }

    public Duration getSeconds() {
        return seconds;
    }

    public double getTaxRate() {
       return taxRate;
    }
}
