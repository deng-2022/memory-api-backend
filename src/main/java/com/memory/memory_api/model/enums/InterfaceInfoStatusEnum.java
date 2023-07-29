<<<<<<< HEAD:src/main/java/com/memory/memory_api/model/enums/InterfaceInfoStatusEnum.java
package com.memory.memory_api.model.enums;
=======
package com.yupi.springbootinit.model.enums;
>>>>>>> 89eac19 (模拟接口初步实现):src/main/java/com/yupi/springbootinit/model/enums/InterfaceInfoStatusEnum.java

/**
 * 接口状态
 *
 * @author 邓哈哈
 * 2023/7/28 22:36
 * Function:
 * Version 1.0
 */

public enum InterfaceInfoStatusEnum {
<<<<<<< HEAD:src/main/java/com/memory/memory_api/model/enums/InterfaceInfoStatusEnum.java
    ONLINE(1, "发布"),

    OFFLINE(0, "下线");
=======
    USUAL(0, "正常"),

    ERROR(1, "错误");
>>>>>>> 89eac19 (模拟接口初步实现):src/main/java/com/yupi/springbootinit/model/enums/InterfaceInfoStatusEnum.java

    private final int value;
    private final String text;

    InterfaceInfoStatusEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    /**
     * 判断接口状态
     *
     * @param value 接口状态
     * @return 存在与否
     */
    public static InterfaceInfoStatusEnum getEnumByValue(Integer value) {
        if (value == null) {
            return null;
        }
        InterfaceInfoStatusEnum[] values = InterfaceInfoStatusEnum.values();
        for (InterfaceInfoStatusEnum infoStatusEnum : values) {
            if (infoStatusEnum.getValue() == value) {
                return infoStatusEnum;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}