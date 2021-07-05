package com.sl.serializer;

/**
 * Created by yazai
 * Date: 20:36 2021/7/5
 * Description: 通用的序列化反序列化接口
 */
public interface CommonSerializer {
    //  序列化
    byte[] serialize(Object obj);

    //    反序列化
    Object deserialize(byte[] bytes, Class<?> clazz);


    //    获得该序列化器的编号
    int getCode();


    //    根据编号获取序列化器
    static CommonSerializer getByCode(int code) {
        switch (code) {
            case 0:
                return new KryoSerializer();
            case 1:
                return new JsonSerializer();
            default:
                return null;
        }
    }
}
