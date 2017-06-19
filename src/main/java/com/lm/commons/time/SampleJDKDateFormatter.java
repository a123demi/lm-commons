package com.lm.commons.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * JDK 日期格式化
 * @author liangming.deng
 * @date   2017年6月19日
 *
 */
public class SampleJDKDateFormatter implements DateFormatter {
    Predicate<String>          predicate;
    Supplier<SimpleDateFormat> formatSupplier;

    public SampleJDKDateFormatter(Predicate<String> predicate, Supplier<SimpleDateFormat> formatSupplier) {
        this.predicate = predicate;
        this.formatSupplier = formatSupplier;
    }

    @Override
    public boolean support(String str) {
        return predicate.test(str);
    }

    @Override
    public Date format(String str) {
        try {
            return formatSupplier.get().parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getPattern() {
        return formatSupplier.get().toPattern();
    }
}