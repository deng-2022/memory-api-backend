<<<<<<< HEAD:src/main/java/com/memory/memory_api/model/enums/InterfaceInfoMethodEnum.java
package com.memory.memory_api.model.enums;
=======
package com.yupi.springbootinit.model.enums;
>>>>>>> b1096f4 (模拟接口初步实现):src/main/java/com/yupi/springbootinit/model/enums/InterfaceInfoMethodEnum.java

/**
 * 接口请求方法
 *
 * @author 邓哈哈
 * 2023/7/28 22:36
 * Function:
 * Version 1.0
 */

public enum InterfaceInfoMethodEnum {
    GET("GET", "GET方法"),

    POST("POST", "POST方法");

    private final String value;
    private final String text;

    InterfaceInfoMethodEnum(String value, String text) {
        this.value = value;
        this.text = text;
    }

    /**
     * 判断接口请求方法
     *
     * @param value 接口请求方法
     * @return 存在与否
     */
    public static InterfaceInfoMethodEnum getEnumByValue(String value) {
        if (value == null) {
            return null;
        }
        InterfaceInfoMethodEnum[] values = InterfaceInfoMethodEnum.values();
        for (InterfaceInfoMethodEnum infoStatusEnum : values) {
            if (infoStatusEnum.getValue().equals(value)) {
                return infoStatusEnum;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
