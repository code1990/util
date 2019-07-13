import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
//import java.util.Base64;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
public class FileUtil {

    public static void main(String[] args) {
//        String str = "D:\\zzzzzzzzz\\19王道（建议看）\\组成原理\\考前冲刺";
//        if(new File(str).isDirectory()){
//            for (File f:new File(str).listFiles()) {
//                System.out.println(f.getName()+ReadVideoTime(f)+ReadVideoSize(f));
////                System.out.println(GetFileSize(f));
////                System.out.println(ReadVideoTime(f)+ReadVideoSize(f));
////                System.out.println();
////                System.out.println(f.);
//            }
//        }
        String[] selectedRole = new String[]{
                "8ac0219062050a850162b3908261572f@8ac0219062050a850162b41370b9655e",
                "8ac0219062050a850162b39e2e855d3a@8ac0219062050a850162b41370b9655e",
                "8ac0219062050a850162b39e88335d3e@8ac0219062050a850162b41370b9655e",
                "8ac0219062050a850162b39e88335d3e@8ac02191624cccc60162b8a8c5691c52"
        };
        String id = "";
        StringBuilder sb = new StringBuilder();
        for(int i =0;i<selectedRole.length;i++){
            String roleId = selectedRole[i];
            String idTmp = roleId.split("@")[1];
            if(!sb.toString().contains(idTmp)){
                sb.append(idTmp+",");
            }
        }
        id = sb.substring(0,sb.length()-1).toString();
        if(id.contains(",")){
            id = id.replaceAll(",", "','");
        }
        System.out.println(id);
    }
    public static String GetFileSize(File file){
        String size = "";
        if(file.exists() && file.isFile()){
            long fileS = file.length();
            DecimalFormat df = new DecimalFormat("#.00");
            if (fileS < 1024) {
                size = df.format((double) fileS) + "BT";
            } else if (fileS < 1048576) {
                size = df.format((double) fileS / 1024) + "KB";
            } else if (fileS < 1073741824) {
                size = df.format((double) fileS / 1048576) + "MB";
            } else {
                size = df.format((double) fileS / 1073741824) +"GB";
            }
        }else if(file.exists() && file.isDirectory()){
            size = "";
        }else{
            size = "0BT";
        }
        return size;
    }

    public static String ReadVideoTime(File source) {
        Encoder encoder = new Encoder();
        String length = "";
        try {
            MultimediaInfo m = encoder.getInfo(source);
            long ls = m.getDuration()/1000;
            int hour = (int) (ls/3600);
            int minute = (int) (ls%3600)/60;
            int second = (int) (ls-hour*3600-minute*60);
            length = hour+"'"+minute+"''"+second+"'''";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return length;
    }




    /**
     * 获取视频大小
     * @param source
     * @return
     */
    public static String ReadVideoSize(File source) {
        FileChannel fc= null;
        String size = "";
        try {
            @SuppressWarnings("resource")
            FileInputStream fis = new FileInputStream(source);
            fc= fis.getChannel();
            BigDecimal fileSize = new BigDecimal(fc.size());
            size = fileSize.divide(new BigDecimal(1048576), 2, RoundingMode.HALF_UP) + "MB";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null!=fc){
                try{
                    fc.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return size;
    }
}
