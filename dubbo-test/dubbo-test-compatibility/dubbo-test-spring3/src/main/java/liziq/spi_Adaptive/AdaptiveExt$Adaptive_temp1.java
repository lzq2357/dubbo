package liziq.spi_Adaptive;

import org.apache.dubbo.common.extension.ExtensionLoader;


/**
 * 这是 org.apache.dubbo.common.extension.ExtensionLoader#createAdaptiveExtensionClassCode()
 * 创建的代理类，代理了 Adaptive 注解的方法:  echo2、echo3。
 */
public class AdaptiveExt$Adaptive_temp1 implements AdaptiveExt {
	private static final org.apache.dubbo.common.logger.Logger logger = org.apache.dubbo.common.logger.LoggerFactory.getLogger(ExtensionLoader.class);
	private java.util.concurrent.atomic.AtomicInteger count = new java.util.concurrent.atomic.AtomicInteger(0);

	public void echo1() {
		throw new UnsupportedOperationException("method public abstract void liziq.spi_Adaptive.AdaptiveExt.echo1() of interface liziq.spi_Adaptive.AdaptiveExt is not adaptive method!");
	}

	public void echo2(org.apache.dubbo.common.URL arg0) {
		if (arg0 == null) {
			throw new IllegalArgumentException("url == null");
		}
		org.apache.dubbo.common.URL url = arg0;

		//查看url上的 adaptive.ext参数
		String extName = url.getParameter("adaptive.ext", "META-INF/dubbo");
		if (extName == null) {
			throw new IllegalStateException("Fail to get extension(liziq.spi_Adaptive.AdaptiveExt) name from url(" + url.toString() + ") use keys([adaptive.ext])");
		}
		AdaptiveExt extension = null;
		try {
			extension = (AdaptiveExt) ExtensionLoader.getExtensionLoader(AdaptiveExt.class).getExtension(extName);
		} catch (Exception e) {
			if (count.incrementAndGet() == 1) {
				logger.warn("Failed to find extension named " + extName + " for type liziq.spi_Adaptive.AdaptiveExt, will use default extension dubbo instead.", e);
			}
			extension = (AdaptiveExt) ExtensionLoader.getExtensionLoader(AdaptiveExt.class).getExtension("META-INF/dubbo");
		}
		extension.echo2(arg0);
	}

	public void echo3(org.apache.dubbo.common.URL arg0) {
		if (arg0 == null) {
			throw new IllegalArgumentException("url == null");
		}
		org.apache.dubbo.common.URL url = arg0;

		//查看url上的 myKey 参数
		String extName = url.getParameter("myKey", "META-INF/dubbo");
		if (extName == null) {
			throw new IllegalStateException("Fail to get extension(liziq.spi_Adaptive.AdaptiveExt) name from url(" + url.toString() + ") use keys([myKey])");
		}
		AdaptiveExt extension = null;
		try {
			extension = (AdaptiveExt) ExtensionLoader.getExtensionLoader(AdaptiveExt.class).getExtension(extName);
		} catch (Exception e) {
			if (count.incrementAndGet() == 1) {
				logger.warn("Failed to find extension named " + extName + " for type liziq.spi_Adaptive.AdaptiveExt, will use default extension dubbo instead.", e);
			}
			extension = (AdaptiveExt) ExtensionLoader.getExtensionLoader(AdaptiveExt.class).getExtension("META-INF/dubbo");
		}
		extension.echo3(arg0);
	}
}
