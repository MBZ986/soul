package cn.smartrick.soul.controller;

import cn.smartrick.soul.config.UploadProperties;
import cn.smartrick.soul.dto.Msg;
import cn.smartrick.soul.util.StringUtil;
import cn.smartrick.soul.util.UploadFileQiniu;
import com.qiniu.common.QiniuException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import cn.smartrick.soul.constant.ResponseStatus;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
@RestController
@RequestMapping("/yun")
public class FileHandlerController {

    @Autowired
    private UploadFileQiniu uploadFile;

    /**
     * 解析文件公网地址
     * @param fileNames
     * @return
     */
    @PostMapping("/parses")
    public Msg parse(String[] fileNames) {
        // 解析文件公开文件地址
        log.info(Arrays.toString(fileNames));
        Msg msg = new Msg();
        ArrayList<String> urls = new ArrayList<String>();
        if (fileNames!=null||fileNames.length>0) {
            for (String filekey : fileNames) {
                String downloadPrivatePath = uploadFile.getDownloadPrivatePath(filekey);
                urls.add(downloadPrivatePath);
            }
            msg.setMsg("地址解析成功");
            msg.setCode(ResponseStatus.SUCCESS.getCode());
            msg.setData(urls);
        } else {
            msg.setMsg("解析地址为空");
            msg.setCode(ResponseStatus.FAIL.getCode());
        }
        return msg;
    }

    @GetMapping("/parse")
    public Msg parse(String fileName) {
        // 解析文件公开文件地址
        Msg msg = new Msg();
        log.info(fileName);
        if (!StringUtils.isEmpty(fileName)) {
            String downloadPrivatePath = uploadFile.getDownloadPrivatePath(fileName);
            if (StringUtil.notEmpty(downloadPrivatePath)) {
                msg.setMsg("地址解析成功");
                msg.setCode(ResponseStatus.SUCCESS.getCode());
                msg.setData(downloadPrivatePath);
            } else {
                msg.setMsg("地址解析失败");
                msg.setCode(ResponseStatus.FAIL.getCode());
            }
        } else {
            msg.setMsg("解析地址为空");
            msg.setCode(ResponseStatus.FAIL.getCode());
        }
        return msg;
    }


    /**
     * 上传文件
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Msg uploadFile(MultipartFile file) {
        // 上传到七牛云
        Msg msg = new Msg();
        if (file != null) {
            String fileKey = uploadFile.uploadFile(file);
            msg.setMsg("上传成功");
            msg.setData(fileKey);
            msg.setCode(ResponseStatus.SUCCESS.getCode());
        } else {
            msg.setMsg("上传文件为空");
            msg.setCode(ResponseStatus.FAIL.getCode());
        }
        return msg;
    }

    /**
     * 下载文件
     *
     * @param fileKey
     * @param response
     */
    @GetMapping("/{fileKey}")
    public void downloadFile(@PathVariable("fileKey") String fileKey, HttpServletResponse response) {
        ServletOutputStream outputStream = null;
        try {
            if (StringUtil.notEmpty(fileKey)) {
                response.setHeader("Content-Type", "image/jpeg");
                outputStream = response.getOutputStream();
                outputStream.write(uploadFile.loadFile(fileKey));
            } else {
                PrintWriter writer = response.getWriter();
                writer.print("图片参数错误");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 删除文件
     *
     * @param fileKey
     */
    @DeleteMapping("/{fileKey}")
    public Msg deleteFile(@PathVariable("fileKey") String fileKey) {
        Msg msg = null;
        try {
            msg = Msg.res(uploadFile.delete(fileKey)==200,"删除","文件");
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return msg;
    }




}