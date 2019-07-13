import io.VideoUtil;
import org.junit.Test;

import java.io.File;

public class VideoTest {

    @Test
    public void getTime(){
        File file = new File("F:\\尚硅谷Java数据结构和算法\\视频");
        if(file.isDirectory() && file.exists()){
            File[] files = file.listFiles();
            int h =0;
            int m = 0;
            int s= 0;
            for (File f:files){
                String name = f.getName();
                if(f.isDirectory()){
                    File[] filess = f.listFiles();
                    for (File ff:filess){
                        System.out.println(name+"  "+ff.getName()+"\t"+ VideoUtil.ReadVideoTime(ff));
                        String times = VideoUtil.ReadVideoTime(ff);
                        h+=new Integer(times.split(":")[0]);
                        m+=new Integer(times.split(":")[1]);
                        s+=new Integer(times.split(":")[2]);
                    }

                }else if(f.getName().endsWith(".avi")){
                    System.out.println(f.getName());
//                    System.out.println(f.getName()+"\t"+VideoUtil.ReadVideoTime(f));
//                    String times = VideoUtil.ReadVideoTime(f);
//                    h+=new Integer(times.split(":")[0]);
//                    m+=new Integer(times.split(":")[1]);
//                    s+=new Integer(times.split(":")[2]);
                }

            }

            System.out.println(h);
            System.out.println(m);
            System.out.println(s);
            h = h + (m+s/60)/60;
            m = (m+s/60)%60;
            s = s%60;
            System.out.println("总时长"+h+"小时"+m+"分钟"+s+"秒");
//            System.out.println(m*60+818-4*60*60-40*60-38);

        }
    }
}
