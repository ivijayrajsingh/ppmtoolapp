package in.codeblog.ppmapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.codeblog.ppmapi.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {
	
	@Override
	Iterable<Project> findAllById(Iterable<Long> ids);
	
	Project findByProjectIdentifier(String projectId);
	
	@Override
	Iterable<Project> findAll();

}
