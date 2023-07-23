package es.agoney;

import database.UserDAO;
import domain.User;
import java.util.ArrayList;
import java.util.List;

public class User_crud_jdbc {

    public static void main(String[] args) {
        System.out.println("Hola");
        List<User> listUser = new ArrayList<>();
        User user1 = new User("John", "1234");
        User user2 = new User("Mikel", "5678");        
        User user3 = new User("PaBorrar", "Eliminar");
        UserDAO userDAO = new UserDAO();
        int u1 = userDAO.create(user1);
        int u2 = userDAO.create(user2);
        int u3 = userDAO.create(user3);
        listUser = userDAO.getAll();
        for (User u: listUser){
            System.out.println(u);
        }
        System.out.println("------------ BY ID --------------------");
        System.out.println(userDAO.getById(3));
        System.out.println("//////////////// BY NAME ///////////////////");
        System.out.println(userDAO.getByName("John"));
        System.out.println("++++++ UPDATE +++++++++++++");
        user2.setIdUser(2);
        user2.setPassword("otra_password");
        user2 = userDAO.update(user2);
        System.out.println("USER2: " + user2);
        System.out.println("*+*+*+*+*+*+* DELETE *+*+*+*+*+*+*+*+**");
        user3.setIdUser(3);
        int rows = userDAO.delete(user3);
        if (rows==1) System.out.println("Eliminado el usuario3");
        listUser = userDAO.getAll();
        for (User u: listUser){
            System.out.println(u);
        }        
    }
}
