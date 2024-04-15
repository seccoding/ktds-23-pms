package com.ktdsuniversity.edu.pms.ckeditor.web;

import com.ktdsuniversity.edu.pms.ckeditor.service.CkeditorService;
import com.ktdsuniversity.edu.pms.ckeditor.vo.CkeditorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CkeditorTestController {

    @Autowired
    private CkeditorService ckeditorService;

    @GetMapping("/ckeditor")
    public String viewCkeditorPage() {
        return "ckeditor/ckeditortest";
    }

    @PostMapping("/ckeditor/save")
    public String saveCkeditorContent(CkeditorVO ckeditorVO) {

        ckeditorService.saveContent(ckeditorVO);

        return "redirect:/ckeditor/list";
    }

    @GetMapping("/ckeditor/list")
    public String viewCkeditorArticleListPage(Model model) {

        model.addAttribute("list", ckeditorService.allContent());

        return "ckeditor/list";
    }

    @GetMapping("/ckeditor/{id}")
    public String viewContentPage(@PathVariable String id, Model model) {

        model.addAttribute("content", ckeditorService.selectOneContent(id));

        return "ckeditor/view";
    }
}
