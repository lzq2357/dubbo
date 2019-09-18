package liziq;
import org.apache.dubbo.common.URL;

/**
 * @author liziq
 */
//@Adaptive
public class OtherAdaptiveExt implements AdaptiveExt {

    @Override
    public void echo(URL url) {
        System.out.println("other");

    }
}