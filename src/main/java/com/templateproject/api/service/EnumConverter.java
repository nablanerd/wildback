package com.templateproject.api.service;

import com.templateproject.api.entity.BuildingType;
import com.templateproject.api.entity.TechnologyType;

public class EnumConverter {
	 public static TechnologyType getTechnologyTypeFromString(String technologyString) {
	        try {
	            return TechnologyType.valueOf(technologyString.toUpperCase());
	        } catch (IllegalArgumentException e) {
	            // TODO chaine incorrecte
	            return null;
	        }
	    }

	    public static BuildingType getBuildingTypeFromString(String buildingTypeString) {
	        try {
	            return BuildingType.valueOf(buildingTypeString.toLowerCase());
	        } catch (IllegalArgumentException e) {
	            // TODO  chaine incorecte 
	        	return null;
	        }
	    }
}
