package com.zhoulx.gavisualization.controller;


import com.zhoulx.gavisualization.service.TSPService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
public class TSPController {
    @Autowired
    private TSPService tspService;

    @RequestMapping("/index")
    public ModelAndView getAllCities() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("cities", tspService.getAllCities());
        mav.setViewName("index");
        return mav;
    }


    @ResponseBody
    @PostMapping("/schemas/{start}/{schemasCount}")
    public ResponseEntity getRouteWithoutParameters(@RequestBody List<String> cities, @PathVariable String start, @PathVariable String schemasCount) throws IOException {

        return ResponseEntity.ok(tspService.process(cities, start, Integer.valueOf(schemasCount)));
    }
}
