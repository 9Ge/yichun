package com.enercomn.common;

/**
 * Created by CooL on 2018/1/26.
 */
public class ZtreeNode {

    /**
     * 树形结构自己ID
     */
    private String id;

    /**
     * 树形父节点ID
     */
    private String pId;

    /**
     * 树形结构名称
     */
    private String name;

    /**
     * 树形结构是否默认展开
     * 默认值 不展开
     */
    private String open = "false";

    /**
     * 节点序号
     */
    private int sort;

    /**
     * 树形结构显示图标
     */
    private String iconSkin = "";

    /**
     * 原始数据是否包含子节点,默认不包含=0
     */
    private String hasChild = "0";

    public String getIconSkin() {
        return iconSkin;
    }

    public void setIconSkin(String iconSkin) {
        this.iconSkin = iconSkin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getHasChild() {
        return hasChild;
    }

    public void setHasChild(String hasChild) {
        this.hasChild = hasChild;
    }
}
