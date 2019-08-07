package com.job.applicants.aptitude.test.service.controller;


import com.job.applicants.aptitude.test.service.data.Point;
import com.job.applicants.aptitude.test.service.data.Tag;
import com.job.applicants.aptitude.test.service.repositories.TimeSeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class TimeSeriesController {

    private final TimeSeriesRepository timeSeriesRepository;

    @GetMapping("/point")
    public String getListTag(Model modelTag) {
        List<Tag> tagList = timeSeriesRepository.getAllTag().collect(Collectors.toList());
        List<Point> pointList = timeSeriesRepository.getAllLastFivePoint().collect(Collectors.toList());
        modelTag.addAttribute("tags", tagList).addAttribute("points", pointList);
        return "point";
    }


}
