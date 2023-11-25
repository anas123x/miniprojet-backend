package tn.esprit.com.foyer.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.com.foyer.mailModel.MailStructure;
import tn.esprit.com.foyer.services.IMailService;
import tn.esprit.com.foyer.services.MailService;

@RestController
@AllArgsConstructor
@RequestMapping("/mailer")
public class MailController {
    IMailService mailService;

    @PostMapping("/sendMail/{e-mail}")
    public String sendMail(@PathVariable("e-mail") String mail, @RequestBody MailStructure mailStructure){
        mailService.sendMail(mail,mailStructure);
        return "mail send with success !!";
    }

}
