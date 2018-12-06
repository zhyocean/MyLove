package com.zhy.love.controller;

import com.zhy.love.model.DailySpeech;
import com.zhy.love.service.TodayService;
import com.zhy.love.utils.FileUtil;
import com.zhy.love.utils.TimeUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.security.Principal;
import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/11/28 14:51
 * Describe: 我的日常
 */
@RestController
public class TodayControl {

    @Autowired
    TodayService todayService;

    @PostMapping("/commitTodayWords")
    public JSONObject publishISay(DailySpeech dailySpeech,
                                  HttpServletRequest request){
        JSONObject returnJson = new JSONObject();

        List<MultipartFile> multipartFiles = ((MultipartHttpServletRequest)request).getFiles("picture");

        FileUtil fileUtil = new FileUtil();
        TimeUtil timeUtil = new TimeUtil();
        String picPath = this.getClass().getResource("/").getPath().substring(1) + "dailySpeechImg/";

        File file;
        StringBuilder picsUrlStrBuilder = new StringBuilder();
        //判断是否有上传的图片
        long publishTime;
        for (MultipartFile multipartFile : multipartFiles){
            //上传图片
            publishTime = System.currentTimeMillis();
            file = fileUtil.multipartFileToFile(multipartFile, picPath, publishTime + ".jpeg");
            if(file != null){
                String url = fileUtil.uploadFile(file, "dailySpeech/" + timeUtil.getFormatDateForThree());
                picsUrlStrBuilder.append(url).append(",");
            }
        }
        //设置上传的图片
        String picsUrlStr = picsUrlStrBuilder.toString();
        if(!"".equals(picsUrlStr) && picsUrlStr.length() > 0){
            dailySpeech.setPicsUrl(picsUrlStr.substring(0, picsUrlStr.lastIndexOf(",")));
        }

        returnJson = todayService.publishISay(dailySpeech);
        return returnJson;
    }

    @PostMapping("/getTodayInfo")
    public JSONObject getTodayInfo(@RequestParam("rows") String rows,
                                   @RequestParam("pageNum") String pageNum){

        return todayService.getTodayInfo(Integer.parseInt(rows), Integer.parseInt(pageNum));

    }

}
