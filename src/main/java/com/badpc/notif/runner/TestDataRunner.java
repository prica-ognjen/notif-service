package com.badpc.notif.runner;

import com.badpc.notif.domain.NotificationType;
import com.badpc.notif.service.EmailService;
import com.badpc.notif.service.NotificationTypeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestDataRunner implements CommandLineRunner {

    private final EmailService emailService;
    private final NotificationTypeService notificationTypeService;

    public TestDataRunner(EmailService emailService, NotificationTypeService notificationTypeService) {
        this.emailService = emailService;
        this.notificationTypeService = notificationTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        NotificationType t1 = new NotificationType();
        //ime prezime link
        t1.setName("NOTIFICATION_REGISTER");
        t1.setText("Pozdrav [[ime]] [[prezime]]! Molimo vas da potvrdite identited klikom na sledeci link [[link]]");
        notificationTypeService.save(t1);

        NotificationType t2 = new NotificationType();
        //ime prezime link
        t2.setName("NOTIFICATION_RESET_PASSWORD");
        t2.setText("Pozdrav! Poslat je zahtev za promenu sifre vaseg naloga. Ukoliko ste vi to poslali, molimo vas da posetite sledeci link kako biste resetovali sifru: [[link]]");
        notificationTypeService.save(t2);

        NotificationType t3 = new NotificationType();
        //ime prezime hotel idRezervacije tipSobe date
        t3.setName("NOTIFICATION_RESERVE_CLIENT");
        t3.setText("Pozdrav [[ime]] [[prezime]]! Uspesno ste rezervisali sobu. Saljemo vam bitne podatke:\n" +
                "Hotel [[imeHotela]]\n" +
                "Broj Rezervacije [[idRezervacije]]\n" +
                "Tip Sobe [[tipSobe]]\n" +
                "Datum [[date]] check in");
        notificationTypeService.save(t3);

        NotificationType t4 = new NotificationType();
        //ime prezime idRezervacije tipSobe date
        t4.setName("NOTIFICATION_RESERVE_MANAGER");
        t4.setText("Pozdrav sefe! Klijent [[ime]] [[prezime]] je naseo i rezervisao sobu:\n" +
                "Broj Rezervacije [[idRezervacije]]\n" +
                "Tip Sobe [[tipSobe]]\n" +
                "Datum [[date]] check in");
        notificationTypeService.save(t4);

        NotificationType t5 = new NotificationType();
        //ime prezime idRezervacije tipSobe date
        t5.setName("NOTIFICATION_CANCEL_RESERVATION");
        t5.setText("Postovani rezervacija broj [[idRezervacije]] je otkazana\n");
        notificationTypeService.save(t5);
    }
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

Manager: Pozdrav sefe %ime %prezime! Klijent je naseo i rezervisao sobu:
Broj Rezervacije %idRezervacije
Tip Sobe %tipSobe
Datum %date check in

2 dana pre check in-a:
Postovani za dva dana vam je vreme da dodjete u hotel



 */

