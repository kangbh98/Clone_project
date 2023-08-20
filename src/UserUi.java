import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class UserUi {
    private BufferedReader br;
    public UserUi(){
        br= new BufferedReader(new InputStreamReader(System.in));
    }
    public int menu(){
        System.out.println("1. 회원등록");
        System.out.println("2. 회원목록보기");
        System.out.println("5. 종료");
        int menuId = -1;
        try{
            String line =br.readLine();
            menuId = Integer.parseInt((line));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return menuId;
    }
    public User regUser(){
        try {
            System.out.println("email을 입력하세요.");
            String email = br.readLine();
            System.out.println("이름을 입력하세요.");
            String name = br.readLine();
            System.out.println("생년을 입력하세요.");
            String strBirthday = br.readLine();
            int birthYear = Integer.parseInt(strBirthday);

            User user = new User(email, name, birthYear);
            return user;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }

    }

    public void printUserList(List<User> users){
        System.out.println("email,이름,생년을 출력합니다");
        System.out.println("======================");
        for(int i=0;i<users.size();i++){
            User user = users.get(i);
            System.out.print(user.getEmail());
            System.out.print("     ");
            System.out.print(user.getName());
            System.out.print("     ");
            System.out.println(user.getBirthYear());
            System.out.println("     ");
        }
    }
}
