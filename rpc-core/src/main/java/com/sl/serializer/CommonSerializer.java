package com.sl.serializer;

/**
 * Created by yazai
 * Date: 20:36 2021/7/5
 * Description: 通用的序列化反序列化接口
 */
public interface CommonSerializer {

    Integer KRYO_SERIALIZER = 0;
    Integer JSON_SERIALIZER = 1;
    Integer HESSIAN_SERIALIZER = 2;
    Integer PROTOBUF_SERIALIZER = 3;

    //默认使用Kryo进行序列化
    Integer DEFAULT_SERIALIZER = KRYO_SERIALIZER;


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
            case 2:
                return new HessianSerializer();
            case 3:
                return new ProtobufSerializer();
            default:
                return null;
        }
    }
}
