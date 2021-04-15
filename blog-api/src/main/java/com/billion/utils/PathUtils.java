package com.billion.utils;

import com.billion.common.annotation.LogAnnotation;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 文件路径相关工具包
 * @author Billion
 * @create 2021/04/08 11:23
 */
public class PathUtils {
    private PathUtils(){
    }

    /**
     * 获取上传文件位置
     * @param uploadChildPath 上传文件相对路径，格式如：static/api/image/adavar/
     * @param filename 上传文件名
     * @return
     */
    @LogAnnotation(module = "文件路径模块", operation = "获取文件上传地址：路径+文件名")
    public static File getUploadDest(String uploadChildPath, String filename){
        //1.获取根目录
        String rootPath = getClassPath();
        File rootFile = new File(rootPath);
        //2.获取上传完整路径，不包含文件名
        File uploadPath = new File(rootFile.getAbsolutePath(), uploadChildPath);
        if(!uploadPath.exists()) uploadPath.mkdirs();
        //获取上传完整路径，包含文件名
        File uploadFile = new File(uploadPath, filename);
        return uploadFile;
    }

    /**
     * 成文件的网络URL:通过Nginx服务器映射到后端路径
     * @param scheme 请求协议
     * @param proxyServer Nginx服务器IP+port，如：localhost:80
     * @param childPath 相对地址：如：/api/image/adavar/
     * @param filename
     * @return
     */
    @LogAnnotation(module = "文件路劲模块", operation = "获取上传文件的URL")
    public static String getUploadFileUrl(String scheme, String proxyServer, String childPath, String filename){
        return scheme + "://" + proxyServer + childPath + filename;
    }

    /**
     * 在开发测试模式时，得到的地址为：{项目根目录}/target/classes/
     * 在打包成jar正式发布时，得到的地址为：{发布jar包目录}/
     * @return
     */
    @LogAnnotation(module = "文件路径模块", operation = "获取开发或生产环境下的classpath路径")
    public static String getClassPath(){
        try {
            return ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
}
