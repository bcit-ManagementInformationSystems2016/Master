package ca.bcit.infosys.controllers;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
 
@ManagedBean
@SessionScoped
public class TreeManagedBean {
    // TreeNode instance
    private TreeNode root;
    private TreeNode singleSelectedTreeNode;
    private TreeNode [] multipleSelectedTreeNodes;
    private TreeNode [] checkboxSelectedTreeNodes;
 
    public TreeManagedBean(){
        // root node
        this.root = new DefaultTreeNode("Journey To The Dark Side of The Moon", null);
        
        // elaboration node
        TreeNode Elaboration = new DefaultTreeNode("Elaboration", this.root);
        // inception node
        TreeNode Inception = new DefaultTreeNode("Inception", this.root);
        // construction node
        TreeNode Construction = new DefaultTreeNode("Construction", this.root);
        
        // child node for Elaboration
        TreeNode Time = new DefaultTreeNode("document","Time", Elaboration);        
        // child node for Elaboration
        TreeNode Money = new DefaultTreeNode("image","Money", Elaboration);
        // child node for Elaboration
        TreeNode Great_Gig_in_the_Sky = new DefaultTreeNode("image","Great Gig in the Sky", Elaboration);
        
        // child node for Inception
        TreeNode More_test = new DefaultTreeNode("document","More test", Inception);        
        // child node for Inception
        TreeNode Dark_Side_of_the_Moon = new DefaultTreeNode("image","Dark Side of the Moon", Inception);
        
        // child node for Construction
        TreeNode test_project  = new DefaultTreeNode("image"," test project", Construction);
        
    }
 
    public TreeNode getRoot() {
        return root;
    }
 
    public void setRoot(TreeNode root) {
        this.root = root;
    }
 
    public TreeNode getSingleSelectedTreeNode() {
        return singleSelectedTreeNode;
    }
 
    public void setSingleSelectedTreeNode(TreeNode singleSelectedTreeNode) {
        this.singleSelectedTreeNode = singleSelectedTreeNode;
    }
 
    public TreeNode[] getMultipleSelectedTreeNodes() {
        return multipleSelectedTreeNodes;
    }
 
    public void setMultipleSelectedTreeNodes(TreeNode[] multipleSelectedTreeNodes) {
        this.multipleSelectedTreeNodes = multipleSelectedTreeNodes;
    }
 
    public TreeNode[] getCheckboxSelectedTreeNodes() {
        return checkboxSelectedTreeNodes;
    }
 
    public void setCheckboxSelectedTreeNodes(TreeNode[] checkboxSelectedTreeNodes) {
        this.checkboxSelectedTreeNodes = checkboxSelectedTreeNodes;
    }
 
    public void onNodeSelect(NodeSelectEvent event){
        System.out.println("Node Data ::"+event.getTreeNode().getData()+" :: Selected");
    }
 
    public void onNodeUnSelect(NodeUnselectEvent event){
        System.out.println("Node Data ::"+event.getTreeNode().getData()+" :: UnSelected");
    }
 
    public void onNodeExpand(NodeExpandEvent event){
        System.out.println("Node Data ::"+event.getTreeNode().getData()+" :: Expanded");
    }
 
    public void onNodeCollapse(NodeCollapseEvent event){
        System.out.println("Node Data ::"+event.getTreeNode().getData()+" :: Collapsed");
    }
 
    public String printSelectedNodes(){
        System.out.println("Single Selection Is :: "+this.singleSelectedTreeNode.getData());
        for(TreeNode n : this.multipleSelectedTreeNodes){
            System.out.println("Multiple Selection Are :: "+n.getData());
        }
        for(TreeNode n : this.checkboxSelectedTreeNodes){
            System.out.println("CheckBox Selection Are :: "+n.getData());
        }
        return "";
    }
}