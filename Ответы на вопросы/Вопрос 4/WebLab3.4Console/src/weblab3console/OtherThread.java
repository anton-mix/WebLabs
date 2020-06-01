
package weblab3console;

import java.util.ArrayList;
import java.util.List;
import lab3.SoapManager_Service;
import lab3.SqlObject;


public class OtherThread extends Thread{
    String res = "";
    List<SqlObject> res2 = null;
    String numberOperation = "";
    SoapManager_Service prob = null;
    static final Object lock = new Object();
    
    String buffId         = "";
    String buffName       = "";
    String buffSecondName = "";
    String buffAge        = "";
    String buffSex        = ""; 
    
    public void run() throws RuntimeException{
        try {
            switch (numberOperation) {
                case "1":
                    List<SqlObject> buffRes2 = prob.getSoapManagerPort().select(buffId, buffName, buffSecondName, buffAge, buffSex);
                    synchronized (lock) {
                        res2 = buffRes2;
                    }
                    break;
                case "2": {
                    String buffRes = prob.getSoapManagerPort().insert(buffId, buffName, buffSecondName, buffAge, buffSex);
                    synchronized (lock) {
                        res = buffRes;
                    }
                }
                break;
                case "3": {
                    String buffRes = prob.getSoapManagerPort().update(buffId, buffName, buffSecondName, buffAge, buffSex);
                    synchronized (lock) {
                        res = buffRes;
                    }
                }
                break;
                case "4": {
                    String buffRes = prob.getSoapManagerPort().delete(buffId);
                    synchronized (lock) {
                        res = buffRes;
                    }
                }
                break;
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex.toString());
        }
    }
    
    public String getRes(){
        String buff = "";
        synchronized (lock) {
            buff = new String(res);
            res = "";
        }
        return buff;
    }
    
    public List<SqlObject> getRes2(){
        List<SqlObject> buff = null;
        synchronized (lock) {
            if(res2 != null){
                buff = new ArrayList<SqlObject>();
                for(int i = 0; i < res2.size() ; i++) {
                    buff.add(res2.get(i));
                    }
            }
            res = null;
        }
        return buff;
    }
    
    public void setNumberOperation(String numberOperation) {
        this.numberOperation = numberOperation;
    }

    public void setProb(SoapManager_Service prob) {
        this.prob = prob;
    }

    public void setBuffId(String buffId) {
        this.buffId = buffId;
    }

    public void setBuffName(String buffName) {
        this.buffName = buffName;
    }

    public void setBuffSecondName(String buffSecondName) {
        this.buffSecondName = buffSecondName;
    }

    public void setBuffAge(String buffAge) {
        this.buffAge = buffAge;
    }

    public void setBuffSex(String buffSex) {
        this.buffSex = buffSex;
    }
}
