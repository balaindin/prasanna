package com.app.property.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.property.service.bo.ProjectBO;
import com.app.property.service.dto.ProjectDTO;
import com.app.property.service.dto.WSOutputDTO;

@Controller
@RequestMapping("/project")
public class ProjectService {

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public WSOutputDTO<ProjectDTO> addProperty(@RequestBody ProjectDTO projectDTO) {
		WSOutputDTO<ProjectDTO> output = new WSOutputDTO<ProjectDTO>();

		try {
			output.setObject(projectBO.addProject(projectDTO));
			output.setMessage("Property succesfully added!");
		} catch (Exception ex) {
			output.setError("Error - adding the Project");
		}
		
		return output;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public String updateProject(@RequestBody ProjectDTO projectDTO) {
		WSOutputDTO<ProjectDTO> output = new WSOutputDTO<ProjectDTO>();

		try {
			output.setObject(projectBO.updateProject(projectDTO));
		} catch (Exception ex) {
			return ex.toString();
		}
		return "Project succesfully updated!";
	}

	@RequestMapping(value = "/get/{projectId}", method = RequestMethod.GET)
	@ResponseBody
	public WSOutputDTO<ProjectDTO> getProject(@PathVariable("projectId") long projectId) {
		WSOutputDTO<ProjectDTO> output = new WSOutputDTO<ProjectDTO>();
		
		try {
			output.setObject(projectBO.getProject(projectId));
			output.setMessage("Property succesfully retrieved!");
		} catch (Exception ex) {
			output.setError(ex.getMessage());
		}
		return output;
	}
		
	@Autowired
	private ProjectBO projectBO;
}
