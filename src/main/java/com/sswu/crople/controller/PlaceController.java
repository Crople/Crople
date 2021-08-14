package com.sswu.crople.controller;

import com.sswu.crople.dto.PageRequestDTO;
import com.sswu.crople.dto.PlaceDTO;
import com.sswu.crople.dto.ReviewDTO;
import com.sswu.crople.service.PlaceService;
import com.sswu.crople.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// /place에 대한 요청을 처리하는 컨트롤러
@Controller
@RequestMapping("/place")
@Log4j2
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/register")
    public void register(){

    }

    // /place/register POST 요청시 호출되는 메서드
    @PostMapping("/register")
    public String register(PlaceDTO placeDTO, RedirectAttributes redirectAttributes){
        log.info("placeDTO: " + placeDTO);

        // PlaceService의 register 메소드를 이용해 place를 등록
        Long placeId = placeService.register(placeDTO);

        redirectAttributes.addFlashAttribute("msg", placeId);

        // post 작업이 끝나고 /place/list로 리다이렉트
        return "redirect:/place/list";
    }

    // /place/list GET 요청시 호출되는 메서드
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        log.info("pageRequestDTO: " + pageRequestDTO);

        if(pageRequestDTO.getType() == null){
            pageRequestDTO.setType("밥집");
        }

        // result라는 이름의 attribute로 pageRequestDTO의 page, size에 해당하는 List<PlaceDTO>를 html로 전송
        model.addAttribute("result", placeService.getList(pageRequestDTO));
    }

    // /place/read, /place/modify GET 요청시 호출되는 메서드
    @GetMapping({"/read", "modify"})
    public void read(long placeId, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model){
        log.info("placeId: " + placeId);

        // placeId에 해당하는 PlaceDTO를 받아옴
        PlaceDTO placeDTO = placeService.getPlace(placeId);

        // dto라는 attribute에 placeDTO를 실어 html로 전송
        model.addAttribute("dto", placeDTO);
        model.addAttribute("page", requestDTO.getPage());
    }
}
