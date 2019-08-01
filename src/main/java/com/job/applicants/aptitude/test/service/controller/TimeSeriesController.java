package com.job.applicants.aptitude.test.service.controller;


import com.job.applicants.aptitude.test.service.repositories.TimeSeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TimeSeriesController {

    private TimeSeriesRepository timeSeriesRepository;

    @GetMapping("/points")
    public String getListTag(){
        return "point";
    }


}
