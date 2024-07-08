package com.Website.WebBanHang.Service;


import com.Website.WebBanHang.model.Slide;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Website.WebBanHang.repository.SlideRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SlideService {

    @Autowired
    private final SlideRepository slideRepository;
    public List<Slide> findAllSlide()
    {

        return slideRepository.findByHideTrueOrderByOrderAsc();
    }

}
