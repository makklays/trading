/**
 * There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next (i + 1)th station. You begin the journey with an empty tank at one of the gas stations.
 * Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1. If there exists a solution, it is guaranteed to be unique.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */

class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int currentTank = 0;  // Текущий баланс бензина в баке 
        int startIndex = 0;   // Потенциальный стартовый индекс i 
        
        int sumGas = 0;
        int sumCost = 0;

        // Если бы мы начали путь с любой станции до startIndex, наш бак ушел бы в минус. 
        // Это значит, что отрезок пути от 0 до startIndex — это самый «затратный» и тяжелый участок всей трассы, который «съедает» бензин.
        // Но так как общая сумма бензина в плюсе, а на участке от startIndex до конца массива мы накопили топливо, 
        // этого накопленного в конце массива бензина гарантированно хватит, чтобы покрыть весь дефицит начального участка (от 0 до startIndex). 

        for(int i = 0; i < gas.length; i++) {
            sumGas += gas[i];
            sumCost += cost[i];

            currentTank += gas[i] - cost[i];

            if (currentTank < 0) {
                currentTank = 0;
                startIndex = i + 1;
            }
        }

        // Дано бензина больше чем требуется 
        if (sumGas < sumCost) return -1;

        return startIndex;
    }
}

