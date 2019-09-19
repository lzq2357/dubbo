package liziq.spi_Adaptive;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;


/**
 * @spi：标记这个类是 spi接口（dubbo自己的 spi），value="dubbo" 表示使用默认的key为dubbo
 * @Adaptive 接口方法必须要有 URL 属性
 *
 * 实际上，dubbo只会根据 URL上的 adaptive.ext=xxx 来获取指定的实现类
 *      @SPI("dubbo")， 则会在获取不到adaptive.ext的值时，给一个默认值"dubbo"
 *      @Adaptive({"myKey"})， 则是 dubbo允许自定义 adaptive.ext这个名词，现在定义为 myKey
 *
 * 如果接口有一个 子类，标记了 @Adaptive，则默认作为 默认实现类
 * 如果没有，则动态生成一个代理类
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
     * Adaptive 接口必须有 url参数，
     *
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