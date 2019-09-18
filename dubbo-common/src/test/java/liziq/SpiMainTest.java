package liziq;


import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;

/**
 * @spi("xxx")： 标记在接口上，标识接口的实现类是 spi，从配置文件找。
 *     配置文件是key-value形式。所以xxx代表  key的默认值
 * @Adaptive 放在实现类上，代表 这个实现类是 它父接口的 默认实现类
 * @Adaptive("myKey") 放在方法 上
 *     代表这个方法是动态的，在使用接口时，传入一个url，带上 myKey=xxx，代表这次使用的是 xxx这个类的 方法
 *
 *
 * 优先级：@Adaptive实现类 > url > spi默认值
 *          @Adaptive("myKey") 方法也是 通过url
 * @author liziq
 * @date 2019/09/17
 */
public class SpiMainTest {

    public static void main(String[] args) {

        // AdaptiveExt上标记 @SPI
        // 实现类写在 配置文件中：META-INF/dubbo/internal/dubbo.spi.AdaptiveExt
        ExtensionLoader<AdaptiveExt> loader = ExtensionLoader.getExtensionLoader(AdaptiveExt.class);
        AdaptiveExt adaptiveExtension = loader.getAdaptiveExtension();

        //获取指定 key=dubbo 的接口实现类。 加载配置文件k-v，然后初始化 key=dubbo的实现类
        AdaptiveExt dubbo = loader.getExtension("dubbo");

        //1. 接口 @spi("dubbo") ，指定默认 实现类 key="dubbo"
        //    会使用 dubbo对应的类
        URL url1 = URL.valueOf("test://localhost/test");
        adaptiveExtension.echo(url1);

        //2. 接口 @spi("dubbo") ，url上，指定参数  实现类 key="cloud"
        //   优先使用  url 上的 cloud。   "adaptive.ext=" 是固定的参数名
        URL url2 = URL.valueOf("test://localhost/test?adaptive.ext=cloud");
        adaptiveExtension.echo(url2);

        //3. 接口 @spi("dubbo") ，url上，指定参数  实现类 key="cloud"， 实现类上 有 @Adaptive 注解
        //   优先使用 @Adaptive 标记的实现类
        URL url3 = URL.valueOf("test://localhost/test?adaptive.ext=cloud");
        adaptiveExtension.echo(url3);


        //4. 接口 @spi("dubbo") ，且方法上 @Adaptive注解指定一个 名称myKey
        // url上，指定参数  myKey=cloud， 则会使用 cloud类 对应的方法
        URL url4 = URL.valueOf("test://localhost/test?myKey=cloud");
        adaptiveExtension.echo(url4);
    }




}
