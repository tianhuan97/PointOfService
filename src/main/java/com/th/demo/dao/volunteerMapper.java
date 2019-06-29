package com.th.demo.dao;

import java.util.List;

import com.th.demo.model.volunteer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface volunteerMapper {
	public void addVolunteer(volunteer vt);
	public volunteer selVolunteerById(String id);
	public List<volunteer> selVolunteer();
	public int delVolunteer(String numId);
	public int updateVolunteer(volunteer vt);
}
