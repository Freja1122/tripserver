package com.tongji.testserver.service;

import com.tongji.testserver.domain.LoadHotNode;
import com.tongji.testserver.domain.PsgHotNode;

/**
 * @program: testserver
 * @description: 载客热点和上客热点
 * @author: Annntn
 * @create: 2017-12-21 09:02
 **/

public interface HotNodeService {

    public LoadHotNode createLoadHotNode(double latitude, double longitude);

    public PsgHotNode createPsgHotNode(double latitude, double longitude);

    public void deleteLoadHotNode(long id);

    public void deletePsgHotNode(long id);

    public String getHotExtent(double latitude, double longitude);

}
