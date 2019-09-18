package liziq;

import org.apache.dubbo.common.URL;

/**
 * @author liziq
 */
public class SpringCloudAdaptiveExt implements AdaptiveExt {

    @Override
    public void echo(URL url) {
        System.out.println("spring cloud");
    }
}