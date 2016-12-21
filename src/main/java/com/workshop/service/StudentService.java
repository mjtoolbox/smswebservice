package com.workshop.service;

import com.workshop.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * Created by mijo on 2016-07-05.
 */
@Service
public class StudentService {

//    private static List<Student> students = new ArrayList<Student>();

    public StudentService() {
        // Populate test data. Later each WS method will retrieve data from DB.
//        students.add(new Student(1001, "Mike", 17));
//        students.add(new Student(1002, "Jane", 19));
//        students.add(new Student(1003, "Bob", 19));
//        students.add(new Student(1004, "Susan", 22));
//        students.add(new Student(1005, "Daniel", 25));
//        students.add(new Student(1006, "John", 26));
//        students.add(new Student(1007, "Debbie", 28));
    }

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Student> findAll() {
        String sql = "SELECT * FROM `acsm_728bf0f8fa73974`.`student`";
        List<Student> students = jdbcTemplate.query(sql, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int i) throws SQLException {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setGender(rs.getString("gender"));
                student.setProvince(rs.getString("province"));
                return student;
            }
        });

        return students;
    }


    public Student findStudentById(int anId) {
        String sql = "SELECT * FROM `acsm_728bf0f8fa73974`.`student` WHERE id=" + anId;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Student>() {
            @Override
            public Student extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setName(rs.getString("name"));
                    student.setAge(rs.getInt("age"));
                    student.setGender(rs.getString("gender"));
                    student.setProvince(rs.getString("province"));
                    return student;
                }
                return null;
            }
        });
    }

    public Student findStudentByName(String aName) {
        String sql = "SELECT * FROM `acsm_728bf0f8fa73974`.`student` WHERE name=" + aName;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Student>() {
            @Override
            public Student extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setName(rs.getString("name"));
                    student.setAge(rs.getInt("age"));
                    student.setGender(rs.getString("gender"));
                    student.setProvince(rs.getString("province"));
                    return student;
                }
                return null;
            }
        });
    }

    public void removeStudentById(int anId) {
        String sql = "DELETE FROM `acsm_728bf0f8fa73974`.`student` WHERE id=?";
        jdbcTemplate.update(sql, anId);
    }

    public void saveOrUpdateStudent(Student aStudent) {
        if ( aStudent.getId() > 0){
            // update
            String sql = "UPDATE `acsm_728bf0f8fa73974`.`student` SET" +
                    "`name`=?" +
                    "`age`=?" +
                    "`gender`=?" +
                    "`province`=?" +
                    "WHERE `id`=?";
            jdbcTemplate.update(sql, aStudent.getName(), aStudent.getAge(), aStudent.getGender(),
                    aStudent.getProvince(), aStudent.getId());
        }
        else{
            // insert
            String sql = "INSERT INTO `acsm_728bf0f8fa73974`.`student` (`id`,`name`,`age`,`gender`,`province`)"+
                    "VALUES(?,?,?,?,?)";
            jdbcTemplate.update(sql, generateNextId(), aStudent.getName(), aStudent.getAge(), aStudent.getGender(),
                    aStudent.getProvince());
        }
    }


    public int generateNextId() {
        String sql = "SELECT max(id) FROM `acsm_728bf0f8fa73974`.`student`";
        return jdbcTemplate.queryForObject(sql, Integer.class) + 1;
    }
}
