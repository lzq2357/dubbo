package liziq.spi_Adaptive;

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
        // 实现类写在 配置文件中：META-INF/dubbo/internal/dubbo.spi_Adaptive.AdaptiveExt

        //echo1 是非Adaptive方法， echo2是Adaptive方法， echo3 是Adaptive，且有myKey
        //test1();

        test2();
    }

    /**
     * getExtension("key")：获取指定名称的 实现类
     *
     * 后续的接口调用，都是这个key的，就算 adaptive 也一样
     * 相当于没有 “adaptive动态”
     * */
    private static void test1(){
        //echo1 是非Adaptive方法， echo2是Adaptive方法， echo3 是Adaptive，且有myKey
        ExtensionLoader<AdaptiveExt> loader = ExtensionLoader.getExtensionLoader(AdaptiveExt.class);

        //使用 getExtension
        AdaptiveExt cloud = loader.getExtension("cloud");

        //非Adaptive方法
        cloud.echo1();

        //Adaptive方法，也是使用 cloud，URL上的动态配置失效
        URL url2 = URL.valueOf("test://localhost/test?adaptive.ext=dubbo");
        cloud.echo2(url2);
    }




    /**
     * getAdaptiveExtension，动态实现类，这里会通过字节码生成代理类
     *
     * */
    private static void test2(){
        //echo1 是非Adaptive方法， echo2是Adaptive方法， echo3 是Adaptive，且有myKey
        ExtensionLoader<AdaptiveExt> loader = ExtensionLoader.getExtensionLoader(AdaptiveExt.class);

        //getAdaptiveExtension， 获取动态实现类，这里会根据URL上的参数，来动态的 通过字节码生成代理类
        //动态生成的代理类，代理的就是 那些 Adaptive方法，非Adaptive方法 无法调用

        AdaptiveExt adaptiveExtension = loader.getAdaptiveExtension();

        //非Adaptive方法 无法调用
        //adaptiveExtension.echo1();

        //1. URL上没有指定实现类，则使用默认的实现类。 接口 @spi("dubbo") ，指定默认 实现类 key="dubbo"
        URL url1 = URL.valueOf("test://localhost/test");
        adaptiveExtension.echo2(url1);

        //2.URL上指定了实现类，所以会使用 cloud
        URL url2 = URL.valueOf("test://localhost/test?adaptive.ext=cloud");
        adaptiveExtension.echo2(url2);


        //3. echo3方法上 @Adaptive注解指定一个 名称myKey
        // url上，指定参数  myKey=other， 则会使用 other 类 对应的方法
        URL url3 = URL.valueOf("test://localhost/test?myKey=other");
        adaptiveExtension.echo3(url3);

    }
}
