package com.dawn.zhao.utils;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

public class IpUtils {

    /**
     * System.getProperty 获取系统属性
     *
     * java.version  Java 运行时环境版本
     * java.vendor  Java 运行时环境供应商
     * java.vendor.url  Java 供应商的 URL
     * java.home  Java 安装目录
     * java.vm.specification.version Java 虚拟机规范版本
     * java.vm.specification.vendor Java 虚拟机规范供应
     * java.vm.specification.name Java 虚拟机规范名称
     * java.vm.version  Java 虚拟机实现版本
     * java.vm.vendor  Java 虚拟机实现供应商
     * java.vm.name  Java 虚拟机实现名称
     * java.specification.version Java 运行时环境规范版本
     * java.specification.vendor Java 运行时环境规范供应商
     * java.specification.name   Java 运行时环境规范名称
     * java.class.version   Java 类格式版本号
     * java.class.path  Java 类路径
     * java.library.path   加载库时搜索的路径列表
     * java.io.tmpdir  默认的临时文件路径
     * java.compiler  要使用的 JIT 编译器的名称
     * java.ext.dirs  一个或多个扩展目录的路径
     * os.name  操作系统的名称
     * os.arch  操作系统的架构
     * os.version  操作系统的版本
     * file.separator  文件分隔符（在 UNIX 系统中是“/”）
     * path.separator  路径分隔符（在 UNIX 系统中是“:”）
     * line.separator  行分隔符（在 UNIX 系统中是“/n”）
     * user.name  用户的账户名称
     * user.home  用户的主目录
     * user.dir  用户的当前工作目录
     */
    public static void main(String[] args) throws UnknownHostException {
        Long beginTime = System.currentTimeMillis();
        System.out.println(getLocalIP());
        System.out.println(getLocalPort());
        System.out.println("获取IP的时间 ： "+(System.currentTimeMillis()-beginTime)+"ms");
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("java.vendor"));
        System.out.println(System.getProperty("os.arch"));
        System.out.println(System.getProperty("user.name"));
    }

    /**
     * 获取本地IP地址
     * @throws SocketException
     */
    public static String getLocalIP() throws UnknownHostException {
        if (isWindowsOS()) {
            return InetAddress.getLocalHost().getHostAddress();
        } else {
            return getLinuxLocalIp();
        }
    }

    /**
     * 判断操作系统是否是Windows
     * @return
     */
    public static boolean isWindowsOS() {
        boolean isWindowsOS = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().indexOf("windows") > -1) {
            isWindowsOS = true;
        }
        return isWindowsOS;
    }

    /**
     * 获取本地Host名称
     */
    public static String getLocalHostName() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostName();
    }

    /**
     * 获取Linux下的IP地址
     * @return IP地址
     * @throws SocketException
     */
    private static String getLinuxLocalIp() {
        String ip = "";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                String name = intf.getName();
                if (!name.contains("docker") && !name.contains("lo")) {
                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            String ipaddress = inetAddress.getHostAddress().toString();
                            if (!ipaddress.contains("::") && !ipaddress.contains("0:0:")
                                    && !ipaddress.contains("fe80")) {
                                ip = ipaddress;
                                System.out.println(ipaddress);
                            }
                        }
                    }
                }
            }
        } catch (SocketException ex) {
            System.out.println("获取ip地址异常");
            ip = "127.0.0.1";
            ex.printStackTrace();
        }
        System.out.println("IP:" + ip);
        return ip;
    }

    //读取空闲的可用端口
    private static int getLocalPort(){
        ServerSocket serverSocket;
        try{
            serverSocket = new ServerSocket(0);
            return serverSocket.getLocalPort();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
