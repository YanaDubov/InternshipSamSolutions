package com.yana.internship.controller;

import com.yana.internship.bean.TestEntity;
import com.yana.internship.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping
    public @ResponseBody List<TestEntity> getAll(){
        return testService.getAll();
    }

    @GetMapping("/{id}")
    public TestEntity get(@PathVariable int id) {
        return testService.getById(id);
    }

    @DeleteMapping("/{id}")
    public TestEntity deleteById(@PathVariable int id){

        return testService.deleteById(id);
    }

    @PostMapping
    public TestEntity create(@RequestBody @Valid TestEntity bean){
        return testService.create(bean);
    }

    @PutMapping
    protected TestEntity update(@RequestBody @Valid TestEntity bean){

        return testService.update(bean);
    }

}
