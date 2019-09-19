package liziq.spi_Adaptive;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;


/**
 * @spi：标记这个类是 spi接口（dubbo自己的 spi），value="dubbo" 表示使用默认的key为dubbo
 *
 *  接口方法必须要有 URL 属性
 *
 *
 * @author liziq
 * */
@SPI("dubbo")
public interface AdaptiveExt {



    /**
     * 非 Adaptive 接口
     * */
    void echo1();


    /**
     * Adaptive 接口必须有 url参数
     *
     * @param url ： Adaptive 接口必须有 url参数
     * */
    @Adaptive
    void echo2(URL url);

    /**
     * Adaptive 接口必须有 url参数
     * 可指定 一个参数，myKey， url上带的 myKey=cloud，代表这个方法使用cloud的实现
     *
     * @param url ： Adaptive 接口必须有 url参数
     * */
    @Adaptive({"myKey"})
    void echo3(URL url);
}