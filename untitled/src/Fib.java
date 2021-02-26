public class Fib   {
    public int fib(int n){
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib(n-1) + fib(n-2);
    }


    public int fibdynamic(int n) {
        int[] p = new int[n + 1];
        for (int i = 0; i < n; i++) {
             if (i == 0){
                 p[i] = 0;
             }  
             else if (i == 1) {
                p[i] = 1;
            } else {
                p[i] = p[i - 1] + p[i - 2];
            }

        }
        return p[n];
    }
}
