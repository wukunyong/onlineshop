package com.wuky.order.config;

import java.io.FileWriter;
import java.io.IOException;


public class AlipayConfig
{
	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092600599915";

	// 商户私钥，您的PKCS8格式RSA2私钥
	public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCAsM2EuxRYyaSxUDj4LdgrXWSlndYkQIVlZTRPfmhhsr1ybdRuBD2NO5Z13kKTkOy0iHN8aZPLyf/rEt+GO2GZkkvElx1cfsQhnYhKsZn5pcp3StASKAiTtm3kyGx4cKwwQAObA4cpIWcsZLNr2xntQUZabUBLLBYJ/kXYawaXbBXpPNx/Yd0KKfVmNqNOr+zGb97KOGBHmPCcMb5yl6x6mcJ6r/ohDoVmRhByVdq66ut3AUu4qytZR0u56HuLoVr7n6an2RJqN4Q9+eDubiPp06QXx14YO+KEkuLEEu5NWkXCWvI2ha1FtnS/zg9CAs1xz6Kzn8M+t2adf+xcKWXXAgMBAAECggEAC24owvpO41f9rdYfSUPhKHDiKKetRokIkCn+DHWA1fSnSQuiS6dKxEmfopZ8OVks/RgFke5at0YY2gtSGT0a/SX2Xt7BN972krnISkIeGAN2yUO22XX+1QfmGOUIZWY0lRCKgXE9xxR1cob7PMA4PE+I7S05AbRHvNl94KwY6MgLc13pp4qxP2RQDzxjfHHs7lFIkoY50BDUSClWmOU8vmACHJM39CeY02KfqIZ8DkGMwG4ZcaCGYicMaZbTBGXY2+muVezEHZHE7Anafsp2G20Hp+kyTc/nlJ0l4NFMldVDFN2UClGPAka87IJ7UNa3EDm4Q1JCY2PJg5yy2Z16UQKBgQDLKfKzG5gmhm3K/Yi26plLUHD/eulOjAAnPhkMtbkLqZPM+py3aXIfMsXLFB2kgnAzKQcdNIIIYDbW2V2kTalfO3y6wu6+rZi0q1C+8A0OFYooOrNboM1widwspFTQrRYykRR6/NHP0G4Kiags+W6QCgOU6GgHzw1h2ACoLMUIeQKBgQCiKKWMaOsO3TT/0DrBYxpO8jnaQr1loZ8EKuDDlTVIN2qmF9TJoKE83KrXJp/M0Km7mpg/JvigrZ8vwi/HOVtdLIUosKoHSX07xNmpa3LE6dSg+/Urzl4+adwdm32rUdCL887ZCd8U7CsdxAFjp52ck/YTdxuCuBbewZ4ee0jszwKBgQCfrV+QgC6skNl4Qp6STX6gYB//3GXEk2qHbVoY0DcZQHXnFE3aJelJNgmZXD0UEGUJAMUTojJVTPGtGXhs65EkRUf6Lb2hZiVwUnmLKHbKTAmjJsfTS2ZMfj3rZFk5DeE1ayuyokRxVjs20wy4FG9ejFBWV/dzs7ur3C5f/5f42QKBgQCQNSmURrHTlDFP1jSKSsHYt11vlq6sNSEfKaqdBJJP2MiFUOgxLyJ056EemQC/uklXx1Hhx5yP+Bd692xZ9c4kfDUnTITI2jf+8T7Ihf+Y0oH/cFJGVM5HHwlbYy5NLwMRkw6+wSKcjrKBVBAQOb3Ww1axVNkoVOIx02n0nZrqzQKBgGspcUYeMkXYxzw+0bgMC+q2hQDj81QeRozbfa3LATgySUqPucF35JOAcV89C6E9llk7Hvep6RLK7HUh9kPNmZJ6+EMNmeiBgLIY4q77y6WR1Fm8I0GguRiY92RH59txIDUzc2c3L8z9PIsAu6JfRlOEoqm83c0d/AYuX5y1RQ1N";

	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq+B7lI8n5Eyn5zTEOGUNEQWOgmP4ZV889Fs/qADznldTRhLOY2iEM2/HpvaT0e0Qm7vnGmBbIyvVFf58V9LiiSWPueyVWZIn8FQ8JRkcyTwPzUWfpFzntBjhGcjXTIddESY/ZN9k2k3BEPG5DdLC9HhvyFzSFkuBMcGby5ygBv544OmTgzS5vI/H8zvF4ByTBmM8A4HyHUX7IyIJmEzb4gBYylswmWwLpcZ4Iuhml89JYEmAKInMXLn1pgpfLg5qDADf8OUFasfGou4fTc1Z3YrAb03iiznZPxh5vnKn3qVIfTimn6Di/RVX2gl1hdLC3yBRu2NE9oUUlLFsyJcT+wIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8086/order/error.html";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8086/order/success.html";

	// 签名方式
	public static String sign_type = "RSA2";

	// 字符编码格式
	public static String charset = "utf-8";

	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

	// 支付宝网关
	public static String log_path = "C:\\";


	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	/**
	 * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
	 *
	 * @param sWord 要写入日志里的文本内容
	 */
	public static void logResult(final String sWord)
	{
		FileWriter writer = null;
		try
		{
			writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
			writer.write(sWord);
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (writer != null)
			{
				try
				{
					writer.close();
				}
				catch (final IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

}
