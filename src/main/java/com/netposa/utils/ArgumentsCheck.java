package com.netposa.utils;

import com.netposa.common.Constants;

/**
 * 参数检测类
 *
 * @author JuHan
 * @date 2021-05-07
 */
public class ArgumentsCheck {
    /**
     * 检查参数数量
     *
     * @param args
     * @throws Exception
     */
    public static void checkArgumentsNumber(String[] args) throws Exception {
        if (args.length != Constants.ARGUMENTS_NUMBER) {
            throw new Exception("参数数量有误。参数1为配置文件放置的路径字符串");
        }
    }

    /**
     * 检查传入的线程池大小设置是否合法（是否是证书阿拉伯数字、是否等于0）。合法，返回int值；不合法，抛异常
     *
     * @param threadPoolNum
     * @return
     * @throws Exception
     */
    public static int checkThreadPoolNumber(String threadPoolNum) throws Exception {
        int threadPoolSize;
        try {
            threadPoolSize = Integer.parseInt(threadPoolNum.trim());
        } catch (NumberFormatException e) {
            throw new Exception("输入的参数1（要发起的线程数）有误，请输入整数阿拉伯数字表示线程数");
        }
        if (threadPoolSize == 0) {
            throw new Exception("输入的参数1（要发起的线程数）有误，线程池大小必须大于0");
        }
        return threadPoolSize;
    }

    /**
     * 检查设置的要测试的数据存储引擎名参数。通过，返回string；未通过，抛异常；
     *
     * @param engineType
     * @return
     * @throws Exception
     */
    public static String checkEngineType(String engineType) throws Exception {
        if (Constants.ENGINS.contains(engineType.trim().toLowerCase())) {
            return engineType.trim().toLowerCase();
        } else {
            throw new Exception("参数2错误。参数2为要进行测试的数据存储引擎名字符串。目前包含如下数据存储引擎：" + Constants.ENGINS_STR);
        }
    }

}
