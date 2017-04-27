package com.mark;

public class Balance {
  int id;
  int[] leftBalances;
  int leftBalancesTotalWeight;
  int[] rightBalances;
  int rightBalancesTotalWeight;
  int leftWeight;
  int rightWeight;
  int balanceLeft;
  int balanceRight;
  boolean isBalanced = false;
  int totalWeight = 10;

  public Balance(int id) {
    this.id = id;
  }

  public void balanceMyWeights(Balance[] balances) {
    // balance left weights
    for(int i = 0; i < leftBalances.length; i++) {
      if(!balances[leftBalances[i]].isBalanced) {
        balances[leftBalances[i]].balanceMyWeights(balances);
      }
    }
    // balance right weights
    for(int i = 0; i < rightBalances.length; i++) {
      if(!balances[rightBalances[i]].isBalanced) {
        balances[rightBalances[i]].balanceMyWeights(balances);
      }
    }
    // balance self
    updateTotalWeights(balances);
    if(leftWeight + leftBalancesTotalWeight > rightWeight + rightBalancesTotalWeight) {
      balanceRight = (leftWeight + leftBalancesTotalWeight) - (rightWeight + rightBalancesTotalWeight);
      totalWeight += balanceRight;
    } else {
      balanceLeft = (rightWeight + rightBalancesTotalWeight) - (leftWeight + leftBalancesTotalWeight);
      totalWeight += balanceLeft;
    }

    isBalanced = true;
  }

  private void updateTotalWeights(Balance[] balances) {
    // add left weights
    for(int i = 0; i < leftBalances.length; i++) {
      leftBalancesTotalWeight += balances[leftBalances[i]].totalWeight;
    }
    // add right weights
    for(int i = 0; i < rightBalances.length; i++) {
      rightBalancesTotalWeight += balances[rightBalances[i]].totalWeight;
    }

    totalWeight += rightWeight + leftWeight + rightBalancesTotalWeight + leftBalancesTotalWeight;
  }

}
