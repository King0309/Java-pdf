package com.pdf.format.utils;

import com.itextpdf.text.pdf.BaseFont;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class PdfUtils {

    private static final Logger log = LoggerFactory.getLogger(PdfUtils.class);

    /**
     * 把URL转换为PDF
     * @return
     * @throws Exception
     */
    public static boolean htmlToPdf2(String outputFile, String url)
            throws Exception {
        File outFile = new File(outputFile);
        if (!outFile.exists()) {
            outFile.getParentFile().mkdirs();
        }
        OutputStream os = new FileOutputStream(outputFile);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(url);
        String fontPath="/font/simsun.ttc";
        // 解决中文支持问题
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont(fontPath, BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        renderer.layout();
        renderer.createPDF(os);
        os.flush();
        os.close();
        log.info("文件转换成功");
        return true;
    }

}
