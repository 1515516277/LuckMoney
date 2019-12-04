package com.practice.util;

import com.practice.pojo.dto.RedPacketDTO;
import org.junit.Test;
import org.springframework.scheduling.annotation.Async;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {

    @Test
    //金额*100 按分计算
    public void randomMath() {
        System.out.println(99 / 98);
        int a = 100;
        int b = 0;
        int ran = 1;
        int i = 1;
        System.out.println(b);
        ThreadLocalRandom threadRandom = ThreadLocalRandom.current();
        if (a > 10) {
            ran = threadRandom.nextInt(1, 10);
            i = threadRandom.nextInt(1, a / ran);
        } else {
            i = threadRandom.nextInt(1, a);
        }

        System.out.println(i);
    }


    @Test
    public void toDouble() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int q = 0; q < 100; q++) {
            int i = r.nextInt(99) + 1;
            int b = i * 7 / 12;
//            System.out.println(i + "     " + b);
        }
    }

    @Test
    public void forTest() {
        for (int i = 50; i > 0; i--) {
            randomInt();
        }
    }

    @Test
    //金额单位:分
    public void randomInt() {
        DecimalFormat df = new DecimalFormat("#0.00");
        ThreadLocalRandom threadRandom = ThreadLocalRandom.current();
        int minMoney = 1; //最小金额
        int count = 10;   //红包个数
        int sumMoney = 60000;//红包总金额
        int surplusMoney = sumMoney;//剩余金额
        int makeMoney = 0; //已领取红包个数
        int forCount = count;//循环次数
//        System.out.println("总金额："+sumMoney);
        for (int i = forCount; i > 0; i--) {
            //人数减一
            count--;
//            System.out.println("count:"+count);
            //随机金额
//            System.out.println("最大："+surplusMoney);
            int maxMoney = surplusMoney - i;
            if (count > 0) {
                maxMoney = maxMoney / count;
                maxMoney = maxMoney < 2 ? 2 : maxMoney;
            }
            int randomMoney = threadRandom.nextInt(minMoney, maxMoney);
            randomMoney = randomMoney == 1 ? randomMoney : randomMoney - 1;

            //如果是最后一次分配剩下所有金额否则还是随机金额
            randomMoney = count == 0 ? surplusMoney : randomMoney;
            System.out.println("随机金额:" + randomMoney);
            //使用金额为 随机金额相加
            makeMoney += randomMoney;
            //剩余金额=总金额-使用金额
            surplusMoney = sumMoney - makeMoney;
            if (makeMoney > sumMoney) {
                System.out.println("金额分配异常");
                break;
            }
//            System.out.println("剩余红包金额："+surplusMoney);


        }
        System.out.println("总共使用平分金额：" + makeMoney);
        System.out.println();

    }

    @Async
    public void test2() {
        try {
            Thread.sleep(110000);
            System.out.println(222);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map redWars(RedPacketDTO redPacketDTO) {
        Map resultMap = new HashMap();
        try {
            if (redPacketDTO.getSurplusCount() <= 0) {
                resultMap.put("randomMoney", 0);
                return resultMap;
            }
            ThreadLocalRandom threadRandom = ThreadLocalRandom.current();
            Integer minMoney = 1; //最小金额
            Integer count = redPacketDTO.getSurplusCount();   //红包个数
            Integer surplusMoney = redPacketDTO.getSurplusMoney();//剩余金额
            Integer maxMoney = surplusMoney - count;//最大金额
            if (count > 0) {
                maxMoney = maxMoney / count;
                maxMoney = maxMoney < 2 ? 2 : maxMoney;
            }
            //随机金额
            int randomMoney = threadRandom.nextInt(minMoney, maxMoney);
            randomMoney = randomMoney == 1 ? randomMoney : randomMoney - 1;
            //如果是最后一次分配剩下所有金额否则还是随机金额
            randomMoney = count == 1 ? surplusMoney : randomMoney;
            //剩余金额=剩余金额-使用金额
            surplusMoney = surplusMoney - randomMoney;
            if (surplusMoney < 0) {
                System.out.println("金额分配异常");
                return null;
            }
            redPacketDTO.setSurplusCount(count - 1);
            redPacketDTO.setSurplusMoney(surplusMoney);
            resultMap.put("redPacketDTO", redPacketDTO);
            resultMap.put("randomMoney", randomMoney);
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 记录超过理想值的结果
     */
    private int maxRandom = 0;
    public RedPacketDTO randomAAA(RedPacketDTO redPacketDTO) {
        try {
            if (redPacketDTO.getSurplusCount() <= 0 && redPacketDTO.getSurplusMoney() <= 0) {
                System.out.println("END！ m:" + maxRandom);
                return null;
            }
            ThreadLocalRandom threadRandom = ThreadLocalRandom.current();
            Integer minMoney = 1; //最小金额
            Integer count = redPacketDTO.getSurplusCount();   //红包个数
            Integer surplusMoney = redPacketDTO.getSurplusMoney();//剩余金额
            Integer maxMoney = surplusMoney - count;//最大金额
            if (count > 0) {
                maxMoney = maxMoney / count;
                maxMoney = maxMoney < 2 ? 2 : maxMoney;
            }
            //随机金额
            int randomMoney = threadRandom.nextInt(minMoney, maxMoney);
            randomMoney = randomMoney == 1 ? randomMoney : randomMoney - 1;
            //如果是最后一次分配剩下所有金额否则还是随机金额
            randomMoney = count == 1 ? surplusMoney : randomMoney;
            //剩余金额=剩余金额-使用金额
            surplusMoney = surplusMoney - randomMoney;
            if (surplusMoney < 0) {
                System.out.println("金额分配异常");
                return null;
            }
            redPacketDTO.setSurplusCount(count - 1);
            redPacketDTO.setSurplusMoney(surplusMoney);
            System.out.print(randomMoney + "   ");
            if (maxRandom > redPacketDTO.getSumMoney() / redPacketDTO.getSumCount() * 2) {
                maxNum += maxRandom + ",";
            }
            maxRandom = randomMoney > maxRandom ? randomMoney : maxRandom;
            return redPacketDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    String maxNum = "";

    @Test
    public void randomTest() {
        for (int i = 0; i < 100; i++) {
            RedPacketDTO redPacketDTO = new RedPacketDTO();
            //总个数
            redPacketDTO.setSumCount(100);
            //总金额
            redPacketDTO.setSumMoney(100 * 100);
            //剩余金额
            redPacketDTO.setSurplusCount(redPacketDTO.getSumCount());
            //剩余个数
            redPacketDTO.setSurplusMoney(redPacketDTO.getSumMoney());

            while (newRandom(redPacketDTO, shopRed) != null) {
            }
            System.out.println("===================");
        }
        System.out.println("END");
    }


    /**
     * 商家红包
     */
    private final int shopRed = 1;
    /**
     * 平台红包
     */
    private final int platformRed = 2;

    public RedPacketDTO newRandom(RedPacketDTO redPacketDTO, int type) {
        try {
            if (redPacketDTO.getSurplusCount() <= 0) {
                System.out.println("END!");
                return null;
            }
            //初始化数据
            ThreadLocalRandom threadRandom = ThreadLocalRandom.current();
            //总金额
            int sumMoney = redPacketDTO.getSumMoney();
            //总个数
            int sumCount = redPacketDTO.getSumCount();
            //最小金额
            int minMoney = 1;
            //剩余个数
            int count = redPacketDTO.getSurplusCount();
            //剩余金额
            int surplusMoney = redPacketDTO.getSurplusMoney();
            //最大金额
            int maxMoney = (sumMoney - count) / sumCount * 2;
            if (maxMoney > surplusMoney - count) {
                maxMoney = surplusMoney - count;
            }
            //随机金额
            int randomMoney = 0;
            //商家抽红包
            if (shopRed == type) {
                if (maxMoney == minMoney) {
                    randomMoney = minMoney;
                } else {
                    randomMoney = threadRandom.nextInt(minMoney, maxMoney);
                }
                //如果是最后一次分配剩下所有金额否则还是随机金额
                randomMoney = count == 1 ? surplusMoney : randomMoney;
                //剩余金额=剩余金额-使用金额
                surplusMoney = surplusMoney - randomMoney;
                if (moneyBeyond(surplusMoney)) {
                    return null;
                }
                redPacketDTO.setSurplusCount(count - 1);
                //平台抽红包
            } else if (platformRed == type) {
                //平台红包 个数必须是双数
                if (count % platformRed != 0) {
                    return null;
                }
                do {
                    randomMoney = threadRandom.nextInt(minMoney, maxMoney);
                    //剩余金额不够下次随机 在随机一次
                } while ((surplusMoney - 2 * randomMoney) <= count);
                //如果是最后一次分配剩下所有金额否则还是随机金额
                randomMoney = count == 2 ? surplusMoney / 2 : randomMoney;
                //剩余金额=剩余金额-使用金额
                surplusMoney = surplusMoney - randomMoney * 2;
                if (moneyBeyond(surplusMoney)) {
                    return null;
                }
                redPacketDTO.setSurplusCount(count - 2);
            }
            redPacketDTO.setSurplusMoney(surplusMoney);
            System.out.print(randomMoney + " ");
            return redPacketDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean moneyBeyond(Integer surplusMoney) {
        if (surplusMoney < 0) {
            System.out.println("金额分配异常");
            return true;
        }
        return false;
    }

    @Test
    public void tttt() {
        Double ceil = Math.ceil(0.1);
        int c = 1 / 2 / 3;
        System.out.println(
                c
        );
    }
}
