package me.zhengjie.modules.system.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.domain.Dept;
import me.zhengjie.modules.system.domain.Test;
import me.zhengjie.modules.system.service.DeptService;
import me.zhengjie.modules.system.service.dto.DeptDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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

    @ApiOperation("selectById")
    @PostMapping("/api/selectByName")
    public ResponseEntity<Object> selectByName(@RequestBody Map<String,Object> map){
        Test test = new Test();
        System.out.println(map.get("name").toString());
        test.setName(map.get("name").toString());
        test.setRegion("金辉大厦");
        List<Test> l = new ArrayList<>();
        l.add(test);
        return new ResponseEntity<>(l,HttpStatus.OK);
    }

    @ApiOperation("test select")
    @PostMapping(value = "/api/select")
    public ResponseEntity<Object> select(@RequestBody Test test){
        ArrayList<Test> arrayList = new ArrayList();
        arrayList.add(test);
        arrayList.add(test);
        arrayList.add(test);
        arrayList.add(test);
        return new ResponseEntity<>(arrayList, HttpStatus.OK);
    }

    @ApiOperation("test add")
    @PostMapping(value = "/api/add")
    public Integer add(@RequestBody Test dept){
        return 1;
    }

    @ApiOperation("test update")
    @PutMapping(value = "/api/put")
    public Integer update(Dept dept){
        deptService.update(dept);
        return 1;
    }

}
