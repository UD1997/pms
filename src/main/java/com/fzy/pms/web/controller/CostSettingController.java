package com.fzy.pms.web.controller;

import com.fzy.pms.entity.pms.CostSetting;
import com.fzy.pms.entity.rest.Result;
import com.fzy.pms.service.CostSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: ConstSettingController
 * @description:
 * @author: fzy
 * @date: 2019/04/17 23:45:11
 **/
@RestController
@RequestMapping("/cost")
@Api(value = "费用接口",description = "费用相关的接口")
public class CostSettingController {

    @Autowired
    private CostSettingService costSettingService;

    @GetMapping("/all")
    @ApiOperation(value = "查询全部费用",notes = "查询全部费用")
    public Result getCost(@PageableDefault(value = 15, sort = { "create_time" }, direction = Sort.Direction.DESC) Pageable pageable){
        Page<CostSetting> allData = costSettingService.findAll(pageable);
        return Result.success(allData);
    }

    @PostMapping
    @ApiOperation(value = "添加费用",notes = "添加费用")
    public Result create(@Validated @RequestBody CostSetting costSetting){
        costSettingService.create(costSetting);
        return Result.success();
    }

    @PutMapping
    @ApiOperation(value = "修改费用",notes = "修改费用")
    public Result update(@Validated(CostSetting.Update.class) @RequestBody CostSetting cost){
        costSettingService.update(cost);
        return Result.success();
    }

    @DeleteMapping("{id:\\d+}")
    @ApiOperation(value = "删除费用",notes = "根据id 删除费用")
    public Result deleteMenu(@PathVariable Long id){
        costSettingService.delete(id);
        return Result.success();
    }



}
