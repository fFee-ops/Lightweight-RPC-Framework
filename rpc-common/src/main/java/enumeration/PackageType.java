package enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by yazai
 * Date: 20:22 2021/7/5
 * Description:
 */
@AllArgsConstructor
@Getter
public enum PackageType {
    REQUEST_PACK(0),
    RESPONSE_PACK(1);

    private final int code;
}
