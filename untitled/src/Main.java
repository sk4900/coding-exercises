public class Main {
    public static void main(String[] args){
        String test = "if man was meant to stay on the ground god would have given us roots";
        Encryption testing = new Encryption(test);
        System.out.println(testing.encode());
    }
}
