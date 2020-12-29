public class Encryption {

    private String text;

    private double L;

    private String encrypted;
    public Encryption(String input){
        this.text = input.replaceAll("\\s", "");
        this.L = this.text.length() - 1;

    }
    public String encode(){

        int floor = (int)Math.floor(Math.sqrt(L));
        int ceil = (int)Math.ceil(Math.sqrt(L));
        char[][] grid = new char [floor][ceil];
        int l = 0;
        for (int i = 0; i < floor; i++) {
            for (int j = 0; j < ceil && l < L; j++) {
                grid[i][j] = this.text.charAt(l);
                l++;
            }
        }
        String output = "";
        for (int j = 0; j < ceil; j++) {
            for (int i = 0; i < floor; i++) {
                output += grid[i][j];
            }
            output += " ";
        }
        return output;
    }
}
