package com.badpc.notif.listener;

import com.badpc.notif.domain.Archive;
import com.badpc.notif.domain.NotificationType;
import com.badpc.notif.dto.*;
import com.badpc.notif.listener.helper.MessageHelper;
import com.badpc.notif.repository.ArchiveRepository;
import com.badpc.notif.repository.NotificationTypeRepository;
import com.badpc.notif.service.EmailService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.Date;

@Component
public class EmailListener {

    private final MessageHelper messageHelper;
    private final EmailService emailService;
    private final NotificationTypeRepository notificationTypeRepository;
    private final ArchiveRepository archiveRepository;

    public EmailListener(MessageHelper messageHelper, EmailService emailService, NotificationTypeRepository notificationTypeRepository, ArchiveRepository archiveRepository){
        this.messageHelper = messageHelper;
        this.emailService = emailService;
        this.notificationTypeRepository = notificationTypeRepository;
        this.archiveRepository = archiveRepository;
    }


    @JmsListener(destination = "${destination.sendMailOnReservation}", concurrency = "5-10")
    public void sendMailOnReservation(Message message) throws JMSException{
        System.out.println("Rezervacija ulaz client");
        CReservationNotificationDto s = messageHelper.getMessage(message, CReservationNotificationDto.class);

        NotificationType n = notificationTypeRepository.getByName(s.getType().name());

        String text = n.getText();
        text = text.replace("[[ime]]", s.getFirstName());
        text = text.replace("[[prezime]]", s.getLastName());
        text = text.replace("[[imeHotela]]", s.getHotelName());
        text = text.replace("[[idRezervacije]]", s.getIdReservation().toString());
        text = text.replace("[[tipSobe]]", s.getTipSobe());
        text = text.replace("[[date]]", s.getCheckIn().toString());

        //Cuvanje u arhivi
        Archive archive = new Archive();
        archive.setEmail(s.getEmail());
        archive.setTipNotifikacije(s.getType().name());
        Date date = new Date();
        archive.setDate(date);
        archiveRepository.save(archive);
        emailService.sendSimpleMessage(s.getEmail(), "Hotelino Reservation Confirmation", text);
    }

    @JmsListener(destination = "${destination.mSendResConfirm}", concurrency = "5-10")
    public void sendResConfirmManager(Message message) throws JMSException{
        System.out.println("Rezervacija ulaz manager");
        MReservationNotificationDto s = messageHelper.getMessage(message, MReservationNotificationDto.class);

        NotificationType n = notificationTypeRepository.getByName(s.getType().name());
        String text = n.getText();
        text = text.replace("[[ime]]", s.getFirstName());
        text = text.replace("[[prezime]]", s.getLastName());
        text = text.replace("[[idRezervacije]]", s.getIdReservation().toString());
        text = text.replace("[[tipSobe]]", s.getTipSobe());
        text = text.replace("[[date]]", s.getCheckIn().toString());

        //Cuvanje u arhivi
        Archive archive = new Archive();
        archive.setEmail(s.getEmail());
        archive.setTipNotifikacije(s.getType().name());
        Date date = new Date();
        archive.setDate(date);
        archiveRepository.save(archive);



        emailService.sendSimpleMessage(s.getEmail(), "Hotelino Reservation Confirmation", text);
    }

    @JmsListener(destination = "${destination.createUserEmail}", concurrency = "5-10")
    public void sendMailOnRegistration(Message message) throws JMSException {
        System.out.println("Ulazi register");

        RegisterNotificationDto s = messageHelper.getMessage(message, RegisterNotificationDto.class);

        NotificationType t = notificationTypeRepository.getByName(s.getNotificationType().name());

        String text = t.getText();
        text = text.replace("[[ime]]", s.getFirstName());
        text = text.replace("[[prezime]]", s.getLastName());
        text = text.replace("[[link]]", "http://localhost:8080/api/users/activate/"+s.getId());

        //Cuvanje u arhivi
        Archive archive = new Archive();
        archive.setEmail(s.getEmail());
        archive.setTipNotifikacije(s.getNotificationType().name());
        Date date = new Date();
        archive.setDate(date);
        archiveRepository.save(archive);
        System.out.println("EVO: " + s.getEmail());
        emailService.sendSimpleMessage(s.getEmail(), "Hotelino Account Verification", text);
    }

    @JmsListener(destination = "${destination.cancelReservation}", concurrency = "5-10")
    public void cancelReservation(Message message) throws JMSException {
        System.out.println("Ulazi otkazivanje rezervacije");

        CancelReservationNotificationDto s = messageHelper.getMessage(message, CancelReservationNotificationDto.class);

        NotificationType t = notificationTypeRepository.getByName(s.getNotificationType().name());

        String text = t.getText();
        text = text.replace("[[idRezervacije]]", s.getIdReservation().toString());


        //Cuvanje u arhivi
        Archive archive = new Archive();
        archive.setEmail(s.getEmail());
        archive.setTipNotifikacije(s.getNotificationType().name());
        Date date = new Date();
        archive.setDate(date);
        archiveRepository.save(archive);
        emailService.sendSimpleMessage(s.getEmail(), "Hotelino Reservation Cancelation", text);
    }

    /*

Register
--------
Pozdrav %ime %prezime! Molimo vas da potvrdite identited klikom na sledeci link %link

Reset password
--------------
Pozdrav %ime %prezime! Poslat je zahtev za promenu sifre vaseg naloga. Ukoliko ste vi to poslali, molimo vas da posetite sledeci link kako biste resetovali sifru: %link

Reserved
--------
Client: Pozdrav %ime %prezime! Uspesno ste rezervisali sobu. Saljemo vam bitne podatke:
Hotel %imeHotela
Broj Rezervacije %idRezervacije
Tip Sobe %tipSobe
Datum %date check in

Manager: Pozdrav sefe Klijent %ime %prezime je naseo i rezervisao sobu:
Broj Rezervacije %idRezervacije
Tip Sobe %tipSobe
Datum %date check in

2 dana pre check in-a:
Postovani za dva dana vam je vreme da dodjete u hotel



 */

}
