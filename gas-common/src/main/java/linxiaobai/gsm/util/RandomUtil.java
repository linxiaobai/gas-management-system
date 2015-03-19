package linxiaobai.gsm.util;

import java.util.Random;
import java.util.UUID;

public class RandomUtil {
    //验证码位数
    private static final byte n = 6;
	/*
	 * 获取一个n位的验证码
	 * @return
	 */
	public static String getValidateCode(){
		Random r = new Random();
		String validateCode = "";
		for (int i = 0; i < n; i++) {
			int num = r.nextInt(10);
			validateCode += num;
		}
		return validateCode;
	}

    public static String random(){
        return UUID.randomUUID().toString().substring(0,8);
    }

}
