package com.stackroute.dao;

import com.stackroute.beans.Employee;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDao {
    JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public int save(Employee p){
        String sql="insert into Users(id,name,age,gender) values("+p.getId()+",'"+p.getName()+"',"+p.getAge()+",'"+p.getGender()+"')";
        return template.update(sql);
    }
    public int update(Employee p){
        String sql="update Users set name='"+p.getName()+"', age="+p.getAge()+",gender='"+p.getGender()+"' where id="+p.getId()+"";
        return template.update(sql);
    }
    public int delete(int id){
        String sql="delete from Users where id="+id+"";
        return template.update(sql);
    }
    public Employee getEmpById(int id){
        String sql="select * from Users where id=?";
        return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Employee>(Employee.class));
    }
    public List<Employee> getEmployees(){
        return template.query("select * from Users",new RowMapper<Employee>(){
            public Employee mapRow(ResultSet rs, int row) throws SQLException {
                Employee e=new Employee();
                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                e.setAge(rs.getInt(3));
                e.setGender(rs.getString(4));
                return e;
            }
        });
    }
}
