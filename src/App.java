import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import util.HibernateUtil;
import model.Actor;
import model.Film;

public class App {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.println("\n=== JPQL DEMO ===\n");

        Film newFilm = new Film();
        newFilm.setTitle("MY NEW FILM");
        newFilm.setRating("PG");
        newFilm.setReleaseYear((short)2025);
        newFilm.setLanguageId((short)1);
        session.persist(newFilm);
        System.out.println("Added new film: " + newFilm);

        Actor newActor = new Actor();
        newActor.setFirstName("Dean");
        newActor.setLastName("Winchester");
        session.persist(newActor);
        System.out.println("Added new actor: " + newActor);

        List<Film> allFilms = session.createQuery("FROM Film", Film.class).setMaxResults(5).list();
        System.out.println("\nFirst 5 films:");
        allFilms.forEach(System.out::println);

        List<Film> pgFilms = session.createQuery(
                "FROM Film f WHERE f.rating = 'PG' ORDER BY f.title", Film.class).setMaxResults(5)
                .list();
        System.out.println("\nFilms rated PG:");
        pgFilms.forEach(System.out::println);

        String rating = "PG-13";
        List<Film> filteredFilms = session.createQuery(
                "FROM Film f WHERE f.rating = :rating ORDER BY f.releaseYear DESC", Film.class)
                .setParameter("rating", rating)
                .setMaxResults(5)
                .list();
        System.out.println("\nFilms rated " + rating + ":");
        filteredFilms.forEach(System.out::println);

        Long count = session.createQuery("SELECT COUNT(f) FROM Film f WHERE f.rating = 'R'", Long.class)
                .getSingleResult();
        System.out.println("\nNumber of films rated R: " + count);

        List<Actor> allActors = session.createQuery("FROM Actor", Actor.class).setMaxResults(5).list();
        System.out.println("\nFirst 5 actors:");
        allActors.forEach(System.out::println);

        tx.commit();
        session.close();
        HibernateUtil.shutdown();
    }
}