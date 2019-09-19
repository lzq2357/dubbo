package liziq;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Protocol;

/**
 * liziq dubbo-wrapper 如果SPI的子类 是包装类，则缓存到 cachedWrapperClasses
 * 比如 Protocol 接口有 n 个SPI子类（散落在 dubbo-rpc 模块下，各个子模块中。）：
 *      filter=org.apache.dubbo.rpc.protocol.ProtocolFilterWrapper
 *      listener=org.apache.dubbo.rpc.protocol.ProtocolListenerWrapper
 *      mock=org.apache.dubbo.rpc.support.MockProtocol
 *      dubbo=org.apache.dubbo.rpc.protocol.dubbo.DubboProtocol
 *
 * ProtocolFilterWrapper、ProtocolListenerWrapper 都是 wrapper类型
 * 当我们获取 DubboProtocol时，会被作为构造函数
 *          传入到 ProtocolFilterWrapper，包装为 ProtocolFilterWrapper
 *          然后再传入 ProtocolListenerWrapper 包装
 */
public class SpiWrapperTest {

	
	public static void main(String[] args) {
		
		ExtensionLoader extensionLoader = ExtensionLoader.getExtensionLoader(Protocol.class) ;
		Protocol protocol = (Protocol) extensionLoader.getExtension("dubbo") ;
		System.out.println(protocol);
	}
}