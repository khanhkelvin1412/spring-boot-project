package com.evotek.controller;

import com.evotek.controller.output.NewOuput;
import com.evotek.dto.NewDTO;
import com.evotek.service.ICategoryService;
import com.evotek.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class Controller {

    @Autowired
    private INewService newService;

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView createNew(NewDTO model){
        String categoryCode = model.getCategoryCode().replace(",", "");
        model.setCategoryCode(categoryCode);
        newService.save(model);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "each_new")
    public ModelAndView getDetailNew(@RequestParam("id") long id, Model model){
        NewDTO newDTO = newService.findNewById(id);
        model.addAttribute("post", newDTO);
        return new ModelAndView("detail");
    }

    @RequestMapping(value = "/add")
    public ModelAndView addNew(Model model){
        model.addAttribute("post", new NewDTO());
        model.addAttribute("categorys", categoryService.findAll());
        return new ModelAndView("addNew");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView updateNew(@RequestParam("id") long id, Model model){
        ModelAndView mav = new ModelAndView();
        NewDTO newDTO = newService.findNewById(id);
        if ((newDTO != null)) {
            model.addAttribute("post", newDTO);
        }
        mav.setViewName("editNew");
        return mav;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteNew(@RequestParam("id") long id){
        newService.delete(id);
        return new ModelAndView("redirect:/");
    }


    @RequestMapping("/")
    public ModelAndView index(@RequestParam(value = "page", required = false) Integer page,
                              @RequestParam(value = "limit", required = false) Integer limit,Model model) {
        List<NewDTO> posts = new ArrayList<>();
        posts = newService.findAll();
        model.addAttribute("posts", posts);
        NewOuput results = new NewOuput();
        if(page != null && limit != null){
            results.setPage(page);
            results.setTotalPage((int) Math.ceil(newService.totalItem() / limit));
            Pageable pageable = new PageRequest(page - 1,limit );
            results.setListResult(newService.findAll(pageable));
            model.addAttribute("results", results);
        } else {
            results.setListResult(newService.findAll());
        }
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

}
