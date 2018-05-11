import com.google.gson.GsonBuilder;

import java.util.ArrayList;

/**
 * Created by ma_ab on 5/11/2018.
 */
public class MainBlockchain {


    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static int difficulty = 1 ;

    public static void main(String [] args){
        Block firstBlock = new Block("Hi This is my first block","0");
        Block secondBlock = new Block("Second block darling",firstBlock.hash);
        Block thirdBlock = new Block("Third baby Third",secondBlock.hash);

        System.out.println("Trying to mine block 1");
        firstBlock.mineBlock(difficulty);
        System.out.println("Trying to mine block 2");
        secondBlock.mineBlock(difficulty);
        System.out.println("Trying to mine block 3");
        thirdBlock.mineBlock(difficulty);

        blockchain.add(firstBlock);
        blockchain.add(secondBlock);
        blockchain.add(thirdBlock);

        System.out.println("\nBlockchain is Valid: " + isChainValid());


        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);
    }

    public static boolean isChainValid(){
        Block currentBlock ;
        Block prevBlock ;
        for(int i =1 ; i <blockchain.size();i++) {
            currentBlock = blockchain.get(i);
            prevBlock = blockchain.get(i-1);
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current Hashes not Equal");
                return false;
            }

            if(!prevBlock.hash.equals(currentBlock.prevHash)){
                System.out.println("Prev Hashes not Equal");
                return false;
            }

        }
        return true;
    }
}
