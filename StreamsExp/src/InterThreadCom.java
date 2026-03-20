import java.util.ArrayList;
import java.util.List;

class Helper{
    List<Integer> list = new ArrayList<>();

    synchronized  void produce(int i){
        try {
            while(list.size() >= 3){
                wait();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        list.add(i);
        System.out.println("Produced : " + i);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(list.size() ==3){
            notify();
        }
    }

    synchronized  void consume(){
        try {
            while(list.size() < 3){
                wait();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Consumed : " + list);
        list.clear();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        notify();

    }

}


class Producer extends  Thread{

    Helper helper;
    public Producer(Helper helper){
        this.helper = helper;
    }
    @Override
    public void run(){
        int i = 0;
        while(true){
            helper.produce(i);
            i++;
        }
    }

}

class ConsumerTh extends Thread{
    Helper helper;
    public ConsumerTh(Helper helper){
        this.helper = helper;
    }

    @Override
    public void run(){
        while (true){
            helper.consume();
        }
    }
}

public class InterThreadCom {
    public static void main(String[] args) {
        Helper helper = new Helper();

        Producer producer = new Producer(helper);
        ConsumerTh consumer = new ConsumerTh(helper);

        producer.start();
        consumer.start();
    }
}
