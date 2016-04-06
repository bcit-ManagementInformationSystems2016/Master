package ca.bcit.infosys.models;
 
import java.util.ArrayList;
import java.util.List;

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
 
    @SuppressWarnings("unused")
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
    
    public TreeManagedBean(WorkPackage top, List<WorkPackage> wps) {
    	root = newNodeWithChildren(top, null, wps);
    }
    
    public TreeNode newNodeWithChildren(WorkPackage wpParent, TreeNode parent, List<WorkPackage> wps){
        TreeNode newNode= new DefaultTreeNode(wpParent, parent);
        List<WorkPackage> childrenArray = new ArrayList<WorkPackage>();
        for (int i = 0; i < wps.size(); i++) {
        	if (wps.get(i).getParentWPID().equals(wpParent.getWpID())) {
        		childrenArray.add(wps.get(i));
        	}
        }
        for (WorkPackage wp : childrenArray){
             @SuppressWarnings("unused")
			TreeNode newNode2= newNodeWithChildren(wp, newNode, wps);
        }
        return newNode;
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
    }
 
    public void onNodeUnSelect(NodeUnselectEvent event){
    }
 
    public void onNodeExpand(NodeExpandEvent event){
    }
 
    public void onNodeCollapse(NodeCollapseEvent event){
    }
 
    public String printSelectedNodes(){
        return ""; 
    }
}