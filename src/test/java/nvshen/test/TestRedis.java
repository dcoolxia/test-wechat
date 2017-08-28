package nvshen.test;

import java.util.LinkedHashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 测试redis秒杀
 * @author David
 *
 */
public class TestRedis {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大连接数
        poolConfig.setMaxTotal(10);
        // 最大空闲数
        poolConfig.setMaxIdle(1);
        // 最大允许等待时间，如果超过这个时间还未获取到连接，则会报JedisException异常：
        // Could not get a resource from the pool
        poolConfig.setMaxWaitMillis(1000);
        Set<HostAndPort> nodes = new LinkedHashSet<HostAndPort>();
        nodes.add(new HostAndPort("10.1.27.121", 6379));
        nodes.add(new HostAndPort("10.1.27.121", 6380));
        nodes.add(new HostAndPort("10.1.27.121", 6381));
        nodes.add(new HostAndPort("10.1.27.121", 6382));
        nodes.add(new HostAndPort("10.1.27.121", 6383));
        nodes.add(new HostAndPort("10.1.27.121", 6384));
        final JedisCluster cluster = new JedisCluster(nodes, poolConfig);
        
        cluster.set("oooonumber", "100");
        
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<100;i++) {
                    long incrBy = cluster.incrBy("oooonumber", -1);
                    if (incrBy == 0) {
                        System.out.println("第1个客户：秒杀结束");
                    } else if (incrBy < 0) {
                        
                    } else {
                        System.out.println("第1个客户：本次剩余" + incrBy);
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<100;i++) {
                    long incrBy = cluster.incrBy("oooonumber", -1);
                    if (incrBy == 0) {
                        System.out.println("第2个客户：秒杀结束");
                    } else if (incrBy < 0) {
                        
                    } else {
                        System.out.println("第2个客户：本次剩余" + incrBy);
                    }
                }
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<100;i++) {
                    long incrBy = cluster.incrBy("oooonumber", -1);
                    if (incrBy == 0) {
                        System.out.println("第3个客户：秒杀结束");
                    } else if (incrBy < 0) {
                        
                    } else {
                        System.out.println("第3个客户：本次剩余" + incrBy);
                    }
                }
            }
        });
        
        t1.start();
        t2.start();
        t3.start();
        
//        try {
//            cluster.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}