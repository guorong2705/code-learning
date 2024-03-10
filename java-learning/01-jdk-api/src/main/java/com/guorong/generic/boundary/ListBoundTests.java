package com.guorong.generic.boundary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 集合中元素的上限测试
 */
public class ListBoundTests {

    interface TreeNode<T> {
        /*编号*/
        String getId();

        /*父编号*/
        String getParentId();

        /*获取子节点*/
        List<T> getChildren();

        /*设置子节点*/
        void setChildren(List<T> children);
    }

    class Dept implements TreeNode<Dept> {

        private String id;

        private String parentId;

        private List<Dept> children;

        private String deptName;

        public Dept() {
        }

        public Dept(String id, String parentId, String deptName) {
            this.id = id;
            this.parentId = parentId;
            this.deptName = deptName;
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public String getParentId() {
            return parentId;
        }

        @Override
        public List<Dept> getChildren() {
            return children;
        }

        @Override
        public void setChildren(List<Dept> children) {
            this.children = children;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }
    }

    class Company implements TreeNode<Company> {

        private String id;

        private String parentId;

        private String companyName;

        private List<Company> children;

        public Company() {
        }

        public Company(String id, String parentId, String companyName) {
            this.id = id;
            this.parentId = parentId;
            this.companyName = companyName;
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public String getParentId() {
            return parentId;
        }

        @Override
        public List<Company> getChildren() {
            return children;
        }

        @Override
        public void setChildren(List<Company> children) {
            this.children = children;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }
    }

    private List<TreeNode> treeNodeList = new ArrayList<>();

    private List<Dept> deptList = new ArrayList<>();

    private List<Company> companyList = new ArrayList<>();

    @BeforeEach
    void prepare() {
        // ======================部门初始化数据================================
        deptList.add(new Dept("d1", null, "部门-1"));
        deptList.add(new Dept("d2", null, "部门-2"));
        deptList.add(new Dept("d1-1", "d1", "部门-1-1"));
        deptList.add(new Dept("d1-2", "d1", "部门-1-2"));
        // ======================公司初始化数据================================
        companyList.add(new Company("c1", "d1-1", "公司-1"));
        companyList.add(new Company("c2", "d1-2", "公司-2"));
        companyList.add(new Company("c1-1", "c1", "公司-1-1"));
        companyList.add(new Company("c1-2", "c1", "公司-1-2"));

        treeNodeList.addAll(deptList);
        treeNodeList.addAll(companyList);
    }


    /*组装树形节 */
    private <T extends TreeNode> List<T> listTree(List<T> treeNodeList, Predicate<T> isRootNodePredicate) {
        // 当分组属性值为null时候替换为root
        Function<T, String> function = treeNode -> Objects.isNull(treeNode.getParentId()) ? "root" : treeNode.getParentId();
        // 根据父编号分组
        Map<String, List<T>> parentIdGroupMap = treeNodeList
                .stream()
                .collect(Collectors.groupingBy(function));
        // 组装节点的子元素
        for (T treeNode : treeNodeList) {
            treeNode.setChildren(parentIdGroupMap.get(treeNode.getId()));
        }
        // 返回根节点
        return treeNodeList.stream().filter(isRootNodePredicate).collect(Collectors.toList());
    }


    @Test
    public void testDept() {
        Predicate<Dept> isRootPredicate = e -> Objects.isNull(e.getParentId());
        List<Dept> treeNodes = listTree(deptList, isRootPredicate);
    }


    @Test
    public void testCompany() {
        Predicate<Company> isRootPredicate = e -> Objects.equals(e.getParentId(), "0");
        List<Company> companyList = listTree(this.companyList, isRootPredicate);
    }

    @Test
    public void testTreeNode() {
        Predicate<TreeNode> isRootPredicate = e -> Objects.isNull(e.getParentId());
        List<TreeNode> resultList = listTree(this.treeNodeList, isRootPredicate);
    }

}
