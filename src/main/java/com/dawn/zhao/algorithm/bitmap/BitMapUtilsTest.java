package com.dawn.zhao.algorithm.bitmap;

import com.dawn.zhao.utils.LocalEntityLineWriter;
import com.google.common.io.LineReader;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * BitMap案例,获得最大的随机数字 2147483640
 * numberFile()生成数字保存到文件
 * 5亿个int类型数字存文件,总大小5.57G
 * 启动参数: -Xmx1024M
 */
public class BitMapUtilsTest {

    public static void main(String[] args) throws IOException {
//        numberFile();

        BitMapUtils bitMapUtils = new BitMapUtils(1<<29);
        LineReader lineReader = new LineReader(new InputStreamReader(new FileInputStream(new File("C:\\WorkSpace\\number.txt"))));

        while (true) {
            String numStr = lineReader.readLine();
            if(StringUtils.isEmpty(numStr)) {
                break;
            }
            Integer i = Integer.parseInt(numStr);
            bitMapUtils.put(i);
        }
        System.out.println(bitMapUtils.lastNum());

//        System.out.println(maxNum());
    }

    public static Integer maxNum() throws IOException {
        LineReader lineReader = new LineReader(new InputStreamReader(new FileInputStream(new File("C:\\WorkSpace\\number.txt"))));
        Integer max_num = 0;
        while (true) {
            String numStr = lineReader.readLine();
            if (StringUtils.isEmpty(numStr)) {
                break;
            }
            Integer i = Integer.parseInt(numStr);
            max_num = max_num > i ? max_num : i;
        }
        return max_num;
    }

    public static void numberFile() throws IOException {

        LocalEntityLineWriter entityLineWriter = new LocalEntityLineWriter("C:\\WorkSpace\\number.txt");

        Random random = new Random();
        for (int i = 0; i < 500000000; i++) {
            int randomInt = random.nextInt();
            entityLineWriter.writeLine(randomInt+"");
            if(i%100000 == 0) {
                entityLineWriter.flush();
                System.out.println(i);
            }
        }
        entityLineWriter.close();

    }
}
