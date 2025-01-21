package u4w3.events;

import jakarta.persistence.*;

import java.util.logging.Logger;
import java.util.logging.Level;

public class EventService {

    private EntityManager em;

    public EventService() {
        LoggingConfiguration();
        this.em = Persistence.createEntityManagerFactory("gestioneEventiPU").createEntityManager();
    }

    public void LoggingConfiguration() {
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
    }

    public void save(EventDAO event) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(event);
        transaction.commit();
        System.out.println("Evento salvato correttamente: " + event.getTitolo());
    }

    public EventDAO findById(int id) {
        return em.find(EventDAO.class, id);
    }

    public void deleteById(int id) {
        EventDAO event = em.find(EventDAO.class, id);
        if (event != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(event);
            transaction.commit();
            System.out.println("Evento eliminato correttamente: " + id);
        } else {
            System.out.println("Evento non trovato: " + id);
        }
    }

    public void findByIdAndDelete(int id) {
        EventDAO found = em.find(EventDAO.class, id);
        if (found != null) {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.remove(found);
            t.commit();
            System.out.println("Evento eliminato correttamente");
        } else {
            System.out.println("Evento non trovato");
        }
    }

}
