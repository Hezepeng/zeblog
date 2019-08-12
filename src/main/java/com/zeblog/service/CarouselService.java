package com.zeblog.service;

import com.zeblog.common.ServerResponse;
import com.zeblog.entity.Carousel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019-08-12 11:39
 */
public interface CarouselService {
    ServerResponse<Carousel> addCarousel(Carousel carousel);

    ServerResponse deleteCarousel(Carousel carousel);

    ServerResponse<Carousel> updateCarousel(Carousel carousel);

    ServerResponse<List<Carousel>> getAllCarousel();
}
