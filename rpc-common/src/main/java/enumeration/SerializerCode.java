package enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by yazai
 * Date: 20:25 2021/7/5
 * Description: 字节流中标识序列化和反序列化器
 */
@AllArgsConstructor
@Getter
public enum SerializerCode {

    //JSON序列化器的code为1
    JSON(1);

    private final int code;
}
