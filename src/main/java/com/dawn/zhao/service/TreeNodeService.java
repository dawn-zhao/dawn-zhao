package com.dawn.zhao.service;

import com.dawn.zhao.bean.Node;

import java.util.List;

/**
 * 节点业务逻辑接口类
 *
 * Created by bysocket on 24/01/2018.
 */
public interface TreeNodeService {

    /**
     * 获取节点树
     */
    List<Node> getNodeTree();
}
