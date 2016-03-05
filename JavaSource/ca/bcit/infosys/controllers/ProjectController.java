package ca.bcit.infosys.controllers;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import ca.bcit.infosys.managers.ProjectManager;
import ca.bcit.infosys.models.Project;

@Named("projectController")
@SessionScoped
public class ProjectController implements Serializable {

	@Inject
	private ProjectManager pjtmgr;
	
	public Project[] getAllProjects() {
		System.out.println("test");
		return pjtmgr.getAll();
	}
}
