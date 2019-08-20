package linkedlist;

public class TestMyLinkedList {
    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("Vasya");
        list.add("Petya");
        list.add("Sofa");

        for (String str : list) {
            System.out.println(str);
        }

        System.out.println(list.get(1));
    }
}
