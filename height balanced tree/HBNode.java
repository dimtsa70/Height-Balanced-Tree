
package ce326.hw1;

// Dhmiourgia antikeimenou komvou
public class HBNode {
    int data;
    int height;
    int weight;
    HBNode left,right;

    public HBNode(int initdata) {
        data = initdata;
        height = 1;
        weight = 1;
        this.right = null;
        this.left = null;
                
    }
    
}