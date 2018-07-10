package com.dawn.zhao.demo;

import com.dawn.zhao.utils.id.UUIDHexGenerator;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class DemoClass {

    private static final int ENUM      = 0x00004000;
    private static final int ANNOTATION= 0x00002000;
    private static final int SYNTHETIC = 0x00001000;
//    static SimpleDateFormat[] CUSTOM_DATE_FORMATS = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//
//    static {
//
//        /* Create Date Formats */
//        final String[] possibleDateFormats = {
//                /* RFC 1123 with 2-digit Year */"EEE, dd MMM yy HH:mm:ss z",
//                /* RFC 1123 with 4-digit Year */"EEE, dd MMM yyyy HH:mm:ss z",
//                /* RFC 1123 with no Timezone */"EEE, dd MMM yy HH:mm:ss",
//                /* Variant of RFC 1123 */"EEE, MMM dd yy HH:mm:ss",
//                /* RFC 1123 with no Seconds */"EEE, dd MMM yy HH:mm z",
//                /* Variant of RFC 1123 */"EEE dd MMM yyyy HH:mm:ss",
//                /* RFC 1123 with no Day */"dd MMM yy HH:mm:ss z",
//                /* RFC 1123 with no Day or Seconds */"dd MMM yy HH:mm z",
//                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ssZ",
//                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ss'Z'",
//                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:sszzzz",
//                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ss z",
//                /* ISO 8601 */"yyyy-MM-dd'T'HH:mm:ssz",
//                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ss.SSSz",
//                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HHmmss.SSSz",
//                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ss",
//                /* ISO 8601 w/o seconds */"yyyy-MM-dd'T'HH:mmZ",
//                /* ISO 8601 w/o seconds */"yyyy-MM-dd'T'HH:mm'Z'",
//                /* RFC 1123 without Day Name */"dd MMM yyyy HH:mm:ss z",
//                /* RFC 1123 without Day Name and Seconds */"dd MMM yyyy HH:mm z",
//                /* Simple Date Format */"yyyy-MM-dd",
//                /* Simple Date Format */"MMM dd, yyyy"};
//
//        /* Create the dateformats */
//        CUSTOM_DATE_FORMATS = new SimpleDateFormat[possibleDateFormats.length];
//
//        for (int i = 0; i < possibleDateFormats.length; i++) {
//            CUSTOM_DATE_FORMATS[i] = new SimpleDateFormat(possibleDateFormats[i], Locale.getDefault());
//            CUSTOM_DATE_FORMATS[i].setTimeZone(TimeZone.getTimeZone("GMT"));
//        }
//    }

    public static void main(String[] args) throws IOException, ParseException, InterruptedException {
//        Group group = new Group();
//        System.out.println("demo done");
//        for (int i = 0; i < 100; i++) {
//            Thread.sleep(100);
//        }
//        System.out.println(0x7fffffff >>> 24 & 0xff);
//        System.out.println(0x7fffffff >>> 16);
//        System.out.println(0x7fffffff >>> 8);
//        System.out.println(0x7fffffff);
//
//        File file = new File("FILE.txt");
//        if(!file.exists())
//            file.createNewFile();
//        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
//        randomAccessFile.write('A');
//        randomAccessFile.write('B');
//        int i = 0x7fffffff;
//        randomAccessFile.write((i >>> 24) & 0XFF);
//        randomAccessFile.write((i >>> 16) & 0XFF);
//        randomAccessFile.write((i >>> 8) & 0XFF);
//        randomAccessFile.write(i & 0XFF);
//
//        randomAccessFile.close();

//        int A = 0xabcd;
//        int h=(A&0xff00)>>8;
//        int s=A&0xff;
//        System.out.println(0xff00);
//        System.out.println((0xd*16^0)+(0xc*16^1)+(0xb*16^2)+(0xa*16^3));
//        System.out.println("高八位:"+h);
//        System.out.println(Integer.toHexString(h));
//        System.out.println("低八位:"+s);
//        System.out.println(Integer.toHexString(s));
//        System.out.println("值:"+((h<<8)|s));
//        System.out.println(Integer.toHexString(((h<<8)|s)));
//        1 10 11 100 101 110 111 1000 1001 1010
//        1011 1100 1101 1110 1111 10000 10001 10010 10011 10100i
//        10101 10110 10111 11000 11001 11010 11011 11100 11101 11110
//        11111 100000

//        String str = "101011";
//        int sum = 0;
//        for (int i = 0; i < str.length(); i++) {
//            sum += Integer.parseInt(str.substring(i,i+1))*2^(str.length()-i-1);
//        }
//        System.out.println();
//        System.out.println(sum);
//
//        int a=129;
//        int b=128;
//        System.out.println(Integer.toBinaryString(32));
//
//        System.out.println(Integer.toBinaryString(a));
//        System.out.println(Integer.toBinaryString(b));
//        System.out.println("a&b 与的结果是："+(a&b));

//        File[] files = new File("C:\\WorkTools\\apache-tomcat-8.0.48\\webapps\\ROOT\\WEB-INF\\lib").listFiles();
//        Arrays.stream(files).forEach((f)-> System.out.println(f.getName()));

//        2018-02-05T06:15:31.4681102Z
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//        Date date = simpleDateFormat.parse("2018-02-05T06:15:31.4681102Z");
//        System.out.println(date);
//        System.out.println(new UUIDHexGenerator().generate().toString());

//        List<Integer> primeNumbers = new ArrayList<>();
//        List<Integer> noRole = new ArrayList<>();
//        int i = 1;
//        System.out.println(Integer.MAX_VALUE);
//        while (true && i<Integer.MAX_VALUE){
//            boolean isPrimeNumber = isPrimeNumber(++i);
//            if(isPrimeNumber){
//                primeNumbers.add(i);
//                continue;
//            }
//
//            isRole://判断一个非质数的大于2的偶数,是否为两个质数和,如果是则符合规则
//            for (int p = 0; p < primeNumbers.size(); p++) {
//                for (int p1 = 0; p1 < primeNumbers.size(); p1++) {
//                    if((primeNumbers.get(p)+primeNumbers.get(p1)) == i){
//                        System.out.println(primeNumbers.get(p)+" + "+primeNumbers.get(p1)+ " = " + i);
//                        break isRole;
//                    }
//                }
//                if(p == primeNumbers.size()){
//                    noRole.add(i);
//                    System.out.println(i);
//                }
//            }
//        }
//        System.out.println(i);
        TimeUnit.SECONDS.sleep(1);
    }


    //判断一个数是否是质数（素数）
    public static boolean isPrimeNumber(int num){
        if(num == 2) return true;//2特殊处理
        if(num < 2 || num % 2 == 0) return false;//识别小于2的数和偶数
        for(int i=3; i<=Math.sqrt(num); i+=2){
            if( num % 2 == 0)
                return false;
            if(num % i == 0)//识别被奇数整除
                return false;
        }
        return true;
    }

//    static boolean isPrimeNumber(int a){
//        boolean ean = true;
//        for(int i=2;i< Math.sqrt(a);i++){ //Math.sqrt 是调用Math类中的sqrt方法，求一个数的平方根
//            if(a%i==0){
//                ean = false;
//                break;
//            }
//        }
//        return ean;
//    }

    private static Logger logger = Logger.getLogger(DemoClass.class);

    /**
     * 函数功能描述:UTC时间转本地时间格式
     * @param utcTime UTC时间
     * @param utcTimePatten UTC时间格式
     * @param localTimePatten   本地时间格式
     * @return 本地时间格式的时间
     * eg:utc2Local("2017-06-14 09:37:50.788+08:00", "yyyy-MM-dd HH:mm:ss.SSSXXX", "yyyy-MM-dd HH:mm:ss.SSS")
     */
    public static String utc2Local(String utcTime, String utcTimePatten, String localTimePatten) {
        SimpleDateFormat utcFormater = new SimpleDateFormat(utcTimePatten);
        utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));//时区定义并进行时间获取
        Date gpsUTCDate = null;
        try {
            gpsUTCDate = utcFormater.parse(utcTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return utcTime;
        }
        SimpleDateFormat localFormater = new SimpleDateFormat(localTimePatten);
        localFormater.setTimeZone(TimeZone.getDefault());
        String localTime = localFormater.format(gpsUTCDate.getTime());
        return localTime;
    }

    /**
     * 函数功能描述:UTC时间转本地时间格式
     * @param utcTime UTC时间
     * @param localTimePattern 本地时间格式(要转换的本地时间格式)
     * @return 本地时间格式的时间
     */
    public static String utc2Local(String utcTime, String localTimePattern){
        String utcTimePattern = "yyyy-MM-dd";
        String subTime = utcTime.substring(10);//UTC时间格式以 yyyy-MM-dd 开头,将utc时间的前10位截取掉,之后是含有多时区时间格式信息的数据

        //处理当后缀为:+8:00时,转换为:+08:00 或 -8:00转换为-08:00
        if(subTime.indexOf("+") != -1){
            subTime = changeUtcSuffix(subTime, "+");
        }
        if(subTime.indexOf("-") != -1){
            subTime = changeUtcSuffix(subTime, "-");
        }
        utcTime = utcTime.substring(0, 10) + subTime;

        //依据传入函数的utc时间,得到对应的utc时间格式
        //步骤一:处理 T
        if(utcTime.indexOf("T") != -1){
            utcTimePattern = utcTimePattern + "'T'";
        }

        //步骤二:处理毫秒SSS
        if(utcTime.indexOf(".") != -1){
            utcTimePattern = utcTimePattern + "HH:mm:ss.SSS";
        }else{
            utcTimePattern = utcTimePattern + "HH:mm:ss";
        }

        //步骤三:处理时区问题
        if(subTime.indexOf("+") != -1 || subTime.indexOf("-") != -1){
            utcTimePattern = utcTimePattern + "XXX";
        }
        else if(subTime.indexOf("Z") != -1){
            utcTimePattern = utcTimePattern + "'Z'";
        }

        if("yyyy-MM-dd HH:mm:ss".equals(utcTimePattern) || "yyyy-MM-dd HH:mm:ss.SSS".equals(utcTimePattern)){
            return utcTime;
        }

        SimpleDateFormat utcFormater = new SimpleDateFormat(utcTimePattern);
        utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date gpsUtcDate = null;
        try {
            gpsUtcDate = utcFormater.parse(utcTime);
        } catch (Exception e) {
            logger.error("utcTime converter localTime failed!!!", e);
            return utcTime;
        }
        SimpleDateFormat localFormater = new SimpleDateFormat(localTimePattern);
        localFormater.setTimeZone(TimeZone.getDefault());
        String localTime = localFormater.format(gpsUtcDate.getTime());
        return localTime;
    }

    /**
     * 函数功能描述:修改时间格式后缀
     * 函数使用场景:处理当后缀为:+8:00时,转换为:+08:00 或 -8:00转换为-08:00
     * @param subTime
     * @param sign
     * @return
     */
    private static String changeUtcSuffix(String subTime, String sign){
        String timeSuffix = null;
        String[] splitTimeArrayOne = subTime.split("\\ "+sign+"");
        String[] splitTimeArrayTwo = splitTimeArrayOne[1].split(":");
        if(splitTimeArrayTwo[0].length() < 2){
            timeSuffix = "+" + "0" + splitTimeArrayTwo[0] + ":" + splitTimeArrayTwo[1];
            subTime = splitTimeArrayOne[0] + timeSuffix;
            return subTime;
        }
        return subTime;
    }

    /**
     * 函数功能描述:获取本地时区的表示(比如:第八区-->+08:00)
     * @return
     */
    public static String getTimeZoneByNumExpress(){
        Calendar cal = Calendar.getInstance();
        TimeZone timeZone = cal.getTimeZone();
        int rawOffset = timeZone.getRawOffset();
        int timeZoneByNumExpress = rawOffset/3600/1000;
        String timeZoneByNumExpressStr = "";
        if(timeZoneByNumExpress > 0 && timeZoneByNumExpress < 10){
            timeZoneByNumExpressStr = "+" + "0" + timeZoneByNumExpress + ":" + "00";
        }
        else if(timeZoneByNumExpress >= 10){
            timeZoneByNumExpressStr = "+" + timeZoneByNumExpress + ":" + "00";
        }
        else if(timeZoneByNumExpress > -10 && timeZoneByNumExpress < 0){
            timeZoneByNumExpress = Math.abs(timeZoneByNumExpress);
            timeZoneByNumExpressStr = "-" + "0" + timeZoneByNumExpress + ":" + "00";
        }else if(timeZoneByNumExpress <= -10){
            timeZoneByNumExpress = Math.abs(timeZoneByNumExpress);
            timeZoneByNumExpressStr = "-" + timeZoneByNumExpress + ":" + "00";
        }else{
            timeZoneByNumExpressStr = "Z";
        }
        return timeZoneByNumExpressStr;
    }

    static class SingleTon {
        private static SingleTon singleTon = new SingleTon();
        public static int count1;
        public static int count2 = 0;
        private SingleTon() {
            count1++;
            count2++;
        }

        public static SingleTon getInstance() {
            return singleTon;
        }
    }

    static class Test {
        public static void main(String[] args) {
            SingleTon singleTon = SingleTon.getInstance();
            System.out.println("count1=" + singleTon.count1);
            System.out.println("count2=" + singleTon.count2);
        }
    }
}
