package com.kedu.user;

import com.kedu.user.VO.SearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TodayController {

    @Autowired
    private TodayService service;

   @GetMapping(value = "/")
    public String main(Model model){
       model.addAttribute("locationList",service.selLocationCode());
       return "user/main";
   }

   @GetMapping("/result")
   public String result(){
       return "";
   }

   @PostMapping("/result")
    public String result(SearchDTO param){
        service.saveData(param);
       return "redirect:/result?";
   }
}
