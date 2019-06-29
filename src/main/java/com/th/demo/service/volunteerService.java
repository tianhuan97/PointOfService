package com.th.demo.service;

import java.util.List;

import com.th.demo.model.volunteer;

public interface volunteerService {
	public volunteer selVolunteerById(String id);
	public void addVolunteer(volunteer vt);
	public List<volunteer> selVolunteer();
	public int delVolunteer(String numId);
	public int updateVolunteer(volunteer vt);
}
