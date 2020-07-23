package cn.redarm.studentscoremanager.service;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.PageDTO;
import cn.redarm.studentscoremanager.model.DTO.ScoreNatureDTO;

public interface ScoreNatureService {

    /**
     * @Author Redarm
     * @Description //TODO insert a scoreNature
     * @Date 9:00 下午 2020/7/5
     * @Param [scoreNatureDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult insert(ScoreNatureDTO scoreNatureDTO);

    /**
     * @Author Redarm
     * @Description //TODO query all score nature and returen by page
     * @Date 9:01 下午 2020/7/5
     * @Param [pageDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult queryScoreNaturePage(PageDTO pageDTO);

    /**
     * @Author Redarm
     * @Description //TODO delete a score nature by name
     * @Date 9:01 下午 2020/7/5
     * @Param [name]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult deleteScoreNature(String name);
    
    /**
     * @Author Redarm
     * @Description //TODO query all score nature names
     * @Date 10:56 上午 2020/7/6
     * @Param []
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult queryScoreNatureNames();
}
