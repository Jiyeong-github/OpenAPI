package com.kedu.user;

import com.kedu.user.VO.InsertEntity;
import com.kedu.user.VO.LocationCodeEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodayMapper {
    List<LocationCodeEntity> selLocationCode();
    int insApartmentInfoArr(InsertEntity param);
}
