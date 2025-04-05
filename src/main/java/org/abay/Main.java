package org.abay;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.abay.entities.Product;
import org.abay.persistence.CustomPersistenceUnitInfoProvider;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Map<String,String> properties = new HashMap<>();
        properties.put("hibernate.show_sql","true");
        properties.put("hibernate.hbm2ddl.auto","create");

        EntityManagerFactory factory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfoProvider(),properties);
        EntityManager em = factory.createEntityManager();

        try{
            em.getTransaction().begin();
            //populating the database
            populateDatabase(em);


            //JPQL query to fetch all the products and display them to console
           // TypedQuery<Product> jpql = em.createQuery("SELECT p FROM Product p where p.inStock =false",Product.class);
           //selecting list of products in two different categories

          //  TypedQuery<Product> jpql = em.createQuery("SELECT p from Product p where p.category IN ('Kitchen','Electronics')",Product.class);

            //here am selecting product brands start with S
          //  TypedQuery<Product> jpql = em.createQuery("select p from Product  p where p.brand like 'S%'",Product.class);

            //THE NEXT QUERY IS SELECTING PRODUCTS BASED ON Quantity RANGE ON A CERTAIN SPECIFIC RANGE
//            TypedQuery<Product> jpql = em.createQuery("select p from Product p where p.quantity BETWEEN 5 and 15", Product.class);
//            jpql.getResultList().forEach(System.out::println);


            //AM PERFORMING AGREGATE FUNCTIONS AND ABLE TO FIND TOTAL PRODUCTS BASED ON THE FF QUERY
            TypedQuery<Long>  totalProductsQuery = em.createQuery("select count(p) from  Product p", Long.class);
            System.out.println(totalProductsQuery.getSingleResult());

            //Finding the largest avialable product qunaity
            TypedQuery<Integer> largestQuantity = em.createQuery("select max (p.quantity) from Product p ",Integer.class);
            System.out.println(largestQuantity.getSingleResult());



            em.getTransaction().commit();

        }finally {
            em.close();
        }


    }
   public static void populateDatabase(EntityManager em){
       em.persist(new Product("Laptop", "Dell", "850.00", 10, true, LocalDateTime.now(), "Electronics"));
       em.persist(new Product("Phone", "Samsung", "500.00", 0, false, LocalDateTime.now(), "Electronics"));
       em.persist(new Product("Desk Chair", "Ikea", "120.00", 15, true, LocalDateTime.now(), "Furniture"));
       em.persist(new Product("Monitor", "LG", "200.00", 5, true, LocalDateTime.now(), "Electronics"));
       em.persist(new Product("Blender", "Philips", "75.00", 8, true, LocalDateTime.now(), "Kitchen"));
       em.persist(new Product("Air Fryer", "Ninja", "150.00", 6, true, LocalDateTime.now(), "Kitchen"));
       em.persist(new Product("Wireless Mouse", "Logitech", "30.00", 20, true, LocalDateTime.now(), "Electronics"));
       em.persist(new Product("Backpack", "Samsonite", "90.00", 12, true, LocalDateTime.now(), "Travel"));
       em.persist(new Product("Sneakers", "Nike", "110.00", 7, true, LocalDateTime.now(), "Fashion"));
       em.persist(new Product("Smart Watch", "Apple", "399.00", 4, true, LocalDateTime.now(), "Electronics"));
       em.persist(new Product("Coffee Maker", "Keurig", "99.00", 9, true, LocalDateTime.now(), "Kitchen"));
       em.persist(new Product("Bookshelf", "Sauder", "145.00", 3, true, LocalDateTime.now(), "Furniture"));
       em.persist(new Product("Vacuum Cleaner", "Dyson", "299.00", 2, true, LocalDateTime.now(), "Home"));
       em.persist(new Product("Gaming Console", "Sony", "499.00", 0, false, LocalDateTime.now(), "Electronics"));
       em.persist(new Product("T-Shirt", "Hanes", "12.00", 50, true, LocalDateTime.now(), "Fashion"));
       em.persist(new Product("Water Bottle", "Hydro Flask", "45.00", 25, true, LocalDateTime.now(), "Sports"));
       em.persist(new Product("Toaster", "Black+Decker", "40.00", 10, true, LocalDateTime.now(), "Kitchen"));
       em.persist(new Product("Tablet", "Lenovo", "250.00", 6, true, LocalDateTime.now(), "Electronics"));
       em.persist(new Product("Office Desk", "FlexiSpot", "320.00", 2, true, LocalDateTime.now(), "Furniture"));
       em.persist(new Product("Bluetooth Speaker", "JBL", "99.00", 13, true, LocalDateTime.now(), "Electronics"));

   }
}