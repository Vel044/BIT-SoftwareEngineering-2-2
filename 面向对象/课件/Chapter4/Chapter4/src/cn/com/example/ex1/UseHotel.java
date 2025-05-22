package cn.com.example.ex1;

public class UseHotel {
    public static void main(String[] args){
        Hotel hotel1 = new Hotel("MiniHilton", new String[10][20]);
        Hotel hotel2 = new Hotel("MiniStarwood", new String[15][20]);

        System.out.println(hotel1.getHotelName());  //获取到修改后的hotelName

        int res = hotel1.compareTo(hotel2);
        if(res>0){
            System.out.println("酒店1的房间更多");
        }else if(res<0){
            System.out.println("酒店2的房间更多");
        }else{
            System.out.println("两个酒店的房间一样多");
        }

    }
}
