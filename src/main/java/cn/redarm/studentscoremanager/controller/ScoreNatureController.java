package cn.redarm.studentscoremanager.controller;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.PageDTO;
import cn.redarm.studentscoremanager.model.DTO.ScoreNatureDTO;
import cn.redarm.studentscoremanager.service.ScoreNatureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author Redarm
 * @Date 2020/7/5 9:13 下午
 **/
@RestController
@RequestMapping(value = "/api/scoreNature")
@Api(value = "score_nature_controller")
public class ScoreNatureController {

    @Autowired
    private ScoreNatureService scoreNatureService;

    @PostMapping(value = "insertScoreNature")
    @ApiOperation(value = "insert_score_nature")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult insertScoreNature(@RequestBody @Valid ScoreNatureDTO scoreNatureDTO){
        return scoreNatureService.insert(scoreNatureDTO);
    }

    @PostMapping(value = "queryScoreNaturePage")
    @ApiOperation(value = "query_score_nature_page")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult queryScoreNaturePage(@RequestBody @Valid PageDTO pageDTO){
        return scoreNatureService.queryScoreNaturePage(pageDTO);
    }

    @PostMapping(value = "deleteScoreNature")
    @ApiOperation(value = "delete_score_nature")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CommonResult deleteScoreNature(@RequestBody String name){
        return scoreNatureService.deleteScoreNature(name);
    }

    @GetMapping(value = "queryScoreNatureNames")
    @ApiOperation(value = "query_score_nature_names")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult queryScoreNatureNames(){
        return scoreNatureService.queryScoreNatureNames();
    }
}
