package com.mark;

import java.io.*;

public class Main {

  public static void main(String[] args) {
    if(args.length == 0) {
        System.out.println("Please enter filename to read");
        return;
    }
    try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
      String line;
      // first line, read the count
      line = br.readLine();
      int count = Integer.parseInt(line);
      Balance[] balances = new Balance[count];

      //  read the balances and the wights
      int i = 0;
      Balance currentBalance = new Balance(i);
      while ((line = br.readLine()) != null) {
        // left arm
        if( i%2 == 0){
          currentBalance = new Balance(i/2);
          balances[i/2] = currentBalance;
          String[] tokens = line.split(" ");
          currentBalance.leftWeight = Integer.parseInt(tokens[0]);
          currentBalance.leftBalances = new int[tokens.length - 1];
          for(int j = 1; j < tokens.length; j++){
            currentBalance.leftBalances[j-1] = Integer.parseInt(tokens[j]);
          }
        }
        // right arm
        else {
          String[] tokens = line.split(" ");
          currentBalance.rightWeight = Integer.parseInt(tokens[0]);
          currentBalance.rightBalances = new int[tokens.length - 1];
          for(int j = 1; j < tokens.length; j++){
            currentBalance.rightBalances[j-1] = Integer.parseInt(tokens[j]);
          }
        }
        i++;
      }
      // process - balance the Balances
      for(int k = 0; k < count; k++) {
        if(!balances[k].isBalanced) {
          balances[k].balanceMyWeights(balances);
        }
        System.out.println(balances[k].id + ": " + balances[k].balanceLeft + " " + balances[k].balanceRight);
      }

    } catch (FileNotFoundException e) {
      System.out.println("File not found");
      e.printStackTrace();
      return;
    } catch (IOException e) {
      System.out.println("IO exception");
      e.printStackTrace();
      return;
    }
  }
}

