package com.nqueens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by amateen on 9/29/18.
 */
public class MainApplication {

    private static final int DEFAULT_N = 8;

    public static void main(String[] argc){
        int N = DEFAULT_N;
        if ( argc.length > 0 )
            N = Integer.parseInt(argc[0]);
        Board queens = new Board(N);
        boolean solved = queens.solve();
        if ( !solved )
            System.out.println("No Solutions Found....");



    }




}
