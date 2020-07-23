package cn.redarm.studentscoremanager.service.impl;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.PageDTO;
import cn.redarm.studentscoremanager.model.DTO.ScoreNatureDTO;
import cn.redarm.studentscoremanager.model.entity.ScoreNature;
import cn.redarm.studentscoremanager.repository.ScoreNatureRepository;
import cn.redarm.studentscoremanager.service.ScoreNatureService;
import cn.redarm.studentscoremanager.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @Author Redarm
 * @Date 2020/7/5 9:01 下午
 **/
@Service
@Slf4j
public class ScoreNatureServiceImpl implements ScoreNatureService {

    @Autowired
    private ScoreNatureRepository scoreNatureRepository;

    /**
     * @Author Redarm
     * @Description //TODO insert score nature
     * @Date 9:02 下午 2020/7/5
     * @Param [scoreNatureDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    public CommonResult insert(ScoreNatureDTO scoreNatureDTO) {
        if (scoreNatureRepository.findByName(scoreNatureDTO.getName()) != null){
            return CommonResult.failed("There already have a score nature called " + scoreNatureDTO.getName());
        }

        ScoreNature scoreNature = new ScoreNature();
        scoreNature.setName(scoreNatureDTO.getName());

        scoreNatureRepository.save(scoreNature);

        return CommonResult.success("insert success");
    }

    /**
     * @Author Redarm
     * @Description //TODO query all score nature implement
     * @Date 9:03 下午 2020/7/5
     * @Param [pageDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    public CommonResult queryScoreNaturePage(PageDTO pageDTO) {
        Pageable pageable = PageUtil.getPageable(pageDTO);

        Page<ScoreNature> scoreNatures = scoreNatureRepository.findAll(pageable);

        return CommonResult.success(scoreNatures);
    }

    /**
     * @Author Redarm
     * @Description //TODO delete score nature implement
     * @Date 9:03 下午 2020/7/5
     * @Param [name]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    @Modifying
    @Transactional
    public CommonResult deleteScoreNature(String name) {
        if (StringUtils.isEmpty(name)){
            return CommonResult.failed("name can't be null");
        }

        String[] names = name.split(":");

        name = names[1].substring(1, names[1].length() - 2);

        Integer num = 0;

        num = scoreNatureRepository.deleteAllByName(name);

        if (num == 0){
            return CommonResult.failed("delete error");
        }

        return CommonResult.success("delete success");
    }

    /**
     * @Author Redarm
     * @Description //TODO query score nature names implement
     * @Date 11:00 上午 2020/7/6
     * @Param []
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    public CommonResult queryScoreNatureNames() {
        List<ScoreNature> scoreNatures = scoreNatureRepository.findAll();

        List<Map<String, Object>> list = null;
        list = scoreNatures.stream()
                .map(it -> {
                    Map<String, Object> map = new HashMap<>();

                    map.put("value", it.getName());
                    map.put("label", it.getName());

                    return map;
                }).collect(Collectors.toList());

        return CommonResult.success(list);
    }
}
