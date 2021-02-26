import java.util.*;

public class BestExp {

    List<Integer> numbers;

    List<String> ops;

    double best;

    int iteration;

    int target;

    List<Integer> bestNums = new ArrayList<>();

    List<String> bestOps = new ArrayList<>();

    public BestExp(List<Integer> numbers, int target){
        this.target = target;
        this.numbers = numbers;
        this.iteration = 1;
        this.ops = new ArrayList<>();
        for (int i = 0; i < 99; i++){
            ops.add(randomOp());
        }
        divideByZero();
        this.best = 9999999;
    }

    private void divideByZero(){
        for (int i = 0; i < 99; i++) {
            if (this.ops.get(i).equals("/") & this.numbers.get(i+1) == 0){
                Random rand = new Random();
                int random_num = rand.nextInt(3);
                String newOp = switch (random_num) {
                    case 0 -> "+";
                    case 1 -> "-";
                    case 2 -> "*";
                    default -> "default";
                };
                change(i, newOp);
            }
        }
    }
    public double getBest(){return this.best;}
    public String randomOp(){
        Random rand = new Random();
        int random_num = rand.nextInt(4);
        return switch (random_num) {
            case 0 -> "+";
            case 1 -> "-";
            case 2 -> "*";
            case 3 -> "/";
            default -> "default";
        };
    }

    public void swap(int n1, int n2){
        int temp = this.numbers.get(n1);
        this.numbers.set(n1,this.numbers.get(n2));
        this.numbers.set(n2, temp);
    }
    public void change(int n1, String op){
        this.ops.set(n1, op);
    }

    public String toString(){
        String expression = this.numbers.get(0).toString();
        for (int i = 0; i <99; i++){
            expression += this.ops.get(i);
            expression += this.numbers.get(i+1);
        }
        return expression;
    }

    public String bestString(){
        if (this.bestNums.isEmpty()) return "";
        String expression = this.bestNums.get(0).toString();
        for (int i = 0; i <99; i++){
            expression += this.bestOps.get(i);
            expression += this.bestNums.get(i+1);
        }
        return expression;
    }

    public void runIteration(){
        Collections.shuffle(this.numbers);
        Collections.shuffle(this.ops);
        divideByZero();
        System.out.println("***************************");
        System.out.println("RR Iteration: " + this.iteration + "        Overall Best: " + this.best);
        System.out.println("Overall Best State: " + bestString());
        double current = Math.abs(this.target - evaluateExp());
        System.out.println("S0: " + toString());
        System.out.println("Distance from target: " + current);
        double localBest = best;
        while(current < localBest){
            Random rand = new Random();
            int random = rand.nextInt(2);
            if (random == 0){
                int rands = rand.nextInt(100);
                int rands2 = rand.nextInt(100);
                this.swap(rands, rands2);
            }
            if (random == 1){
                int randc = rand.nextInt(99);
                this.change(randc, randomOp());
            }
            localBest = current;
            current = Math.abs(this.target - evaluateExp());
            System.out.println("\nBest State: " + toString());
            System.out.println("\nDistance from target: " + current);

        }
        if (localBest < this.best){
            this.best = localBest;
            this.bestNums = numbers;
            this.bestOps = ops;
        }

        iteration++;
    }
    public double evaluateExp(){
        double result = this.numbers.get(0);
        for (int i = 0; i < 99; i++) {
            result = switch (this.ops.get(i)){
                case "+" -> result + this.numbers.get(i+1);
                case "-" -> result - this.numbers.get(i+1);
                case "*" -> result * this.numbers.get(i+1);
                case "/" -> (this.numbers.get(i+1) == 0) ? result + 0 : result / this.numbers.get(i+1);
                default -> result;
            };
        }
        return result;
    }
    public static void main(String[] args) throws InterruptedException {

        Random rand = new Random();
        List<Integer> startingNums = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            startingNums.add(rand.nextInt(10));
        }
        int target = rand.nextInt(9000) + 1000;
        System.out.println("Number set: " + startingNums.toString());
        System.out.println("Target: " + target);
        BestExp bestExp = new BestExp(startingNums, target);
        while(bestExp.getBest() != target) {
            bestExp.runIteration();
            Thread.sleep(100);
        }

    }
}
