package com.kainiu.mall.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//查看服务器文件
//2019-11-30
@Controller
@RequestMapping("/file")
public class CheckFile {
    @Value("${picpath}")
    private String UPLOAD_PATH;

    private final ResourceLoader resourceLoader;

    @Autowired
    public CheckFile(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    //查看图片
    @RequestMapping("/show")
    public ResponseEntity showPhotos(String fileName) {
        try {
            if (fileName != null && fileName != "") {
                // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
                return ResponseEntity.ok(resourceLoader.getResource("file:" + UPLOAD_PATH + fileName));
            } else {
                return ResponseEntity.ok(resourceLoader.getResource("file:" + UPLOAD_PATH + "timg.jpg"));
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //根据商品id获取商品的详细信息返回
    //读取文件的方法
    @RequestMapping("/readFile")
    @ResponseBody
    public List<String> readFile(@RequestBody JSONObject jsonParam) {
        String path = (String) jsonParam.get("path");
//        System.out.println("path:"+path);
        List<String> list = new ArrayList<>();
        File file = new File(UPLOAD_PATH + "/" + path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                list.add("/imgs/" + path + "/" + f.getName());
            }
        }
        return list;
    }

}
