import org.junit.Test;

import java.io.File;

/**
 * @author issuser
 * @date 2019-07-02 10:00
 */
public class SJWLTest {

    @Test
    public void test(){
        for(int i=25;i<27;i++){
            String str = "";
            if(i<10){
                str = "0"+i;
            }else{
                str = i+"";
            }
            new File("C:\\Users\\issuser\\Desktop\\sjwl\\test"+str).mkdirs();
        }
    }

    @Test
    public void testInfo3(){
        String str ="处理月\t承运年月\t出票年月\t承运日期\t出票日期\t提前出票分组\t始发责任区代码\t始发责任区名称\t销售责任区代码\t始发大区代码\t始发大区名称\t销售责任区名称\t销售单位属性\t销售大区代码\t销售大区名称\t考核责任区代码\t考核责任区名称\t销售大区属性\t考核单位属性\t考核大区代码\t考核大区名称\t考核大区属性\t海外站点单位\t本异地始发标识\t出票渠道\t渠道细分\t渠道种类\t代理人代号\t代理人名称\t代理人所属国家代码\t代理人所属国家\t代理人所在城市代码\t代理人所在城市名称\t直销/分销\t捆绑代理名称\t统签标识\t统签协议\t统签协议名称\t票面航程\t票面航程始发机场代码\t票面航程国际/国内标识\t直达/中转标识\t航线类型\t航线分区\t航线单位归属\t航线大区单位归属\t航段类型\t航段始发城市名称\t航段到达城市名称\t航段始发机场代码\t航段到达机场代码\t航段起止机场代码\t实际承运航班号\t运输子舱位\t销售子舱位\t实际运价基础\t运价折扣率分组\t运输座位等级\t销售座位等级\t高端舱位标识\t长航线标识\t航线代号\t航线机场代码全程\t航线管辖责任区\t国内/国际票\t本航/外航票\t票证种类\t数据来源渠道\t销售货币代码\t订座数据来源\t政府采购标识\t政府采购编码\t团散标识\t数据类型\t机型\t机号\t实际承运单位\tEMD类型代码\tEMD种类代码\tEMD用途代码\t旅客人数\t免票旅客\t分摊收入\t净收入\t额外手续费\t客标准手续费\t燃油费\t航空保险费（人民币）\t动态兑换免票还原收入";

        System.out.println(str.split("\t").length);
    }
}
