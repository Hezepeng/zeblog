package com.zeblog.dao;

import com.zeblog.entity.Carousel;
import java.util.List;

public interface CarouselMapper {
    int deleteByPrimaryKey(Integer carouselId);

    int insert(Carousel record);

    Carousel selectByPrimaryKey(Integer carouselId);

    List<Carousel> selectAll();

    int updateByPrimaryKey(Carousel record);
}