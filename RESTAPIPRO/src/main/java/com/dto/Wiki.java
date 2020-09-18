package com.dto;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement

public class Wiki {
	@Id@GeneratedValue
	private int wikiId;
	private String wikiName;
	private String description;
	public int getWikiId() {
		return wikiId;
	}
	public void setWikiId(int wikiId) {
		this.wikiId = wikiId;
	}
	public String getWikiName() {
		return wikiName;
	}
	public void setWikiName(String wikiName) {
		this.wikiName = wikiName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Wiki [wikiId=" + wikiId + ", wikiName=" + wikiName + ", description=" + description + "]";
	}

	
	
	
}
