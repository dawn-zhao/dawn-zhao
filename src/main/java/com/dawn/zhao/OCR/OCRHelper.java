package com.dawn.zhao.OCR;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.tomcat.jni.OS;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OCRHelper
{
    public static void main(String[] args) throws TesseractException {
//        try {
//
//            File testDataDir = new File("C:\\Users\\76456\\Desktop\\images");
//            System.out.println(testDataDir.listFiles().length);
//            int i = 0 ;
//            for(File file :testDataDir.listFiles())
//            {
//                i++ ;
//                String recognizeText = new OCRHelper().recognizeText(file);
//                System.out.print(recognizeText+"\t");
//
//                if( i % 5  == 0 )
//                {
//                    System.out.println();
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        System.out.println(System.getenv("TESSDATA_PREFIX"));
//        File imageFile = new File("C:\\Users\\76456\\Desktop\\images\\代码2.png");
//        Tesseract instance = Tesseract.getInstance();
//        instance.setDatapath("K:\\tools\\tesseract\\Tesseract-OCR\\tessdata");//设置训练库的位置
//        instance.setLanguage("chi_sim");//中文识别
//        String result = instance.doOCR(imageFile);
//        System.out.println(result);


    }
    private final String LANG_OPTION = "-l";
    private final String EOL = System.getProperty("line.separator");
    /**
     * 文件位置我放在，项目同一路径
     */
    private String tessPath = new File("tesseract").getAbsolutePath();

    /**
     * @param imageFile
     *            传入的图像文件
     * @return 识别后的字符串
     */
    public String recognizeText(File imageFile) throws Exception
    {
        /**
         * 设置输出文件的保存的文件目录
         */
        File outputFile = new File(imageFile.getParentFile(), "output");

        StringBuffer strB = new StringBuffer();
        List<String> cmd = new ArrayList<String>();
        cmd.add(tessPath + "\\tesseract");
//        if (OS.IS_WIN32) {
//            cmd.add(tessPath + "\\tesseract");
//        } else if (OS.IS_LINUX) {
//            cmd.add("tesseract");
//        } else {
//            cmd.add(tessPath + "\\tesseract");
//        }
        cmd.add("");
        cmd.add(outputFile.getName());
        cmd.add(LANG_OPTION);
        cmd.add("chi_sim");
        cmd.add("eng");

        ProcessBuilder pb = new ProcessBuilder();
        /**
         *Sets this process builder's working directory.
         */
        pb.directory(imageFile.getParentFile());
        cmd.set(1, imageFile.getName());
        pb.command(cmd);
        pb.redirectErrorStream(true);
        Process process = pb.start();
        // tesseract.exe 1.jpg 1 -l chi_sim
        // Runtime.getRuntime().exec("tesseract.exe 1.jpg 1 -l chi_sim");
        /**
         * the exit value of the process. By convention, 0 indicates normal
         * termination.
         */
//      System.out.println(cmd.toString());
        int w = process.waitFor();
        if (w == 0)// 0代表正常退出
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    new FileInputStream(outputFile.getAbsolutePath() + ".txt"),
                    "UTF-8"));
            String str;

            while ((str = in.readLine()) != null)
            {
                strB.append(str).append(EOL);
            }
            in.close();
        } else
        {
            String msg;
            switch (w)
            {
                case 1:
                    msg = "Errors accessing files. There may be spaces in your image's filename.";
                    break;
                case 29:
                    msg = "Cannot recognize the image or its selected region.";
                    break;
                case 31:
                    msg = "Unsupported image format.";
                    break;
                default:
                    msg = "Errors occurred.";
            }
            throw new RuntimeException(msg);
        }
        new File(outputFile.getAbsolutePath() + ".txt").delete();
        return strB.toString().replaceAll("\\s*", "");
    }
}