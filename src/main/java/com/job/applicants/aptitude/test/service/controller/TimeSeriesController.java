package com.job.applicants.aptitude.test.service.controller;


import com.job.applicants.aptitude.test.service.data.Point;
import com.job.applicants.aptitude.test.service.data.Tag;
import com.job.applicants.aptitude.test.service.mock.H2Point;
import com.job.applicants.aptitude.test.service.mock.H2Tag;
import com.job.applicants.aptitude.test.service.repositories.TimeSeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class TimeSeriesController {

    private final TimeSeriesRepository timeSeriesRepository;

    @GetMapping("/point")
    public String getListTag(Model modelTag,Model modelPoit){
        Stream<Tag> tags = timeSeriesRepository.getAllTag();
        List<Tag> tagList = tags.collect(Collectors.toList());
/*        Stream<H2Tag> h2TagStream = timeSeriesRepository.getAllH2Tag();
        Stream<H2Point> h2PointStream = timeSeriesRepository.getAllH2Point();*/
       // Stream<Point> point = timeSeriesRepository.getAllPoints(tagList);
        List<Point> pointList = timeSeriesRepository.getAllPoints().collect(Collectors.toList());
        modelTag.addAttribute("tags",tagList);
        //modelPoit.addAttribute("point",pointList);
        return "point";
    }


}
