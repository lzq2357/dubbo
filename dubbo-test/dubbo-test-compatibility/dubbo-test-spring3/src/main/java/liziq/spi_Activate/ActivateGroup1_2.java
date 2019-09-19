package liziq.spi_Activate;


import com.alibaba.dubbo.common.extension.Activate;

/**
 * @author liziq
 * */
@Activate(group = {"group1", "group2"})
public class ActivateGroup1_2 implements ActivateExt {


    @Override
    public void echo(){
        System.out.println("...group1、group2...");
    }
}