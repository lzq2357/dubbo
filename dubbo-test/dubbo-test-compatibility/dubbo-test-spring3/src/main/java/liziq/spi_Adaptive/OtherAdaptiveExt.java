package liziq.spi_Adaptive;

import org.apache.dubbo.common.URL;

/**
 * @author liziq
 */
//@Adaptive
public class OtherAdaptiveExt implements AdaptiveExt {

    @Override
    public void echo1() {
        System.out.println("other echo1");
    }

    @Override
    public void echo2(URL url) {
        System.out.println("other echo2");
    }

    @Override
    public void echo3(URL url) {
        System.out.println("other echo3");
    }
}