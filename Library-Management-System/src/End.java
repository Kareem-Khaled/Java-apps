
import java.util.ArrayList;

class End extends Operation {

    public End(String userName) {
        super.clearFile();
        for (Publication itr : list) {
            super.finish(itr.name, itr.edition, itr.author, itr.type, itr.quantity);
        }
        System.out.println("BYe " + userName);
        System.exit(0);
    }

}
