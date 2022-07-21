package com.clientutil.repoistory;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.clientutil.entity.ClientEndpoint;

public interface ClientEndpointRepoistory extends CrudRepository<ClientEndpoint,Long> {
	List<ClientEndpoint> findAll(); 

}
