public class ListTest {
    public static void main(String[] args) {
        MyList<String> myList = new MyList<>();

        // 添加元素到链表
        myList.add("Apple");
        myList.add("Banana");
        myList.add("Cherry");

        // 打印链表的长度
        System.out.println("List size: " + myList.size());

        // 打印链表中的元素
        for (int i = 0; i < myList.size(); i++) {
            System.out.println("Element at index " + i + ": " + myList.get(i));
        }

        // 测试访问超出索引的元素
        try {
            System.out.println("Element at index 5: " + myList.get(5));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBoundsException caught: " + e.getMessage());
        }
    }
}
