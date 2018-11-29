package sql;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author issuser
 * github下载地址：https://github.com/MicrosoftArchive/redis/releases
 * 下载完成以后 需要设置开机自动启动 否则无法正常访问
 * 1)先卸载服务：
 * redis-server --service-uninstall
 * 2)然后再安装：
 * redis-server--service-install redis.windows.conf
 * <p>
 * 启停：
 * 启动服务：redis-server --service-start
 * 停止服务：redis-server --service-stop
 * <p>
 * redis.clients.jedis.JedisPool 创建 Redis 连接池对象。
 * redis.clients.jedis.JedisPoolConfig 对连接池进行配置，
 */
public class RedisUtil {

    public void setRedisPool() {
        JedisPoolConfig poolCfg = new JedisPoolConfig();
        //最大空闲数
        poolCfg.setMaxIdle(50);
        //最大连接数
        poolCfg.setMaxTotal(100);
        //最大等待毫秒数
        poolCfg.setMaxWaitMillis(20000);
        //使用配置创建连接池
        JedisPool pool = new JedisPool(poolCfg, "localhost");
        //从连接池中获取单个连接
        Jedis jedis = pool.getResource();
        //如果需密码
        //jed auth assword

    }

    public void TestJedis() {

    }

    @Test
    public void test() {
        //实例化一个客户端
        String host = "localhost";
        int port = 6379;
        String password = "password";
        //可以使用localhost
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //jedis.auth （” password ”）；／／如果需密码
        //ping下，看看是否通的
        System.out.println("Server is running: " + jedis.ping());
        //保存一个
        jedis.set("leiTest", "localhost Connection  sucessfully");
        //获取一个
        String value = jedis.get("leiTest");
        System.out.println("leiTest键值为: " + value);
        // 测试一下redis的读写性能
        int i = 0;// 记录操作次数
        try {
            long start = System.currentTimeMillis();
            while (true) {
                long end = System.currentTimeMillis();
                //当大于等于 1000 毫秒（相当于 秒）时，结束操作 10037条数据
                if (end - start >= 1000) {
                    System.out.println(i);
                    break;
                }
                i++;
                jedis.set("test" + i, i + "");
            }
        } finally {//关闭连接
            jedis.close();
        }
    }
}