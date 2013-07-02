package com.mainapp.mynoppaapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable{

	private ArrayList<String> links= new ArrayList<String>();
	private String dept_id;
	private String course_id;
	private String course_url_oodi;
	private String noppa_language;
	private String course_url;
	private String name;
	public Course(ArrayList l, String d, String c, String cou, String n, String curl, String na){
		links = l;
		dept_id =d;
		course_id=c;
		course_url_oodi=cou;
		noppa_language=n;
		course_url=curl;
		name=na;
	}
	public String getDept_id() {
		return dept_id;
	}
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getCourse_url_oodi() {
		return course_url_oodi;
	}
	public void setCourse_url_oodi(String course_url_oodi) {
		this.course_url_oodi = course_url_oodi;
	}
	public String getNoppa_language() {
		return noppa_language;
	}
	public void setNoppa_language(String noppa_language) {
		this.noppa_language = noppa_language;
	}
	public String getCourse_url() {
		return course_url;
	}
	public void setCourse_url(String course_url) {
		this.course_url = course_url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
//	"links":[{"uri":"http:\/\/noppa-api-dev.aalto.fi\/api\/v1\/courses\/Kon-41.3006","title":"self","rel":"self"}],
//	"dept_id":"T2030",
//	"course_id":"Kon-41.3006",
//	"course_url_oodi":"https:\/\/oodi.aalto.fi\/a\/opintjakstied.jsp?Kieli=6&Tunniste=Kon-41.3006&html=1",
//	"noppa_language":"en",
//	"course_url":"http:\/\/noppa-api-dev.aalto.fi\/noppa\/kurssi\/Kon-41.3006",
//	"name":"Computer Aided Design Basic Course"},

}