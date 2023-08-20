import java.util.List;

public class UserMain {
    public static void main(String[] args) {

        UserUi userUI = new UserUi();
        UserDao userDao = new UserDao("C:\\Users\\user\\Desktop\\Order_manageProject\\users.dat");
        List<User> users = userDao.getUsers();
        while (true) {
            int menuId = userUI.menu();
            if (menuId == 5) {
                System.out.println("종료합니다.");
                userDao.saveUser(users);
                break;
            } else if (menuId == 1) {
                User user = userUI.regUser();
                users.add(user);
                System.out.println("등록되었습니다");
            } else if(menuId==2){
                userUI.printUserList(users);
            }
        }
    }
}
