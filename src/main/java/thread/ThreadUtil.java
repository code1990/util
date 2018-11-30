package thread;

import io.TxtUtil;
import net.IPUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThreadUtil implements Runnable {

//    private int ticket=10;
    private int number = 31657;
    public static  Map<Integer,String> map  = null;
    public ThreadUtil(){
    }
    @Override
    public void run() {
        for(int i=0;i<2000000;i++){
            if(this.number>0){
                //休眠1s秒中，为了使效果更明显，否则可能出不了效果
                String result = "";
                try {
                    Thread.sleep(1000);
                    String value = map.get(number);
                    String[] array = value.split(";");
                    String ip = array[0];
                    int port = new Integer(array[1]);
                    boolean flag = IPUtil.isOk(ip,port);
                    if (flag){
                        result = "200";
                    }else {
                        result = "error";
                    }
//                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(number+":"+result+":"+map.get(number));
//                System.out.println(Thread.currentThread().getName()+"号窗口卖出："+(this.number--)+"IP");
                this.number--;
            }

        }
    }

    public static void main(String args[]){
        ThreadUtil demo=new ThreadUtil();
        //基于火车票创建三个窗口
//        new Thread(demo,"a").start();
//        new Thread(demo,"b").start();
//        new Thread(demo,"c").start();
        List<String> list = TxtUtil.readTxt("C:\\Users\\issuser\\Desktop\\1111.txt");
        map = new HashMap<Integer,String>();
        for (int i = 0; i < list.size(); i++) {
            map.put(i+1,list.get(i));
        }
//        for (Map.Entry m:map.entrySet()){
//            System.out.println(m.getKey()+":"+m.getValue());
//        }
        for(int i =0;i<= 100;i++){
            new Thread(demo,i+"").start();
        }
    }

}
