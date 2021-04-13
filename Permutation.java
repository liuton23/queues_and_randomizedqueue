/* *****************************************************************************
 *  Name: Tong Liu
 *  Date: 4/13/2021
 *  Description: client code for randomized queue
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        String str;
        while (!StdIn.isEmpty()){
            str = StdIn.readString();
            rq.enqueue(str);
        }
        for (int i = 0; i < k; i++){
            System.out.println(rq.dequeue());
        }
    }
}
