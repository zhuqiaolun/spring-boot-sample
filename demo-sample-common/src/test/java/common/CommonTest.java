package common;

import com.demon.sample.util.MD5Util;
import com.demon.sample.util.SnowflakeIdWorker;
import org.junit.Test;

/**
 * @author: Demon
 * @date 2021/3/5 14:05
 * @description: 测试
 */
public class CommonTest {

    @Test
    public void MD5Util加密(){
        String encode = MD5Util.encode("123456");
        System.out.println(encode);
    }

    @Test
    public void 获取随机字符串(){
        String randomStr = SnowflakeIdWorker.getRandomStr();
        System.out.println(randomStr);
    }

}
