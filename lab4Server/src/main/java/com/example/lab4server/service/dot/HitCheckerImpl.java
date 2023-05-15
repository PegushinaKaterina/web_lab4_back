package com.example.lab4server.service.dot;

import com.example.lab4server.service.dot.HitChecker;
import org.springframework.stereotype.Component;

@Component("hitCheckerBean")
public class HitCheckerImpl implements HitChecker {

    public String checkHit(double x, double y, double r) {
        boolean hitInSecondQuarter = (x <= 0) && (y >= 0)
                && ((x * x + y * y) <= (r * r));
        boolean hitInThirdQuarter = (x >= -r) && (x <= 0)
                && (y >= -r) && (y <= 0);
        boolean hitInFourthQuarter = (x >= 0) && (y <= 0)
                && ((y - 2 * x) >= (-r));
        if (hitInSecondQuarter || hitInThirdQuarter || hitInFourthQuarter) {
            return "Попадание";
        } else {
            return "Промах";
        }
    }
}
