package com.dawn.zhao.service.impl;

import com.dawn.zhao.bean.Node;
import com.dawn.zhao.dao.TreeNodeMapper;
import com.dawn.zhao.service.TreeNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 节点业务逻辑实现类
 *
 * Created by bysocket on 24/01/2018.
 */
@Service
public class TreeNodeServiceImpl implements TreeNodeService {

    @Autowired
    private TreeNodeMapper treeNodeDao;

    @Override
    public List<Node> getNodeTree() {
        return treeNodeDao.getNodeTree();
    }
}
