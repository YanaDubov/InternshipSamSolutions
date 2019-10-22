package com.yana.internship.controller;

import com.yana.internship.bean.TestBean;
import com.yana.internship.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping()
    public @ResponseBody List<TestBean> getAll(){
        return testService.getAll();
    }

    @GetMapping("/{id}")
    public TestBean getBook(@PathVariable int id) {
        return testService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        testService.deleteById(id);
    }

    @PostMapping
    public void put(@RequestBody TestBean bean){
        testService.put(bean);
    }

}
