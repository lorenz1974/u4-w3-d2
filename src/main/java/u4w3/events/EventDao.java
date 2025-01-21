package u4w3.events;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "events")
public class EventDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestioneEventiPU");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "titolo")
    private String titolo;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "tipo_evento")
    private String tipoEvento;

    @Column(name = "numero_massimo_partecipanti")
    private int numeroMassimoPartecipanti;

    private EntityManager em;

    public EventDAO(String titolo, LocalDate data, String descrizione,
            String tipoEvento,
            int numeroMassimoPartecipanti) {
        setTitolo(titolo);
        setData(data);
        setDescrizione(descrizione);
        setTipoEvento(tipoEvento);
        setNumeroMassimoPartecipanti(numeroMassimoPartecipanti);

        setEm();

    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm() {

        this.em = emf.createEntityManager();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public int getNumeroMassimoPartecipanti() {
        return numeroMassimoPartecipanti;
    }

    public void setNumeroMassimoPartecipanti(int numeroMassimoPartecipanti) {
        this.numeroMassimoPartecipanti = numeroMassimoPartecipanti;
    }

    public void save(EventDAO e) {
        EntityTransaction t = em.getTransaction();
        t.begin(); // inizia la transazione

        em.persist(e);

        t.commit(); // termina la transazione, qua l'oggetto viene salvato
        System.out.println("Evente salvato correttamente");
    }

    public EventDAO findById(int id) {
        return em.find(EventDAO.class, id);
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

    public void refresh(EventDAO e) {
        EventDAO found = em.find(EventDAO.class, e.getId());
        if (found != null) {
            if (chackField(e, found, "titolo")) {
                found.setTitolo(e.getTitolo());
            }
            if (chackField(e, found, "data")) {
                found.setData(e.getData());
            }
            if (chackField(e, found, "descrizione")) {
                found.setDescrizione(e.getDescrizione());
            }
            if (chackField(e, found, "tipoEvento")) {
                found.setTipoEvento(e.getTipoEvento());
            }
            if (chackField(e, found, "numeroMassimoPartecipanti")) {
                found.setNumeroMassimoPartecipanti(e.getNumeroMassimoPartecipanti());
            }

            em.refresh(found);
            System.out.println("Evento aggiornato correttamente");
        } else {
            System.out.println("Evento non trovato");
        }
    }

    private Boolean chackField(EventDAO e1, EventDAO e2, String field) {
        switch (field) {
            case "titolo":
                return e1.getTitolo() != null && !e1.getTitolo().isEmpty() && e2.getTitolo() != e1.getTitolo();
            case "data":
                return e1.getData() != null && e2.getData() != e1.getData();
            case "descrizione":
                return e1.getDescrizione() != null && !e1.getDescrizione().isEmpty()
                        && e2.getDescrizione() != e1.getDescrizione();
            case "tipoEvento":
                return e1.getTipoEvento() != null && e2.getTipoEvento() != e1.getTipoEvento();
            case "numeroMassimoPartecipanti":
                return e1.getNumeroMassimoPartecipanti() != e2.getNumeroMassimoPartecipanti();
            default:
                return false;
        }
    }
}
