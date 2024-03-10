package com.guorong.poi.word.write;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * apache poi 中段落操作
 * @author guorong
 * @date 2021-10-29
 */
public class WordParagraphTests {

    private static final String PATH = System.getProperty("user.dir") + "/file";

    @Test
    public void test() throws IOException {
        XWPFDocument document = new XWPFDocument();
        // 创建段落
        XWPFParagraph paragraph = document.createParagraph();
        // 设置段落居中
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        run.setText("关关雎鸠，在河之洲。窈窕淑女，君子好逑。"); // 设置段落文本内容
        run.setBold(true); // 加粗

        // 写出文件
        Path path = Paths.get(PATH, System.currentTimeMillis() + ".docx");
        OutputStream outputStream = Files.newOutputStream(path);
        document.write(outputStream);
        outputStream.close();
    }
}
