package liziq.spi_Adaptive;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;


/**
 * @spi("xxx")： 标记在接口上，标识接口的实现类是 spi，从配置文件找。
 *     配置文件是key-value形式。所以xxx代表  key的默认值
 *
 * @spi：标记这个类是 spi接口（dubbo自己的 spi），value="dubbo" 表示使用默认的key为dubbo
 * @Adaptive 接口方法必须要有 URL 属性
 *
 * 实际上，dubbo只会根据 URL上的 adaptive.ext=xxx 来获取指定的实现类
 *      @SPI("dubbo")， 则会在获取不到adaptive.ext的值时，给一个默认值"dubbo"
 *      @Adaptive({"myKey"})， 则是 dubbo允许自定义 adaptive.ext这个名词，现在定义为 myKey
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
