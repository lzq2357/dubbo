package liziq.spi_Adaptive;


import org.apache.dubbo.common.URL;

/**
 * @author liziq
 */
public class DubboAdaptiveExt implements AdaptiveExt {

    @Override
    public void echo1() {
        System.out.println("dubbo echo1");
    }

    @Override
    public void echo2(URL url) {
        System.out.println("dubbo echo2");
    }

    @Override
    public void echo3(URL url) {
        System.out.println("dubbo echo3");
    }
}