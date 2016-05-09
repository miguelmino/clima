package com.mim.planetas.dao;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.appengine.api.utils.SystemProperty;

public final class EMF {
	

	static Map<String, String> properties = new HashMap();
	
	static{
		
		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
			properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.GoogleDriver");
			properties.put("javax.persistence.jdbc.url", System.getProperty("ae-cloudsql.cloudsql-database-url"));
		} else {
			properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
			properties.put("javax.persistence.jdbc.url", System.getProperty("ae-cloudsql.local-database-url"));
		}
	}

    private static final EntityManagerFactory emfInstance =
    		Persistence.createEntityManagerFactory("clima", properties);

    private EMF() {}

    public static EntityManagerFactory get() {
        return emfInstance;
    }
}