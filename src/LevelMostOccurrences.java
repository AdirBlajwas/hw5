import java.util.ArrayDeque;
/** A class which finds the level in a binary node tree with most occurrences of a specified number*/
public class LevelMostOccurrences {
    /**
     * finds the level in a binary node tree with most occurrences of a specified number
     * @param node - BinNode - the root of the tree
     * @param num - the specified number
     * @return the level with the most occurrences of num
     */
    public static int getLevelWithMostOccurrences(BinNode<Integer> node, int num) {
        ArrayDeque<BinNode<Integer>> binNodeQueue = new ArrayDeque<>(); // queue
        ArrayDeque<Integer> levelQueue = new ArrayDeque<>(); // The level of the nodes in q by it's order
        levelQueue.add(0);
        binNodeQueue.add(node);
        int currentLevel = 0;
        int max_level = -1; //The current level with the most occurrences of num
        int max_sum; // number of occurrences of num in max_level
        int curLevelSum = max_sum = 0;

        while (!binNodeQueue.isEmpty()){
            int elementLevel = levelQueue.peek();
            if(elementLevel != currentLevel){ // if we reached another level checks if the sum > max sum then reset.
                if(max_sum < curLevelSum){
                    max_sum = curLevelSum;
                    max_level = currentLevel;
                }
                currentLevel++;
                curLevelSum = 0;
                continue;
            }

            elementLevel = levelQueue.remove();
            BinNode<Integer> binNode = binNodeQueue.remove();
            int data =binNode.getData();
            if( data == num) curLevelSum++;
            if(binNode.getLeft() != null){
                binNodeQueue.add(binNode.getLeft());
                levelQueue.add(elementLevel+1);
            }
            if(binNode.getRight() != null){
                binNodeQueue.add(binNode.getRight());
                levelQueue.add(elementLevel+1);
            }

        }
        if(max_sum < curLevelSum)
            return currentLevel;
       return max_level;
    }
}
