package net;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IPUtil {

    public static List<String> getIPList(){
        List<String> ipList = new ArrayList<>();
        ipList.add("116.62.4.184;8118");
        ipList.add("219.142.214.118;1080");
        ipList.add("222.92.42.178;808");
        ipList.add("114.249.116.142;9000");
        ipList.add("114.116.10.21;3128");
        ipList.add("115.28.209.249;3128");
        ipList.add("162.105.87.211;8118");
        ipList.add("117.35.51.77;53281");
        ipList.add("101.37.79.125;3128");
        ipList.add("111.230.183.90;8000");
        ipList.add("101.132.122.230;3128");
        ipList.add("118.31.79.226;3128");
        ipList.add("183.129.207.73;14823");
        ipList.add("218.14.115.211;3128");
        ipList.add("61.128.208.94;3128");
        ipList.add("27.191.234.69;9999");
        ipList.add("112.74.207.50;3128");
        ipList.add("219.144.232.213;9999");
        ipList.add("113.200.56.13;8010");
        ipList.add("218.60.8.98;3129");
        ipList.add("163.125.64.218;9797");
        ipList.add("218.60.8.83;3129");
        ipList.add("121.69.37.6;9797");
        ipList.add("218.60.8.99;3129");
        ipList.add("123.138.89.132;9999");
        ipList.add("101.236.32.119;3128");
        ipList.add("120.92.22.253;3128");
        ipList.add("106.14.132.11;3128");
        ipList.add("36.110.234.244;80");
        ipList.add("125.46.0.62;53281");
        ipList.add("218.241.234.48;8080");
        ipList.add("221.6.201.18;9999");
        ipList.add("203.93.209.163;53281");
        ipList.add("112.250.109.173;53281");
        ipList.add("58.247.127.145;53281");
        ipList.add("106.13.2.32;3128");
        ipList.add("111.198.154.116;8888");
        ipList.add("58.58.48.138;53281");
        ipList.add("222.52.142.244;8080");
        ipList.add("119.39.68.57;808");
        ipList.add("106.12.107.3;8118");
        ipList.add("119.123.246.123;9000");
        ipList.add("222.221.11.119;3128");
        ipList.add("115.233.210.218;808");
        ipList.add("180.104.63.159;8118");
        ipList.add("14.221.165.139;9797");
        ipList.add("106.12.150.213;808");
        ipList.add("219.246.90.204;3128");
        ipList.add("222.74.61.98;53281");
        ipList.add("221.204.119.175;9797");
        ipList.add("113.200.214.164;9999");
        ipList.add("183.6.120.29;1080");
        ipList.add("203.130.46.108;9090");
        ipList.add("119.31.210.170;7777");
        ipList.add("124.237.83.14;53281");
        ipList.add("119.3.3.51;8010");
        ipList.add("113.116.121.78;9000");
        ipList.add("14.20.235.106;9797");
        ipList.add("59.46.44.6;9999");
        ipList.add("124.152.32.140;53281");
        ipList.add("58.249.55.222;9797");
        ipList.add("218.75.144.25;9000");
        ipList.add("220.171.96.155;50557");
        ipList.add("171.37.174.79;9797");
        ipList.add("101.6.34.65;1080");
        ipList.add("221.205.88.61;9797");
        ipList.add("183.14.77.103;9797");
        ipList.add("49.83.29.6;53281");
        ipList.add("183.129.207.80;21776");
        ipList.add("60.176.46.238;8123");
        ipList.add("124.207.82.166;8008");
        ipList.add("118.182.33.6;32559");
        ipList.add("119.123.172.157;9797");
        ipList.add("61.234.37.106;8080");
        ipList.add("117.35.51.66;53281");
        ipList.add("180.168.198.141;18118");
        ipList.add("101.6.59.131;1080");
        ipList.add("119.51.89.18;1080");
        ipList.add("124.250.70.76;8123");
        ipList.add("58.63.112.186;32231");
        ipList.add("113.65.160.148;9797");
        ipList.add("183.131.154.143;10072");
        ipList.add("183.131.154.147;10112");
        ipList.add("118.250.68.99;1080");
        ipList.add("116.22.59.48;808");
        ipList.add("182.18.13.149;53281");
        ipList.add("183.129.207.82;18118");
        ipList.add("112.95.205.124;8888");
        ipList.add("101.6.52.197;1080");
        ipList.add("153.3.12.255;8123");
        ipList.add("113.116.58.102;9000");
        ipList.add("182.88.151.246;9797");
        ipList.add("122.227.139.170;3128");
        ipList.add("183.129.244.16;18118");
        ipList.add("123.161.20.141;9797");
        ipList.add("101.76.252.73;1080");
        ipList.add("114.119.116.93;61066");
        ipList.add("113.110.200.88;9797");
        ipList.add("222.84.56.164;8118");
        ipList.add("180.140.191.233;36820");
        ipList.add("183.129.244.17;10010");
        ipList.add("211.101.136.86;8080");
        ipList.add("123.139.56.238;9999");
        ipList.add("114.247.222.212;80");
        ipList.add("14.115.107.21;808");
        ipList.add("222.217.19.248;8080");
        ipList.add("112.95.205.123;8888");
        ipList.add("112.95.205.49;8888");
        ipList.add("119.176.80.220;9999");
        ipList.add("124.16.90.243;1080");
        ipList.add("211.103.198.106;8118");
        ipList.add("124.160.56.76;37511");
        ipList.add("183.129.207.83;10800");
        ipList.add("119.123.175.59;9797");
        ipList.add("163.125.67.53;9797");
        ipList.add("140.143.170.222;8118");
        ipList.add("218.28.58.150;53281");
        ipList.add("222.186.45.11;61374");
        ipList.add("58.251.49.4;43007");
        ipList.add("111.225.8.62;9999");
        ipList.add("120.83.49.90;9000");
        ipList.add("116.18.228.234;9797");
        ipList.add("112.95.205.122;9999");
        ipList.add("182.88.9.50;9797");
        ipList.add("114.95.228.80;8118");
        ipList.add("27.202.138.75;9999");
        ipList.add("171.221.239.11;808");
        ipList.add("114.243.130.246;9797");
        ipList.add("59.78.35.129;1080");
        ipList.add("180.169.186.155;1080");
        ipList.add("115.171.203.105;9000");
        ipList.add("58.243.50.184;53281");
        ipList.add("113.120.113.217;8118");
        ipList.add("219.159.38.200;56210");
        ipList.add("59.38.61.149;9797");
        ipList.add("112.95.20.105;8888");
        ipList.add("210.77.4.162;1080");
        ipList.add("113.251.175.36;1080");
        ipList.add("61.152.248.147;80");
        ipList.add("222.132.145.122;53281");
        ipList.add("222.22.66.211;8080");
        ipList.add("171.107.59.225;1080");
        ipList.add("219.245.3.4;3128");
        ipList.add("113.140.1.82;53281");
        ipList.add("103.21.116.85;3128");
        ipList.add("14.155.115.25;9000");
        ipList.add("112.95.24.130;8088");
        ipList.add("182.88.40.92;9797");
        ipList.add("112.95.205.125;8888");
        ipList.add("183.166.129.53;8080");
        ipList.add("116.22.250.40;9797");
        ipList.add("123.172.82.117;53281");
        ipList.add("114.249.117.181;9000");
        ipList.add("211.99.26.183;808");
        ipList.add("58.17.125.215;53281");
        ipList.add("123.117.32.229;9000");
        ipList.add("14.20.235.117;808");
        ipList.add("114.249.119.157;9000");
        ipList.add("202.199.159.130;40670");
        ipList.add("222.180.162.245;9999");
        ipList.add("125.123.140.113;9000");
        ipList.add("202.120.2.67;8123");
        ipList.add("114.249.115.239;9000");
        ipList.add("101.231.50.154;8000");
        ipList.add("59.78.2.140;1080");
        ipList.add("175.102.3.98;8089");
        ipList.add("113.78.67.170;9797");
        ipList.add("112.95.26.9;8088");
        ipList.add("163.125.31.43;8118");
        ipList.add("171.36.40.75;9797");
        ipList.add("14.115.107.7;808");
        ipList.add("222.186.45.115;62222");
        ipList.add("110.251.110.129;9000");
        ipList.add("114.249.115.151;9000");
        ipList.add("112.95.205.127;8888");
        ipList.add("101.76.248.79;1080");
        ipList.add("119.131.88.187;9797");
        ipList.add("112.95.205.117;8888");
        ipList.add("222.52.142.243;8080");
        ipList.add("112.95.204.30;8888");
        ipList.add("116.30.197.93;9797");
        ipList.add("116.232.39.16;8118");
        ipList.add("113.251.172.98;8123");
        ipList.add("125.123.122.197;9000");
        ipList.add("124.235.74.152;8118");
        ipList.add("60.191.57.78;10800");
        ipList.add("59.38.63.163;9797");
        ipList.add("114.249.114.7;9000");
        ipList.add("114.249.117.44;9000");
        ipList.add("114.249.117.140;9000");
        ipList.add("1.196.161.203;9999");
        ipList.add("14.20.235.123;808");
        ipList.add("116.226.35.217;39502");
        ipList.add("182.88.184.195;9797");
        ipList.add("218.106.98.171;53281");
        ipList.add("182.88.244.214;9797");
        ipList.add("112.95.190.107;9797");
        ipList.add("112.95.190.105;9797");
        ipList.add("112.95.190.109;9797");
        ipList.add("112.95.190.108;9797");
        return ipList;
    }

    public static void changeIP(){
        List<String> ipList = IPUtil.getIPList();
        int r = (int)(Math.random()*200+1);
        String[] iphost = ipList.get(r).split(";");
        System.getProperties().setProperty("http.proxyHost", iphost[0]);
        System.getProperties().setProperty("http.proxyPort", iphost[1]);
        System.out.println(System.getProperty("http.proxyHost")+";"+System.getProperty("http.proxyPort"));
    }

    public static List<String> IPIsable(List<String> list) {
        String ip = "";
        String port = "";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        for(int i = 0; i < list.size(); i++) {
            String[] iphost = list.get(i).split(";");
            ip = iphost[0];
            port = iphost[1];

            HttpHost proxy = new HttpHost(ip, Integer.parseInt(port));
            RequestConfig config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(3000).
                    setSocketTimeout(3000).build();
            HttpGet httpGet = new HttpGet("https://www.baidu.com");
            httpGet.setConfig(config);

            httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;" +
                    "q=0.9,image/webp,*/*;q=0.8");
            httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");
            httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit" +
                    "/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");

            try {
                response = httpClient.execute(httpGet);
            } catch (IOException e) {
                System.out.println("不可用代理已删除" + list.get(i).split(";")[0] );
                list.remove(list.get(i));
                i--;
            }
        }

        try {
            httpClient.close();
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 给定ip来判断该ip是否可用
     */
    public boolean isOk(String ip,int port){
        boolean result = false;
        int status = 0;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = null;
            HttpHost proxy = new HttpHost(ip, port);
            RequestConfig config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(3000).
                    setSocketTimeout(3000).build();
            HttpGet httpGet = new HttpGet("https://www.baidu.com");
            httpGet.setConfig(config);

            httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;" +
                    "q=0.9,image/webp,*/*;q=0.8");
            httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");
            httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit" +
                    "/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
            response = httpClient.execute(httpGet);
            status = response.getStatusLine().getStatusCode();
            System.out.println(status+":"+ip+":"+port);
        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("error");
        }
//        System.out.println(status);
        if(200 == status){
            result = true;
        }
        return  result;
    }

    @Test
    public void tests(){
        List<String> list = IPUtil.getIPList();
        for(String str:list){
            String[] array = str.split(";");
            String ip = array[0];
            int port = new Integer(array[1]);
            try {
//                System.out.println();
                isOk(ip,port);
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
    }
}
