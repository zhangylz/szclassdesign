package com.ylzh.helloworld.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "permission")

public class Permission implements Serializable {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 权限id
     */
	@Column(name="permission_id")
    private String permissionId;

    /**
     * 权限名称
     */
	@Column(name="name")
    private String name;

    /**
     * 权限描述
     */
	@Column(name="description")
    private String description;

    /**
     * 权限访问路径
     */
	@Column(name="url")
    private String url;

    /**
     * 权限标识
     */
	@Column(name="perms")
    private String perms;

    /**
     * 父级权限id
     */
	@Column(name="parent_id")
    private Integer parentId;

    /**
     * 类型   0：目录   1：菜单   2：按钮
     */
	@Column(name="type")
    private Integer type;

    /**
     * 排序
     */
	@Column(name="order_num")
    private Integer orderNum;

    /**
     * 图标
     */
	@Column(name="icon")
    private String icon;
    /**
     * 状态：1有效; 0无效
     */
	@Column(name="status")
    private Integer status;
	@Column(name="create_time")
    private Date createTime;
	@Column(name="update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取权限id
     *
     * @return permission_id - 权限id
     */
    public String getPermissionId() {
        return permissionId;
    }

    /**
     * 设置权限id
     *
     * @param permissionId 权限id
     */
    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId == null ? null : permissionId.trim();
    }

    /**
     * 获取权限名称
     *
     * @return name - 权限名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置权限名称
     *
     * @param name 权限名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取权限描述
     *
     * @return description - 权限描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置权限描述
     *
     * @param description 权限描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取权限访问路径
     *
     * @return url - 权限访问路径
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置权限访问路径
     *
     * @param url 权限访问路径
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    /**
     * 获取父级权限id
     *
     * @return parent_id - 父级权限id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父级权限id
     *
     * @param parentId 父级权限id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取状态：1有效；2删除
     *
     * @return status - 状态：1有效；2删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：1有效；2删除
     *
     * @param status 状态：1有效；2删除
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
