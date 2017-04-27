package com.mark;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

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
      HashMap<Integer, Balance> balances = new HashMap<>(count);

      //  read the balances and the wights
      int i = 0;
      Balance currentBalance = new Balance(i);
      while ((line = br.readLine()) != null) {
        // left arm
        if( i%2 == 0){
          currentBalance = new Balance(i/2);
          balances.put(currentBalance.id, currentBalance);
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
          currentBalance.righBalances = new int[tokens.length - 1];
          for(int j = 1; j < tokens.length; j++){
            currentBalance.righBalances[j-1] = Integer.parseInt(tokens[j]);
          }
        }
        i++;
      }
      // process
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

