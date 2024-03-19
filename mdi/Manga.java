package mdi;

public class Manga {
    public static void main(String[] args){
        
        String storeName = null;
        if(args.length > 0){
            storeName = args[0];
        }
        else{
            storeName = "Nameless";
        }

        Controller newController = new Controller(storeName);
        newController.mdi();
    }
}
