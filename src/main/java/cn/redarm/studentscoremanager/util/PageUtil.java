package cn.redarm.studentscoremanager.util;

import cn.redarm.studentscoremanager.model.DTO.PageDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * @Author Redarm
 * @Date 2020/7/5 8:30 下午
 **/
public class PageUtil {

    public static Pageable getPageable(PageDTO pageDTO){
        Integer page = Integer.parseInt(pageDTO.getPage());
        Integer size = Integer.parseInt(pageDTO.getSize());

        Pageable pageable = PageRequest.of(page, size);

        return pageable;
    }
}
