package liziq.spi_Activate;


import com.alibaba.dubbo.common.extension.Activate;

/**
 * @author liziq
 * */
@Activate(group = {"haveValue"}, value = {"myKey1"})
public class ActivateHaveValue1 implements ActivateExt {


    @Override
    public void echo(){
        System.out.println("...haveValue1...");
    }
}