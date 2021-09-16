package com.enercomn.utils;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {
    private List<TreeBean> treeBeanList = new ArrayList<>();
    public TreeUtil(List<TreeBean> treeBeanList) {
        this.treeBeanList = treeBeanList;
    }

    //建立树形结构
    public List<TreeBean> builTree(String projectId){
        List<TreeBean> treeAreaTreeBeans =new ArrayList<>();
        for(TreeBean treeBeanNode : getRootNode(projectId)) {
            treeBeanNode =buildChilTree(treeBeanNode);
            treeAreaTreeBeans.add(treeBeanNode);
        }
        return treeAreaTreeBeans;
    }

    //递归，建立子树形结构
    private TreeBean buildChilTree(TreeBean pNode){
        List<TreeBean> chilAreaTreeBeans =new ArrayList<>();

        for(TreeBean treeBeanNode : treeBeanList) {
            if(treeBeanNode.getCParentId().equals(pNode.getId())) {
                chilAreaTreeBeans.add(buildChilTree(treeBeanNode));
            }
        }
        pNode.setChildrenList(chilAreaTreeBeans);
        return pNode;
    }

    //获取根节点
    private List<TreeBean> getRootNode(String projectId) {
        List<TreeBean> rootAreaTreeBeanLists =new ArrayList<TreeBean>();
        for(TreeBean treeBeanNode : treeBeanList) {
            if(treeBeanNode.getCParentId().equals(projectId)) {
                rootAreaTreeBeanLists.add(treeBeanNode);
            }
        }
        return rootAreaTreeBeanLists;
    }
}
