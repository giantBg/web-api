//package com.bingcore.web;
//
//import org.I0Itec.zkclient.ZkClient;
//import org.junit.Test;
//
///**
// * Created by xubing on 16/5/28.
// */
//public class zkTest {
//    @Test
//    public void testZkClient() {
//        String zkAddress = "127.0.0.1:4180";
//        String zkClusterAddress = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
//        ZkClient zkClient = new ZkClient(zkAddress);
//
//        String node = "/app2";
//        if (!zkClient.exists(node)) {
//            zkClient.createPersistent(node, "hello zk");
//        }
//        System.out.println(zkClient.readData(node));
//    }
//}
