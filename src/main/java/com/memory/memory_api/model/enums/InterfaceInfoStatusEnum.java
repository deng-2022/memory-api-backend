<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 07df87f (模拟接口初步实现)
=======
>>>>>>> c0301db (重构 抽取公共服务)
<<<<<<< HEAD:src/main/java/com/memory/memory_api/model/enums/InterfaceInfoStatusEnum.java
package com.memory.memory_api.model.enums;
=======
package com.yupi.springbootinit.model.enums;
<<<<<<< HEAD
>>>>>>> 89eac19 (模拟接口初步实现):src/main/java/com/yupi/springbootinit/model/enums/InterfaceInfoStatusEnum.java
=======
package com.memory.memory_api.model.enums;
>>>>>>> e32758e (重构 抽取公共服务)
=======
>>>>>>> b1096f4 (模拟接口初步实现):src/main/java/com/yupi/springbootinit/model/enums/InterfaceInfoStatusEnum.java
<<<<<<< HEAD
>>>>>>> 07df87f (模拟接口初步实现)
=======
=======
package com.memory.memory_api.model.enums;
>>>>>>> dd943cf (重构 抽取公共服务)
>>>>>>> c0301db (重构 抽取公共服务)

/**
 * 接口状态
 *
 * @author 邓哈哈
 * 2023/7/28 22:36
 * Function:
 * Version 1.0
 */

public enum InterfaceInfoStatusEnum {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 07df87f (模拟接口初步实现)
=======
>>>>>>> c0301db (重构 抽取公共服务)
<<<<<<< HEAD:src/main/java/com/memory/memory_api/model/enums/InterfaceInfoStatusEnum.java
    ONLINE(1, "发布"),

    OFFLINE(0, "下线");
=======
    USUAL(0, "正常"),

    ERROR(1, "错误");
<<<<<<< HEAD
>>>>>>> 89eac19 (模拟接口初步实现):src/main/java/com/yupi/springbootinit/model/enums/InterfaceInfoStatusEnum.java
=======
    ONLINE(1, "发布"),

    OFFLINE(0, "下线");
>>>>>>> e32758e (重构 抽取公共服务)
=======
>>>>>>> b1096f4 (模拟接口初步实现):src/main/java/com/yupi/springbootinit/model/enums/InterfaceInfoStatusEnum.java
<<<<<<< HEAD
>>>>>>> 07df87f (模拟接口初步实现)
=======
=======
    ONLINE(1, "发布"),

    OFFLINE(0, "下线");
>>>>>>> dd943cf (重构 抽取公共服务)
>>>>>>> c0301db (重构 抽取公共服务)

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