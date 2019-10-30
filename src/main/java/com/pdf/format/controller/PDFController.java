package com.pdf.format.controller;

import com.pdf.format.utils.PdfUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value = "/pdf")
public class PDFController {

    @PostMapping(value = "/test")
    public String formatPDF(HttpServletRequest request, HttpServletResponse response, String url){
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");//设置日期格式
            String format = df.format(new Date());
            boolean ret = PdfUtils.htmlToPdf2("D:/dd.pdf", url);
            if(ret){
                File f = new File("D:/dd.pdf");
                out = new BufferedOutputStream(response.getOutputStream());
                in = new BufferedInputStream(new FileInputStream(f));
                response.setContentType(new MimetypesFileTypeMap().getContentType(f));// 设置response内容的类型
                String filename = format+"-座次表";
                String encoding = request.getCharacterEncoding();
                if ("UTF-8".equals(encoding)) {
                    filename = URLEncoder.encode(filename, "UTF-8");
                } else {
                    filename = new String(filename.getBytes("utf-8"), "ISO8859-1");
                }
                response.setHeader("Content-disposition", "attachment;filename=" + filename + "." + "pdf");// 设置头部信息
                byte[] buffer = new byte[1024];
                int length = 0;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                out.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
            return "失败";
        }
        return "成功";
    }

}
