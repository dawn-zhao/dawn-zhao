package com.dawn.zhao.workbook;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Gmy on 2017/6/2.
 */
public class ApachePOIUtil {

    /**
     * @apiNote 暂时不支持继承关系的类，暂时对excel每页的sheet数量做上线处理
     */
    public static <T> Workbook writeCountryListToFile(String fileName, String sheetName,
                                                      List<String> headers,
                                                      List<T> body, Class<T> tClass, Integer sheetCount) throws Exception {
        if (StringUtils.isBlank(fileName)) {
            throw new Exception("缺少文件名");
        }
        if (tClass == null) {
            throw new Exception("缺少body类别");
        }

        if (body == null || body.size() == 0) {
            throw new Exception("缺少body");
        }
        Workbook workbook;
        if (fileName.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (fileName.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new Exception("invalid file name, should be xls or xlsx");
        }
        process(workbook, sheetName, headers, body, tClass, sheetCount);
        return workbook;
    }

    public static <T> void process(Workbook workbook, String sheetName, List<String> headers, List<T> body,
                                   Class tClass, Integer sheetCount) throws IllegalAccessException {
        sheetCount = sheetCount == null ? 1000000 : sheetCount;
        //根据反射获取注解中的列标题
        Field[] fields = tClass.getDeclaredFields();
        //过滤只有带有注解的fields，并保证数序
        List<Field> fieldsFilter = Arrays.stream(fields).filter(e -> e.getAnnotation(Column.class) != null)
                .sorted(Comparator.comparingInt(o -> o.getAnnotation(Column.class).ordered()))
                .collect(Collectors.toList());

        Sheet sheet = workbook.createSheet(sheetName);
        int rowIndex = processTitle(sheet, headers, fieldsFilter);
        //迭代数据
        Integer count = 1;
        Integer tab = 1;
        for (T rowData : body) {
            //代表进行到下一sheet
            if (count % sheetCount == 0) {
                sheet = workbook.createSheet(String.format("%s-%s", sheetName, tab++));
                processTitle(sheet, headers, fieldsFilter);
                rowIndex = processTitle(sheet, headers, fieldsFilter);
            }
            Row rowBody = sheet.createRow(rowIndex++);
            int fieldIndex = 0;
            for (Field field : fieldsFilter) {
                //设置访问权限修饰符级别
                field.setAccessible(true);
                //获取注解的属性value值，并设置单元格数据
                Object fieldValue = field.get(rowData);
                if (fieldValue instanceof Date && StringUtils.isNotEmpty(field.getAnnotation(Column.class).format())) {
                    rowBody.createCell(fieldIndex).setCellValue(DateFormatUtils.format(
                            (Date) fieldValue, field.getAnnotation(Column.class).format()));
                } else {
                    rowBody.createCell(fieldIndex).setCellValue(field.get(rowData).toString());
                }
                fieldIndex++;
            }
            count++;
        }
    }


    public static int processTitle(Sheet sheet, List<String> headers, List<Field> fieldsFilter) {
        //构建头信息
        int rowIndex = 0;
        if (headers != null && headers.size() > 0) {
            for (String header : headers) {
                Row rowHeader = sheet.createRow(rowIndex++);
                rowHeader.createCell(0).setCellValue(header);
            }
        }
        //根据反射获取注解中的列标题
        Row rowHeader = sheet.createRow(rowIndex++);
        int index = 0;
        for (Field field : fieldsFilter) {
            //获取注解的属性value值
            String value = field.getAnnotation(Column.class).value();
            rowHeader.createCell(index++).setCellValue(value);
        }
        return rowIndex;
    }

    public static class CardExportDataModel {

        public CardExportDataModel(String index, String code, String pwd, Date date) {
            this.index = index;
            this.code = code;
            this.pwd = pwd;
            this.date = date;
        }

        @Column(value = "卡劵密码", ordered = 3)
        private String pwd;

        @Column(value = "序号", ordered = 1)
        private String index;

        @Column(value = "卡劵号", ordered = 2)
        private String code;

        @Column(value = "生成时间", ordered = 4, format = "yyyy-MM-dd HH:mm:ss")
        private Date date;
    }

    public static void main(String args[]) throws Exception {
        List<String> headers = new ArrayList<>();
        headers.add("您正在使用指点无限-SaaS中心-卡券系统 生成的卡券");
        headers.add("名称：未来影院");
        headers.add("卡券类别：影院优惠卷");
        headers.add("有效期：365天");
        headers.add("入库渠道：测试渠道");
        headers.add("激活状态：已激活");
        headers.add("入库编号：11111111111");
        List<CardExportDataModel> body = new ArrayList<>();
        body.add(new CardExportDataModel("1", "a", "098", new Date()));
        body.add(new CardExportDataModel("2", "b", "098", new Date()));
        body.add(new CardExportDataModel("3", "c", "098", new Date()));
        String fileName = "卡劵-2017-06-02.xls";
        String sheetName = "卡劵列表";
        Workbook workbook = writeCountryListToFile(fileName, sheetName, headers, body, CardExportDataModel.class, 2);
        try (FileOutputStream fileOut = new FileOutputStream("D:\\IdeaProjects\\cias-report-boa\\cias-report-boa-api\\src\\main\\java\\com\\ariadnethread\\cias" + fileName)) {
            workbook.write(fileOut);
        }
    }
}