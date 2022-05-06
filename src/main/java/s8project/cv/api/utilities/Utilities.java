package s8project.cv.api.utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Utilities {

    public static List<?> copy(List<?> list){
        Iterator it = list.iterator();
        List copy = new ArrayList();
        for(int i=0; i< list.size(); i++){
            copy.add(it.next());
        }
        return copy;
    }
}
