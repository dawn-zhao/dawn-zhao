package com.dawn.zhao.dao;

import com.dawn.zhao.bean.Node;

import java.util.List;

public interface TreeNodeMapper {

    /**
     * 获取节点树
     */
    List<Node> getNodeTree();
}
