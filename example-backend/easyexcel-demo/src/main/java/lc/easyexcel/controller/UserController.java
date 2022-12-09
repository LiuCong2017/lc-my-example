package lc.easyexcel.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController {

    //input excel
    @PostMapping("/uploadUserExcel")
    public String uploadUserExcel(@RequestParam("file")MultipartFile file){

        return null;
    }

}
