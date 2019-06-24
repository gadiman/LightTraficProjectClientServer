package Server;

public class DataTransfer{
    String carNum;
    String lightTrafficNum;
    String crossroadNum;
    String name;
    DataTransfer(String carNum,String lightTrafficNum,String crossroadNum,String name){
        this.carNum = carNum;
        this.lightTrafficNum = lightTrafficNum;
        this.crossroadNum =crossroadNum;
        this.name = name;
    }
    public String print(){
        return this.carNum + "\n"
        +this.lightTrafficNum +"\n"
        +this.crossroadNum +"\n"
        +this.name +"\n"+"*********************************\n";
    }

}
