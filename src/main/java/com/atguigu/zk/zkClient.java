package com.atguigu.zk;


import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class zkClient {
    private String connectString = "hadoop102:2181,hadoop103:2181,hadoop104:2181";
    private int sessionTimeout = 2000;
    private ZooKeeper zkClient;

    @Before
    public void init() throws IOException {

        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                //监听
//                System.out.println("----------------------------");
//                List<String> children = null;
//                try {
//                    children = zkClient.getChildren("/", true);
//                    for (String child : children) {
//                        System.out.println(child);
//                    }
//
//                    System.out.println("-------------------");
//                } catch (KeeperException e) {
//                    throw new RuntimeException(e);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }

            }
        }
        );
    }

    //    //创建子节点
    @Test
    public void create() throws InterruptedException, KeeperException {
        String nodeCreate = zkClient.create("/hadoop", "zookeeper.avi".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    //删除子节点
    @Test
   public void delete() throws InterruptedException, KeeperException {
        //1.删除单个节点
        zkClient.delete("/hadoop",0);
    }
    //监听节点变化
    @Test
    public void getChildren() throws InterruptedException, KeeperException {
        //延时
        Thread.sleep(Long.MAX_VALUE);
    }
    //判断节点是否存在
    @Test
    public void exist() throws InterruptedException, KeeperException {
        Stat stat = zkClient.exists("/sanguo", false);

        System.out.println(stat==null? "not exist":"exist");
    }
}


