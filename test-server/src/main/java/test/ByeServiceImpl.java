package test;

import com.sl.annotation.Service;
import com.sl.api.ByeService;

/**
 * Created by yazai
 * Date: 下午3:02 2021/7/7
 * Description:
 */
@Service
public class ByeServiceImpl implements ByeService {

    @Override
    public String bye(String name) {
        return "bye, " + name;
    }
}
