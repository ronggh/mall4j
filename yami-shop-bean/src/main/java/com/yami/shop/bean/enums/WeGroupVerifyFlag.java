
package com.yami.shop.bean.enums;

public enum WeGroupVerifyFlag {
    /**
     * 未审核
     */
    NONVERIFY("0"),

    /**
     * 审核通过
     */
    VERIFIED("1"),

    /**
     * 审核未通过
     */
    UNVERIFIED("2");

    private String status;

    public String value() {
        return status;
    }

    WeGroupVerifyFlag(String status) {
        this.status = status;
    }

    public static WeGroupVerifyFlag instance(String value) {
        WeGroupVerifyFlag[] enums = values();
        for (WeGroupVerifyFlag statusEnum : enums) {
            if (statusEnum.value().equals(value)) {
                return statusEnum;
            }
        }
        return null;
    }
}
