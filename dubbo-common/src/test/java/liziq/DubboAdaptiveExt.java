package liziq;


import org.apache.dubbo.common.URL;

/**
 * @author liziq
 */
public class DubboAdaptiveExt implements AdaptiveExt {

    @Override
    public void echo(URL url) {
        System.out.println("dubbo");
    }
}