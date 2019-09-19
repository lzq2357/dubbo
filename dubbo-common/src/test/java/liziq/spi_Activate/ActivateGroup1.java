package liziq.spi_Activate;

import com.alibaba.dubbo.common.extension.Activate;


/**
 * @author liziq
 * */
@Activate(group = {"group1"})
public class ActivateGroup1 implements ActivateExt {


    @Override
    public void echo(){
        System.out.println("...group1...");
    }
}