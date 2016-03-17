package ca.bcit.infosys.controllers;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ca.bcit.infosys.managers.ProjectManager;
import ca.bcit.infosys.models.Project;

//@ManagedBean
//@FacesConverter(value="projectConverter")
@Named("projectConverter")
//@Default
@SessionScoped
public class ProjectConverter implements Converter, Serializable {
	//@Inject
	//private ProjectManager pjtmgr;
	@PersistenceContext
	(unitName="BluehostTesty") EntityManager em;
	
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		System.out.println("this is the value: " + value);
		return em.find(Project.class, new Integer(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		Project project;
        project = (Project) value;
        return String.valueOf(project.getProjectID());
	}

}
