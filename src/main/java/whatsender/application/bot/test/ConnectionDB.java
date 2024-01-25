package whatsender.application.bot.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import whatsender.application.entities.Cliente;

/**
 *
 * @author ALEXANDRE
 */
public class ConnectionDB {
    
    public static void main(String[] args) {
//        Cliente client1 = new Cliente(null,"Alexandre", "22998523363", "22998523363");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("whatsender-jpa");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
//        em.persist(client1);
        em.getTransaction().commit();
        
        System.out.println("Pronto!!!");
        
        em.close();
        emf.close();
        
        
    }
    
}
