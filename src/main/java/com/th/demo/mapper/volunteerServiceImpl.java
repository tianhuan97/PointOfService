package com.th.demo.mapper;

import com.th.demo.dao.volunteerMapper;
import com.th.demo.model.volunteer;
import com.th.demo.service.volunteerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class volunteerServiceImpl implements volunteerService {
    @Resource
    private volunteerMapper vtDao;

    @Override
    public volunteer selVolunteerById(String id) {
        return vtDao.selVolunteerById(id);
    }

    @Override
    public void addVolunteer(volunteer vt) {
        vtDao.addVolunteer(vt);
    }

    @Override
    public List<volunteer> selVolunteer() {
        return vtDao.selVolunteer();
    }

    @Override
    public int delVolunteer(String numId) {
        return vtDao.delVolunteer(numId);
    }

    @Override
    public int updateVolunteer(volunteer vt) {
        return vtDao.updateVolunteer(vt);
    }
}
