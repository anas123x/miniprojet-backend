package tn.esprit.com.foyer.services;

import tn.esprit.com.foyer.mailModel.MailStructure;

public interface IMailService {
    public void sendMail(String mail , MailStructure mailStructure);
}
