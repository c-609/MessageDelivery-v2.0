package cn.tiger.vo;

import cn.tiger.dto.TreeNode;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

/**
 * UtilityClass注解，构造函数私有化
 *
 * create by yifeng
 */
@UtilityClass
public class TreeUtil {

    /**
     *  两层循环实现建树
     *
     * @param treeNodes 传入的树节点列表
     * @param root 根
     * @return
     */
    public <T extends TreeNode> List<T> buildByLoop(List<T> treeNodes, Object root) {
        List<T> trees = new ArrayList<>();

        for (T treeNode : treeNodes) {

            if(root.equals(treeNode.getParentId())) {
                trees.add(treeNode);
            }

            for (T it : treeNodes) {
                if(it.getParentId() == treeNode.getId()) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<>());
                    }
                    treeNode.add(it);
                }
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNode
     * @param treeNodes
     * @param <T>
     * @return
     */
    public <T extends TreeNode> T findChildren(T treeNode, List<T> treeNodes) {
        for (T it : treeNodes) {
            if (treeNode.getId() == it.getParentId()) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }

    /**
     * 递归查找叶子节点（非根）
     * @param trees 树
     * @param <T>
     * @return
     */
    public <T extends TreeNode> List<T> findLeafNode(List<T> trees) {
        List<T> list = new ArrayList<>();
        for (T it : trees) {
            if(it.getChildren().size() == 0) {
                list.add(it);
//                return list;
                continue;
            }
            findLeafNode(it.getChildren()).stream().forEach(item ->
            {
                list.add((T)item);
            });
        }
        return list;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode();
        TreeNode treeNode2 = new TreeNode();
        TreeNode treeNode3 = new TreeNode();
        TreeNode treeNode4 = new TreeNode();
        TreeNode treeNode5 = new TreeNode();

        treeNode1.setId(1);
        treeNode2.setId(2);
        treeNode3.setId(3);
        treeNode4.setId(4);
        treeNode5.setId(5);

        treeNode1.setParentId(-1);
        treeNode2.setParentId(-1);
        treeNode3.setParentId(2);
        treeNode4.setParentId(2);
        treeNode5.setParentId(1);

        List treeNodes = new ArrayList();
        treeNodes.add(treeNode1);
        treeNodes.add(treeNode2);
        treeNodes.add(treeNode3);
        treeNodes.add(treeNode4);
        treeNodes.add(treeNode5);
        List<TreeNode> tree = buildByLoop(treeNodes, -1);
//        TreeNode treeNodeP = findChildren(treeNode2, tree);
        List<TreeNode> leafNodes = findLeafNode(tree);
        TreeNode childrens= findChildren(treeNode2, tree);

        System.out.println("递归查找子节点（有根）" + childrens);
        System.out.println("叶子节点（无根）" + leafNodes);
    }

    /**
     * 通过menu创建树节点
     * @param menus
     * @param root
     * @return
     */
//    public List<MenuTree> buildTree(List<Menu> menus, int root) {
//        List<MenuTree> trees = new ArrayList<>();
//        MenuTree node;
//        for (Menu menu : menus) {
//            node = new MenuTree();
//            node.setId(menu.getId());
//            node.setParentId(menu.getParentId());
//            node.setName(menu.getName());
//            node.setPath(menu.getPath());
//            node.setComponent(menu.getComponent());
//            trees.add(node);
//        }
//        return TreeUtil.buildByLoop(trees, root);
//    }

}
