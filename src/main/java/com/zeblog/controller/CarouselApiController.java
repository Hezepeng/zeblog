package com.zeblog.controller;

import com.zeblog.common.ServerResponse;
import com.zeblog.entity.Carousel;
import com.zeblog.interceptor.AdminInterceptor;
import com.zeblog.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019-08-12 11:33
 */

@Controller
@RequestMapping("api/carousel/")
public class CarouselApiController {

    @Autowired
    CarouselService carouselService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    @AdminInterceptor
    public ServerResponse addCarousel(HttpServletRequest request, @RequestBody Carousel carousel) {
        return carouselService.addCarousel(carousel);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    @AdminInterceptor
    public ServerResponse deleteCarousel(HttpServletRequest request, @RequestBody Carousel carousel) {
        return carouselService.deleteCarousel(carousel);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    @AdminInterceptor
    public ServerResponse updateCarousel(HttpServletRequest request, @RequestBody Carousel carousel) {
        return carouselService.updateCarousel(carousel);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getAllCarousel(HttpServletRequest request) {
        return carouselService.getAllCarousel();
    }

    @RequestMapping(value = "upload",method = RequestMethod.POST)
    @ResponseBody
    @AdminInterceptor
    public ServerResponse uploadImage(HttpServletRequest request, MultipartFile file){
        return null;
    }


}
