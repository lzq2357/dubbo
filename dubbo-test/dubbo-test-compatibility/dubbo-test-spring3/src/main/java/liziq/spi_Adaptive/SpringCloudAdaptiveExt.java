package liziq.spi_Adaptive;

import org.apache.dubbo.common.URL;

/**
 * @author liziq
 */
public class SpringCloudAdaptiveExt implements AdaptiveExt {

    @Override
    public void echo1() {
        System.out.println("spring cloud echo1");
    }

    @Override
    public void echo2(URL url) {
        System.out.println("spring cloud echo2");
    }

    @Override
    public void echo3(URL url) {
        System.out.println("spring cloud echo3");
    }
}