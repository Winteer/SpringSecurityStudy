package com.winter.security.repository;

import com.winter.security.model.Master;
import org.springframework.data.repository.Repository;

/**
 * @ClassName : MasterRepository  //类名
 * @Description :   //描述
 * @Author : Winter  //作者
 * @Date: 2020-08-25 15:06  //时间
 */
public interface MasterRepository extends Repository<Master,Integer> {

    public Master findByMaId(int maId);

    public void save(Master master);
}
