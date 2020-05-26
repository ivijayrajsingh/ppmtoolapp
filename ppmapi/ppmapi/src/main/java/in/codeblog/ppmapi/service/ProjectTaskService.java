package in.codeblog.ppmapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.codeblog.ppmapi.domain.Backlog;
import in.codeblog.ppmapi.domain.ProjectTask;
import in.codeblog.ppmapi.repository.BacklogRepository;
import in.codeblog.ppmapi.repository.ProjectTaskRepository;

@Service
public class ProjectTaskService {
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
		
		
		//Exceptions: Project not Found
		
		//ProjectTasks to be added to specific project, project!=null then Backlog Exists
		Backlog backlog =  backlogRepository.findByProjectIdentifier(projectIdentifier);
		
		//Set the Backlog to project task
		projectTask.setBacklog(backlog);
		
		
		Integer backLogSequence = backlog.getPTSequence();
		
		//Update the BacklogSequence by one
		backLogSequence++;
		backlog.setPTSequence(backLogSequence);
		
		//We want our project Sequence to be like idpro-1 ....
		//Adding  backlogSequence to ProjectTask
		projectTask.setProjectSequence(projectIdentifier+"-"+backLogSequence);
		
		projectTask.setProjectIdentifer(projectIdentifier);
		
		// Initial priority when priority is not set
		if(projectTask.getPriority()==null) {
			projectTask.setPriority(3);
		}
		
		// Initial Status when status is not set
		if(projectTask.getStatus()=="" || projectTask.getStatus()==null) {
			projectTask.setStatus("TO_DO");
		}
		return projectTaskRepository.save(projectTask);
	}
}
