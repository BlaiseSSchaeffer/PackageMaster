package com.blaise2s.packagemaster.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.blaise2s.packagemaster.api.DeliveryRestResource;
import com.blaise2s.packagemaster.api.MetadataRestResource;
import com.blaise2s.packagemaster.api.ResidentRestResource;
import com.blaise2s.packagemaster.api.UnitRestResource;

@ApplicationPath("/api")
public class PackakeMasterAPI extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>(1);
		classes.add(ResidentRestResource.class);
		classes.add(UnitRestResource.class);
		classes.add(DeliveryRestResource.class);
		classes.add(MetadataRestResource.class);
		return classes;
	}

}
