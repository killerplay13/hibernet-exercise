package web.emp.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;
@Data
@Entity
public class Dept {
	@Id
	    private Integer deptno;
	    private String dname;
	    private String loc;

//	    @OneToMany
////	    @JoinColumn(name="對方關聯欄位",referencedColumnName="自方關聯欄位")
//	    @JoinColumn(name = "DEPTNO", referencedColumnName="DEPTNO")
	    
	    @OneToMany(mappedBy = "dept")
	    private List<Emp> emps;
}
