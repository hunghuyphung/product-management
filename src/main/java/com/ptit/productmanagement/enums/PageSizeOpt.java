package com.ptit.productmanagement.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum PageSizeOpt {
    TEN(10),
    TWENTY(20),
    THIRTY(30),
    FORTY(40),
    FIFTY(50),
    HUNDRED(100);

    private final int value;

    public static List<Integer> sequenceFrom(int startValue) {
        List<Integer> list = new ArrayList<>();
        list.add(startValue);

        for (var pageSizeOtp : PageSizeOpt.values()) {
            if (pageSizeOtp.getValue() != startValue) {
                list.add(pageSizeOtp.getValue());
            }
        }

        return list;
    }
}
