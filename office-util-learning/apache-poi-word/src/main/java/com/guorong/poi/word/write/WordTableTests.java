package com.guorong.poi.word.write;

import org.apache.poi.xwpf.usermodel.*;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * apache-poi word的表格操作
 * @author guorong
 * @date 2021-10-28
 */
public class WordTableTests {

    private static final String path = System.getProperty("user.dir") + "/file";


    @Test
    public void test() throws IOException {
        Path path = Paths.get(WordTableTests.path, "a.docx");
        OutputStream outputStream = Files.newOutputStream(path);
        XWPFDocument document= new XWPFDocument();
        document.write(outputStream);
        outputStream.close();
    }

    @Test
    public void testTable() throws IOException {
        // 创建word文档
        XWPFDocument document = new XWPFDocument();

        // 表格===============================================
        // 创建表格
        XWPFTable table = document.createTable(6,2);
        // 表格宽度
        // table.setWidthType(TableWidthType.PCT);
        table.setWidth("90%");
        // 表格水平位置：TableRowAlign.CENTER 代表水平居中
        table.setTableAlignment(TableRowAlign.CENTER);

        // 单元格==============================================
        XWPFTableRow rowOne = table.getRow(0);
        // 第一行第一列
        XWPFTableCell rowOneCellOne = rowOne.getCell(0);
        rowOneCellOne.getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER); // 设置单元格段落(内容)居中
        XWPFRun run = rowOneCellOne.getParagraphs().get(0).createRun(); // 将新的运行附加到此段落
        run.setBold(true); // 单元格段落(内容)加粗
        run.setFontSize(15); // 单元格段落(内容)字大小
        run.setFontFamily("黑体"); // 字体
        run.setText("时间"); // 单元格内容
        // 第一行第二列
        XWPFTableCell rowOneCellTwo = rowOne.getCell(1);
        rowOneCellTwo.setText("监控报告");

        // 写出文件
        Path path = Paths.get(WordTableTests.path, System.currentTimeMillis() + ".docx");
        OutputStream outputStream = Files.newOutputStream(path);
        document.write(outputStream);
        outputStream.close();
    }

}
