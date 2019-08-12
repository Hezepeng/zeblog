package com.zeblog.service.impl;

import com.zeblog.common.ServerResponse;
import com.zeblog.dao.CarouselMapper;
import com.zeblog.entity.Carousel;
import com.zeblog.service.CarouselService;
import javafx.scene.effect.Effect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019-08-12 11:42
 */
@Service
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    CarouselMapper carouselMapper;

    @Override
    public ServerResponse<Carousel> addCarousel(Carousel carousel) {
        if (carousel == null) {
            return ServerResponse.createByErrorMessage("后台未接收到轮播信息");
        }
        int effectRow = 0;
        try {
            effectRow = carouselMapper.insert(carousel);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("发生异常，创建轮播失败");
        }
        if (effectRow == 0) {
            return ServerResponse.createByErrorMessage("发生异常，创建轮播失败");
        }
        carousel = carouselMapper.selectByPrimaryKey(carousel.getCarouselId());
        return ServerResponse.createBySuccess("轮播创建成功", carousel);
    }

    @Override
    public ServerResponse deleteCarousel(Carousel carousel) {
        if (carousel == null) {
            return ServerResponse.createByErrorMessage("后台未接收到轮播信息");
        }
        int effectRow = 0;
        try {
            effectRow = carouselMapper.deleteByPrimaryKey(carousel.getCarouselId());
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("发生异常，删除轮播失败");
        }
        if (effectRow == 0) {
            return ServerResponse.createByErrorMessage("发生异常，删除轮播失败");
        }
        return ServerResponse.createBySuccess("删除成功");
    }

    @Override
    public ServerResponse<Carousel> updateCarousel(Carousel carousel) {
        if (carousel == null) {
            return ServerResponse.createByErrorMessage("后台未接收到轮播信息");
        }
        int effectRow = 0;
        try {
            effectRow = carouselMapper.updateByPrimaryKey(carousel);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("发生异常，轮播更新失败");
        }
        if (effectRow == 0) {
            return ServerResponse.createByErrorMessage("发生异常，轮播更新失败");
        }
        carousel = carouselMapper.selectByPrimaryKey(carousel.getCarouselId());
        return ServerResponse.createBySuccess("轮播更新成功", carousel);
    }

    @Override
    public ServerResponse<List<Carousel>> getAllCarousel() {
        return ServerResponse.createBySuccess(carouselMapper.selectAll());
    }
}
