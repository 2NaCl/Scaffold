package me.zhengjie.modules.system.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.domain.Dept;
import me.zhengjie.modules.system.service.DeptService;
import me.zhengjie.modules.system.service.dto.DeptDto;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Api(tags = "系统：部门管理")
@RequestMapping
public class TestController {

    private DeptService deptService;

    @ApiOperation("test")
    @GetMapping(value = "/api/test")
    public String download() throws Exception {
        System.out.println("123123123");
        return "hahaha";
    }

    @ApiOperation("test select")
    @PostMapping(value = "/api/select")
    public Dept select(@RequestBody Dept dept){
        return dept;
    }

    @ApiOperation("test add")
    @PostMapping(value = "/api/add")
    public Integer add(@RequestBody Dept dept){
        deptService.create(dept);
        return 1;
    }

    @ApiOperation("test update")
    @PutMapping(value = "/api/put")
    public Integer update(Dept dept){
        deptService.update(dept);
        return 1;
    }

}
