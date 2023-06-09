package com.qa.hcm.api.pojo;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Filter {
	 @JsonIgnore
	    private Map<String, Object> additionalProperties = new HashMap<>();
	    
	  @JsonAnyGetter
	  public Map<String, Object> getAdditionalProperties() {
	      return this.additionalProperties;
	  }

	  @JsonAnySetter
	  public void setAdditionalProperty(String name, Object value) {
	      this.additionalProperties.put(name, value);
	  }
}
