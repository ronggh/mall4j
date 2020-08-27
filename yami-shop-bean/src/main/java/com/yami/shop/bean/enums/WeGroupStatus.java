
package com.yami.shop.bean.enums;

public enum WeGroupStatus {

    /**
     * 未审核
     */
    NONVERYIFY("0"),

    /**
     * 审核通过
     */
    VERYIFIED("1"),

    /**
     * 审核未通过
     */
    UNVERYIFIED("2");

    private String status;

    public String value() {
        return status;
    }

    WeGroupStatus(String status) {
        this.status = status;
    }

    public static WeGroupStatus instance(String value) {
        WeGroupStatus[] enums = values();
        for (WeGroupStatus statusEnum : enums) {
            if (statusEnum.value().equals(value)) {
                return statusEnum;
            }
        }
        return null;
    }
}
