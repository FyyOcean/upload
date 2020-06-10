package com.baizhi.controller;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Fy_ on 2020/6/9 17:17
 */
@Controller
@RequestMapping("file")
public class FileController {

    //处理文件下载的操作
    @RequestMapping("down")
    public void download(String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //或许文件名 根据文件名去指定目录读取文件
        //根据相对路径获取绝对路径
        String realPath = request.getServletContext().getRealPath("/files/download");
        FileInputStream is = new FileInputStream(new File(realPath, fileName));
        //设置响应头 attachment/inline 附件/在线打开
        response.setHeader("content-disposition","inline;fileName="+ URLEncoder.encode(fileName,"UTF-8"));
        //通过响应流响应即可
        ServletOutputStream outputStream = response.getOutputStream();
        //流的复制
        IOUtils.copy(is,outputStream);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(outputStream);





    }
    //处理文件上传操作
    @RequestMapping("upload")
    public String upload(MultipartFile aaa ,HttpServletRequest request) throws IOException {
        System.out.println("文件名"+aaa.getOriginalFilename());
        System.out.println("文件名类型"+aaa.getContentType());
        System.out.println("文件大小"+aaa.getSize());
        String realPath = request.getSession().getServletContext().getRealPath("/files");
        //根据相对路径获取绝对路径
        //创建时间文件夹
        String format=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        File file = new File(realPath, format);
        if(!file.exists()) file.mkdirs();
        //获取后缀
        String extension = FilenameUtils.getExtension(aaa.getOriginalFilename());
        String newFileNamePrefix= UUID.randomUUID().toString().replace("-","")+
                new SimpleDateFormat("yyyyMMddHHmmSSS").format(new Date());
        String newFileName= newFileNamePrefix+"."+extension;
        //根据文件后缀动态获取文件类型
        String mimeType = request.getSession().getServletContext().getMimeType("." + extension);
        System.out.println("动态根据文件后缀获取文件类型"+mimeType);

        //处理上传操作
        aaa.transferTo(new File(file,newFileName));

        return "redirect:/upload.jsp";
    }
}
