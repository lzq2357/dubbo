package liziq.spi_Activate;


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
public interface ActivateExt {



    /**
     * 打印
     * */
    void echo();
}