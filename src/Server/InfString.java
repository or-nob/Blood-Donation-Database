package Server;

import java.io.Serializable;
import java.util.function.ObjLongConsumer;

/**
 * Created by user on 12/10/2015.
 */
public class InfString implements Serializable {
    Inf Obj;
    String S;
    public InfString(Inf Obj, String S){
        this.Obj = Obj;
        this.S = S;
    }
    public InfString(String S){
        this.S = S;
    }
    Inf getObj(){return Obj;}
    String getS() {return S;}
}
