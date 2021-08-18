package com.sswu.crople.controller;

import com.sswu.crople.dto.PageRequestDTO;
import com.sswu.crople.dto.ReviewDTO;
import com.sswu.crople.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/place/review")
@Log4j2
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;


    private long placeId;
    private int page;


    @GetMapping("/register")
    public void reviewRegister( long placeId, int page, Model model){
        log.info("ReviewsController의 register 메소드-----------------placeId : " + placeId + ", page : " + page);

        this.placeId = placeId;
        this.page = page;
        System.out.print("-------------------------------------");
        String principle = SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication().getPrincipal().toString();
        String email = principle.substring(principle.lastIndexOf("email=")+6);
        email = email.substring(0,email.indexOf(","));
        System.out.println(email);


        model.addAttribute("placeId", placeId);
        model.addAttribute("email", email);

    }

    @PostMapping("/register")
    public RedirectView reviewRegister(ReviewDTO reviewDTO, RedirectAttributes redirectAttributes){
        log.info("reviewDTO: " + reviewDTO);

        // PlaceService의 register 메소드를 이용해 place를 등록
        Long reviewId = reviewService.register(reviewDTO);

        // 이미지
        return new RedirectView("/place/read?placeId="+reviewDTO.getPlaceId());
    }
}
