package com.enercomn.utils;

import java.util.ArrayList;
import java.util.List;

public class MenuTree {
    private List<AreaTreeBean> areaTreeBeanList = new ArrayList<>();
    public MenuTree(List<AreaTreeBean> areaTreeBeanList) {
        this.areaTreeBeanList = areaTreeBeanList;
    }

    //建立树形结构
    public List<AreaTreeBean> builTree(String projectId){
        List<AreaTreeBean> treeAreaTreeBeans =new ArrayList<AreaTreeBean>();
        for(AreaTreeBean areaTreeBeanNode : getRootNode(projectId)) {
            areaTreeBeanNode =buildChilTree(areaTreeBeanNode);
            treeAreaTreeBeans.add(areaTreeBeanNode);
        }
        return treeAreaTreeBeans;
    }

    //递归，建立子树形结构
    private AreaTreeBean buildChilTree(AreaTreeBean pNode){
        List<AreaTreeBean> chilAreaTreeBeans =new ArrayList<AreaTreeBean>();
        for(AreaTreeBean areaTreeBeanNode : areaTreeBeanList) {
            if(areaTreeBeanNode.getCParentId().equals(pNode.getCTamId())) {
                chilAreaTreeBeans.add(buildChilTree(areaTreeBeanNode));
            }
        }
        pNode.setChildren(chilAreaTreeBeans);
        return pNode;
    }

    //获取根节点
    private List<AreaTreeBean> getRootNode(String projectId) {
        List<AreaTreeBean> rootAreaTreeBeanLists =new ArrayList<AreaTreeBean>();
        for(AreaTreeBean areaTreeBeanNode : areaTreeBeanList) {
            if(areaTreeBeanNode.getCParentId().equals(projectId)) {
                rootAreaTreeBeanLists.add(areaTreeBeanNode);
            }
        }
        return rootAreaTreeBeanLists;
    }

}
