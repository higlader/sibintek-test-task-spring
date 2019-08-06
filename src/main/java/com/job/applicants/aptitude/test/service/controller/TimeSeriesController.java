package com.job.applicants.aptitude.test.service.controller;


import com.job.applicants.aptitude.test.service.data.Point;
import com.job.applicants.aptitude.test.service.data.Quality;
import com.job.applicants.aptitude.test.service.data.Tag;
import com.job.applicants.aptitude.test.service.repositories.TimeSeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class TimeSeriesController {

    private final TimeSeriesRepository timeSeriesRepository;

    @GetMapping("/point")
    public String getListTag(Model modelTag) {
        List<Tag> tagList = timeSeriesRepository.getAllTag().collect(Collectors.toList());
        List<Point> pointList = timeSeriesRepository.getAllPoint().collect(Collectors.toList());
        List<Point> sortedPoint = new ArrayList<>();
        ListIterator<Point> iterator = pointList.listIterator();
        for (Tag tag : tagList) {
            while (iterator.hasNext()) {
                if (iterator.next().getTag().equals(tag) && iterator.next().getQuality().equals(Quality.EXCELLENT)) {
                    sortedPoint.add(iterator.next());
                }
            }
        }
        modelTag.addAttribute("tags", tagList).addAttribute("points", sortedPoint);
        return "point";
    }


}
