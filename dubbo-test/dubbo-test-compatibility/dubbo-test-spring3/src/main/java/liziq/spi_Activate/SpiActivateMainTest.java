package liziq.spi_Activate;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;

import java.util.List;

/**
 * @Activate(value="vvv", group="ggg", order="1")
 *      注解上的Value叫 ActivateValue
 *      order影响 结果输出顺序，数字越小，优先级越高，越靠前
 *
 * 通过getActivateExtension 来获取 有 @Activate 注解的实现类，
 *      会根据参数，选中那些满足条件的 实现类，然后实例化。
 *      如果没有过滤参数，那么选择所有
 *
 * 第一步，过滤 group + ActivateValue
 *      1.根据传入参数1：group="group1"：第一步，只选择组为 group1 的实现类
 *      2.如果有 ActivateValue，则必须要求 url上的参数名称，包含 ActivateValue
 *          比如url="test://localhost/test?kkk=vvv111"。
 *          会取 url上的参数名称数组：["kkk"]，看是否 包含 ActivateValue
 *      都满足后，选中
 *
 * 第二步，过滤 dubbo spi name
 *      根据传入的参数values={"1","2"}，选中 dubbo spi中，名称为 1、2的
 *      或者，根据传入参数key=kkk，从url上，取 kkk的参数值作为名称
 *
 * 根据两步 的过滤结果，取并集
 *
 *
 * @author liziq
 * */
public class SpiActivateMainTest {

    public static void main(String[] args) {

        //test1();

        test2();
    }



    /**
     * 第一步，过滤 group + ActivateValue
     *      1.根据传入参数1：group="group1"：第一步，只选择组为 group1 的实现类
     *      2.如果有 ActivateValue，则必须要求 url上的参数名称，包含 ActivateValue
     *          比如url="test://localhost/test?kkk=vvv111"。
     *          会取 url上的参数名称数组：["kkk"]，看是否 包含 ActivateValue
     *      都满足后，选中
     * */
    private static void test1(){
        ExtensionLoader<ActivateExt> loader = ExtensionLoader.getExtensionLoader(ActivateExt.class);

        //ActivateGroup1：@Activate(group = {"group1"})
        //ActivateGroup1_2：@Activate(group = {"group1,group2"})


        //选择  group包含 "group1"   的实现类，多个实现类的 group可以相同，所以取出来的值是list
        URL url1 = URL.valueOf("test://localhost/test");
        List<ActivateExt> list1 = loader.getActivateExtension(url1, new String[]{}, "group1");

        for(int i = 0; i < list1.size(); ++i){
            System.out.println(i + " : " + list1.get(i).getClass().getName());
        }
        System.out.println("-----------------------------");



        //ActivateHaveValue1：@Activate(group = {"haveValue"},value = {"myKey1"})
        //ActivateHaveValue2：@Activate(group = {"haveValue"},value = {"myKey2"})

        //选择 ActivateHaveValue1
        URL url2 = URL.valueOf("test://localhost/test?myKey1=feaf");
        List<ActivateExt> list2 = loader.getActivateExtension(url2, new String[]{}, "haveValue");

        for(int i = 0; i < list2.size(); ++i){
            System.out.println(i + " : " + list2.get(i).getClass().getName());
        }
        System.out.println("-----------------------------");

    }


    /**
     * 第二步，过滤 dubbo spi name
     *      根据传入的参数values={"1","2"}，选中 dubbo spi中，名称为 1、2的
     *      或者，根据传入参数key=kkk，从url上，取 kkk的参数值作为名称
     *  */

    private static void test2(){

        //ActivateHaveValue1：@Activate(group = {"haveValue"},value = {"myKey1"})
        //ActivateHaveValue2：@Activate(group = {"haveValue"},value = {"myKey2"})

        //选中 ActivateGroup1，尽管 group 不匹配。 因为 group_1=ActivateGroup1
        URL url1 = URL.valueOf("test://localhost/test");
        List<ActivateExt> list1 = ExtensionLoader.getExtensionLoader(ActivateExt.class).
                getActivateExtension(url1, new String[]{"group_1"}, "feafeafa");


        for(int i = 0; i < list1.size(); ++i){
            System.out.println(i + " : " + list1.get(i).getClass().getName());
        }
        System.out.println("-----------------------------");




        //选择 ActivateImpl1。  key=kkk   ->  url上取到 kkk=group_1_2  ->  取到 dubbo spi中名称为 group_1_2 的

        //这里加 group的原因是，如果没有 group，那么就会选择所有的实现类（无 ActivateValue ）
        URL url2 = URL.valueOf("test://localhost/test?kkk=group_1_2");
        List<ActivateExt> list2 = ExtensionLoader.getExtensionLoader(ActivateExt.class).
                getActivateExtension(url2, "kkk", "faklkl");


        for(int i = 0; i < list2.size(); ++i){
            System.out.println(i + " : " + list2.get(i).getClass().getName());
        }
        System.out.println("-----------------------------");
    }

}
