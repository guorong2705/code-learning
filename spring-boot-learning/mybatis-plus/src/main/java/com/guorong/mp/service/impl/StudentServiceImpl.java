package com.guorong.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guorong.mp.entity.Student;
import com.guorong.mp.mapper.StudentMapper;
import com.guorong.mp.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {


}
