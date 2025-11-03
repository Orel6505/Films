import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import util.HibernateUtil;
import model.Film;

public class App {
        public static void main(String[] args) {
                Session session = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();

                System.out.println("\n=== JPQL DEMO ===\n");

                // Simple SELECT query
                List<Film> allFilms = session.createQuery("FROM Film", Film.class).setMaxResults(5).list();
                System.out.println("First 5 films:");
                allFilms.forEach(System.out::println);

                // Filter with WHERE clause
                List<Film> pgFilms = session.createQuery(
                                "FROM Film f WHERE f.rating = 'PG' ORDER BY f.title", Film.class).setMaxResults(5)
                                .list();
                System.out.println("\nFilms rated PG:");
                pgFilms.forEach(System.out::println);

                // Using parameterized query
                String rating = "PG-13";
                List<Film> filteredFilms = session.createQuery(
                                "FROM Film f WHERE f.rating = :rating ORDER BY f.releaseYear DESC", Film.class)
                                .setParameter("rating", rating)
                                .setMaxResults(5)
                                .list();
                System.out.println("\nFilms rated " + rating + ":");
                filteredFilms.forEach(System.out::println);

                // Aggregation example (COUNT)
                Long count = session.createQuery("SELECT COUNT(f) FROM Film f WHERE f.rating = 'R'", Long.class)
                                .getSingleResult();
                System.out.println("\nNumber of films rated R: " + count);

                tx.commit();
                session.close();
                HibernateUtil.shutdown();
        }
}
