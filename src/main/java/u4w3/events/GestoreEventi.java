package u4w3.events;

import java.time.LocalDate;
import java.util.Random;

import org.w3c.dom.events.Event;

import jakarta.persistence.EntityTransaction;

public class GestoreEventi {

    public static void main(String[] args) {

        EventService eventService = new EventService();

        // for (int i = 0; i < 300; i++) {
        // int randomTitolo = new Random().nextInt(1000) + 1000;
        // int randomPartecipanti = new Random().nextInt(10);

        // String tipoEvento;
        // if (new Random().nextBoolean()) {
        // tipoEvento = "PUBBLICO";
        // } else {
        // tipoEvento = "PRIVATO";
        // }

        // EventDAO event = new EventDAO(
        // "Evento " + randomTitolo,
        // LocalDate.now(),
        // "Descrizione evento " + randomTitolo,
        // tipoEvento,
        // randomPartecipanti);

        // try {
        // eventService.save(event);
        // } catch (Exception e) {
        // System.out.println("Errore durante il salvataggio: " + e.getMessage());
        // }
        // }

    }
}